package be.valuya.bob.core.api.troll;

import be.valuya.accountingtroll.AccountingEventListener;
import be.valuya.accountingtroll.AccountingManager;
import be.valuya.accountingtroll.domain.ATAccount;
import be.valuya.accountingtroll.domain.ATAccountImputationType;
import be.valuya.accountingtroll.domain.ATAccountingEntry;
import be.valuya.accountingtroll.domain.ATBookPeriod;
import be.valuya.accountingtroll.domain.ATBookYear;
import be.valuya.accountingtroll.domain.ATDocument;
import be.valuya.accountingtroll.domain.ATPeriodType;
import be.valuya.accountingtroll.domain.ATTax;
import be.valuya.accountingtroll.domain.ATThirdParty;
import be.valuya.accountingtroll.domain.ATThirdPartyType;
import be.valuya.accountingtroll.event.BalanceChangeEvent;
import be.valuya.bob.core.domain.BobAccount;
import be.valuya.bob.core.domain.BobAccountHistoryEntry;
import be.valuya.bob.core.domain.BobCompany;
import be.valuya.bob.core.domain.BobCompanyHistoryEntry;
import be.valuya.bob.core.domain.BobDocument;
import be.valuya.bob.core.domain.BobException;
import be.valuya.bob.core.config.BalanceComputationMode;
import be.valuya.bob.core.config.BobFileConfiguration;
import be.valuya.bob.core.domain.BobPeriod;
import be.valuya.bob.core.BobTheTinker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemoryCachingBobAccountingManager implements AccountingManager {

    private static final BigDecimal ZERO_EURO = BigDecimal.ZERO.setScale(3, RoundingMode.UNNECESSARY);

    private static final Comparator<BobPeriod> BOB_PERIOD_COMPARATOR = Comparator.comparing(BobPeriod::getfYear)
            .thenComparing(BobPeriod::getYear)
            .thenComparing(BobPeriod::getMonth);

    private final BobTheTinker bobTheTinker;

    private final BobFileConfiguration bobFileConfiguration;

    // Caches
    private Map<String, ATBookYear> bookYears;
    private Map<ATBookYear, List<ATBookPeriod>> bookPeriods;
    private Map<String, ATAccount> accounts;
    private Map<String, ATThirdParty> thirdParties;
    private Map<String, ATDocument> documents;

    public MemoryCachingBobAccountingManager(BobFileConfiguration bobFileConfiguration) {
        bobTheTinker = new BobTheTinker();
        this.bobFileConfiguration = bobFileConfiguration;
    }

    @Override
    public Optional<LocalDateTime> getLastAccountModificationTime() {
        return bobTheTinker.getLastAccountModifiactionDate(bobFileConfiguration);
    }

    @Override
    public Stream<ATAccount> streamAccounts() {
        return bobTheTinker.readAccounts(bobFileConfiguration)
                .filter(this::isValidAccount)
                .map(this::convertToTrollAccount);
    }

    @Override
    public Stream<ATBookYear> streamBookYears() {
        // Seems periods are grouped by the 'fYear' field which can span more than 1 civil year.
        return bobTheTinker.readPeriods(bobFileConfiguration)
                .sorted(BOB_PERIOD_COMPARATOR)
                .filter(isValidBookYearPeriod())
                .collect(Collectors.groupingBy(BobPeriod::getfYear))
                .entrySet()
                .stream()
                .map(entry -> this.convertToTrollBookYear(entry.getKey(), entry.getValue()));
    }


    @Override
    public Stream<ATBookPeriod> streamPeriods() {
        return bobTheTinker.readPeriods(bobFileConfiguration)
                .filter(this::isValidPeriod)
                .map(this::convertToTrollPeriod);
    }

    @Override
    public Stream<ATThirdParty> streamThirdParties() {
        return bobTheTinker.readCompanies(bobFileConfiguration)
                .filter(this::isValidCompany)
                .flatMap(this::convertToTrollThirdParties);
    }

    @Override
    public Stream<ATAccountingEntry> streamAccountingEntries(AccountingEventListener accountingEventListener) {
        // Balance cache
        Map<String, AccountBalance> balancesPerAccountCode = new ConcurrentSkipListMap<>();

        Comparator<BobAccountHistoryEntry> entryComparator = Comparator
                .comparing((BobAccountHistoryEntry e) -> e.getHfyearOptional().orElse("")) //TODO: not optional
                .thenComparing((BobAccountHistoryEntry e) -> e.getHyearOptional().orElse(-1))
                .thenComparing((BobAccountHistoryEntry e) -> e.getHmonthOptional().orElse(-1))
                .thenComparing((BobAccountHistoryEntry e) -> e.getHdocdateOptional().orElse(LocalDate.MIN))
                .thenComparing((BobAccountHistoryEntry e) -> e.getHdocnoOptional().orElse(null), Comparator.nullsFirst(Comparator.naturalOrder())); // balance first


        Stream<ATAccountingEntry> entryStream = bobTheTinker.readAccountHistoryEntries(bobFileConfiguration)
                .sorted(entryComparator)
                .filter(this::isValidHistoryEntry)
                .flatMap(entry -> convertWithBalanceCheck(entry, balancesPerAccountCode, accountingEventListener));

        // Ensure all accounts with the yealryReset flag will fire a balance change event if relevant.
        streamAccounts().filter(ATAccount::isYearlyBalanceReset)
                .map(account -> this.createYearlyResetBalanceChangeEventOptional(account, balancesPerAccountCode))
                .flatMap(this::streamOptional)
                .forEach(accountingEventListener::handleBalanceChangeEvent);

        return entryStream;
    }

    @Override
    public Stream<ATDocument> streamDocuments() {
        return bobTheTinker.readDocuments(bobFileConfiguration)
                .filter(this::isValidDocument)
                .map(this::convertToTrollDocument);
    }


    private Optional<BalanceChangeEvent> createYearlyResetBalanceChangeEventOptional(ATAccount atAccount, Map<String, AccountBalance> balancesPerAccountCode) {
        ATBookPeriod lastBookYearOpening = streamPeriods().filter(p -> p.getPeriodType() == ATPeriodType.OPENING)
                .reduce((c, n) -> n)
                .orElseThrow(() -> new BobException("No period opening"));
        LocalDate balanceResetDate = lastBookYearOpening.getStartDate();

        String accountCode = atAccount.getCode();
        AccountBalance accountBalanceNullable = balancesPerAccountCode.get(accountCode);

        // If there is already a balance dated in this book year, skip
        boolean hasValidBalance = Optional.ofNullable(accountBalanceNullable)
                .filter(b -> !b.getDate().isBefore(balanceResetDate))
                .isPresent();
        if (hasValidBalance) {
            return Optional.empty();
        }

        // Otherwise, reset balance to 0 and fire event
        AccountBalance accountBalance = createAccountBalance(atAccount, balanceResetDate, lastBookYearOpening, ZERO_EURO);
        BalanceChangeEvent balanceChangeEvent = createBalanceChangeEvent(accountBalance, Optional.empty());
        return Optional.of(balanceChangeEvent);
    }

    private boolean isValidAccount(BobAccount bobAccount) {
        String aid = bobAccount.getAid();
        return aid != null && isNotEmptyString(aid.trim());
    }

    private boolean isValidCompany(BobCompany bobCompany) {
        String cid = bobCompany.getcId();
        Optional<String> name1Optional = bobCompany.getcName1Optional();
        return cid != null && isNotEmptyString(cid) && name1Optional.isPresent();
    }

    private boolean isValidPeriod(BobPeriod bobPeriod) {
        String label = bobPeriod.getLabel();
        return label != null && isNotEmptyString(label.trim());
    }

    private boolean isValidDocument(BobDocument bobDocument) {
        String id = bobDocument.getId();
        String fyear = bobDocument.getFyear();
        String dbk = bobDocument.getDbk();
        return id != null && fyear != null && dbk != null;
    }

    private Predicate<BobPeriod> isValidBookYearPeriod() {
        return p -> p.getMonth() > 0;
    }

    private boolean isValidHistoryEntry(BobAccountHistoryEntry historyEntry) {
        String hid = historyEntry.getHid();
        Optional<Integer> hmonthOptional = historyEntry.getHmonthOptional();
        Optional<Integer> hyearOptional = historyEntry.getHyearOptional();
        Optional<String> hdbkOptional = historyEntry.getHdbkOptional();
        Optional<BigDecimal> hamountOptional = historyEntry.getHamountOptional();
        return hid != null && isNotEmptyString(hid.trim())
                && hmonthOptional.isPresent()
                && hyearOptional.isPresent()
                && hdbkOptional.isPresent()
                && hamountOptional.isPresent()
                ;
    }

    private Stream<ATAccountingEntry> convertWithBalanceCheck(BobAccountHistoryEntry entry, Map<String, AccountBalance> balancesPerAccountCode, AccountingEventListener accountingEventListener) {
        ATAccountingEntry atAccountingEntry = convertToTrollAccountingEntry(entry);

        BalanceComputationMode balanceComputationMode = bobFileConfiguration.getBalanceComputationMode();
        switch (balanceComputationMode) {
            case BOOK_YEAR_ENTRIES_ONLY:
                return this.checkBalanceResetingOnNewBookYear(atAccountingEntry, balancesPerAccountCode, accountingEventListener);
            case IGNORE_OPENINGS_FOR_INTERMEDIATE_YEARS:
                return this.checkBalanceChangeIgnoringIntermediateOpeningPeriods(atAccountingEntry, balancesPerAccountCode, accountingEventListener);
            default:
                throw new BobException("Unhandled balance computation mode: " + balanceComputationMode);
        }
    }

    private Stream<ATAccountingEntry> checkBalanceResetingOnNewBookYear(ATAccountingEntry atAccountingEntry, Map<String, AccountBalance> balancesPerAccountCode, AccountingEventListener accountingEventListener) {
        ATBookPeriod bookPeriod = atAccountingEntry.getBookPeriod();
        ATPeriodType periodType = bookPeriod.getPeriodType();

        Optional<AccountBalance> currentBalance = getResettedBalanceOnBookYearChange(atAccountingEntry, balancesPerAccountCode);
        AccountBalance newBalance;
        if (periodType == ATPeriodType.OPENING) {
            newBalance = resetAccountBalance(atAccountingEntry, balancesPerAccountCode);
        } else {
            newBalance = updateAccountBalanceAfterAccountingEntry(atAccountingEntry, balancesPerAccountCode, currentBalance);
        }
        BalanceChangeEvent balanceChangeEvent = createBalanceChangeEvent(newBalance, Optional.of(atAccountingEntry));
        accountingEventListener.handleBalanceChangeEvent(balanceChangeEvent);

        return Stream.of(atAccountingEntry);
    }

    private Stream<ATAccountingEntry> checkBalanceChangeIgnoringIntermediateOpeningPeriods(ATAccountingEntry atAccountingEntry, Map<String, AccountBalance> balancesPerAccountCode, AccountingEventListener accountingEventListener) {
        ATAccount atAccount = atAccountingEntry.getAccount();
        String accountCode = atAccount.getCode();
        ATBookPeriod bookPeriod = atAccountingEntry.getBookPeriod();
        ATPeriodType periodType = bookPeriod.getPeriodType();

        AccountBalance curBalance = balancesPerAccountCode.get(accountCode);
        AccountBalance newBalance;
        if (curBalance == null) {
            if (periodType != ATPeriodType.OPENING) {
                // TODO: warn? Without any amount for the opening of the first book year, we have to assume it was 0
            }
            newBalance = resetAccountBalance(atAccountingEntry, balancesPerAccountCode);
        } else if (periodType != ATPeriodType.OPENING) {
            newBalance = updateAccountBalanceAfterAccountingEntry(atAccountingEntry, balancesPerAccountCode);
        } else {
            // Ignore
            return Stream.empty();
        }
        BalanceChangeEvent balanceChangeEvent = createBalanceChangeEvent(newBalance, Optional.of(atAccountingEntry));
        accountingEventListener.handleBalanceChangeEvent(balanceChangeEvent);
        return Stream.of(atAccountingEntry);
    }


    private BalanceChangeEvent createBalanceChangeEvent(AccountBalance accountBalance, Optional<ATAccountingEntry> accountingEntryOptional) {
        ATAccount account = accountBalance.getAccount();
        BigDecimal balanceAmount = accountBalance.getBalance();
        LocalDate balanceDate = accountBalance.getDate();
        ATAccountImputationType imputationType = account.getImputationType();

        BalanceChangeEvent changeEvent = new BalanceChangeEvent();
        changeEvent.setAccount(account);
        changeEvent.setNewBalance(balanceAmount);
        changeEvent.setDate(balanceDate);
        changeEvent.setAccountingEntryOptional(accountingEntryOptional);
        return changeEvent;
    }

    private ATAccount convertToTrollAccount(BobAccount bobAccount) {
        String accountNumber = bobAccount.getAid();
        String name = bobAccount.getHeading1Optional()
                .orElse("-");
        boolean yearResetAccount = isYearResetAccount(accountNumber);
        ATAccountImputationType atAccountImputationType = bobAccount.getAdbcdOptional()
                .map(this::createATAccountImputationType)
                .orElseThrow(() -> new BobException("No debit/credit set on account " + accountNumber)); // TODO: required

        ATAccount account = new ATAccount();
        account.setCode(accountNumber);
        account.setName(name);
        account.setYearlyBalanceReset(yearResetAccount);
        account.setImputationType(atAccountImputationType);
        return account;
    }

    private ATAccountImputationType createATAccountImputationType(String value) {
        switch (value) {
            case "D":
                return ATAccountImputationType.DEBIT;
            case "C":
                return ATAccountImputationType.CREDIT;
            default:
                return ATAccountImputationType.UNKNOWN;
        }
    }

    private boolean isYearResetAccount(String accountCode) {
        return accountCode.startsWith("6") || accountCode.startsWith("7");
    }

    private ATBookYear convertToTrollBookYear(String fYear, List<BobPeriod> periods) {
        BobPeriod firstPeriod = periods.stream()
                .filter(this::isValidPeriod)
                .filter(p -> p.getMonth() > 0)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        BobPeriod lastPeriod = periods.get(periods.size() - 1);
        LocalDate periodStartDate = getPeriodStartDate(firstPeriod);
        LocalDate periodEndDate = getPeriodEndDate(lastPeriod);

        ATBookYear bookYear = new ATBookYear();
        bookYear.setName(fYear);
        bookYear.setStartDate(periodStartDate);
        bookYear.setEndDate(periodEndDate);

        return bookYear;
    }


    private ATBookPeriod convertToTrollPeriod(BobPeriod bobPeriod) {
        String yearName = bobPeriod.getfYear();
        ATBookYear bookYear = findBookYearByName(yearName)
                .orElseThrow(() -> new BobException("No book year matching " + yearName));
        String periodShortName = bobPeriod.getLabel();
        int month = bobPeriod.getMonth();

        LocalDate periodStartDate;
        LocalDate periodEndDate;
        ATPeriodType periodType;
        if (month == 0) {
            periodStartDate = bookYear.getStartDate();
            // TODO: to mimic winbooks, but might not be monthly periods
            periodEndDate = periodStartDate.plusMonths(1);
            periodType = ATPeriodType.OPENING;
        } else {
            periodStartDate = getPeriodStartDate(bobPeriod);
            periodEndDate = getPeriodEndDate(bobPeriod);
            periodType = ATPeriodType.GENERAL;
        }

        ATBookPeriod bookPeriod = new ATBookPeriod();
        bookPeriod.setBookYear(bookYear);
        bookPeriod.setName(periodShortName);
        bookPeriod.setStartDate(periodStartDate);
        bookPeriod.setEndDate(periodEndDate);
        bookPeriod.setPeriodType(periodType);
        return bookPeriod;
    }


    private Stream<ATThirdParty> convertToTrollThirdParties(BobCompany bobCompany) {
        // A single company for both client/suppliers, while a thirdparty has a single purpose
        Optional<String> cVatNumberOptional = bobCompany.getcVatNumberOptional();
        Optional<String> sVatNumberOptional = bobCompany.getsVatNumberOptional();

        ATThirdParty clientThirdParty = createBaseThirdParty(bobCompany);
        clientThirdParty.setType(ATThirdPartyType.CLIENT);
        cVatNumberOptional.ifPresent(clientThirdParty::setVatNumber);

        ATThirdParty supplierThirdParty = createBaseThirdParty(bobCompany);
        supplierThirdParty.setType(ATThirdPartyType.SUPPLIER);
        sVatNumberOptional.ifPresent(supplierThirdParty::setVatNumber);

        return Stream.of(clientThirdParty, supplierThirdParty);
    }

    private ATThirdParty createBaseThirdParty(BobCompany bobCompany) {
        String id = bobCompany.getcId();
        Optional<String> fullName = bobCompany.getcName1Optional().filter(this::isNotEmptyString);
        Optional<String> address = bobCompany.getcAddress1Optional().filter(this::isNotEmptyString);
        Optional<String> zipCode = bobCompany.getcZipCodeOptional().filter(this::isNotEmptyString);
        Optional<String> city = bobCompany.getcLocalityOptional().filter(this::isNotEmptyString);
        Optional<String> countryCode = bobCompany.getcCountryCodeOptional().filter(this::isNotEmptyString);
        Optional<String> phoneNumber = bobCompany.getcPhoneNumberOptional().filter(this::isNotEmptyString);
        Optional<String> bankAccount = bobCompany.getcBankNumberOptional().filter(this::isNotEmptyString);
        Optional<String> languageOptional = bobCompany.getcLanguageOptional().filter(this::isNotEmptyString);

        ATThirdParty baseThirdParty = new ATThirdParty();
        baseThirdParty.setId(id);
        baseThirdParty.setFullName(fullName.orElse(null));
        baseThirdParty.setAddress(address.orElse(null));
        baseThirdParty.setZipCode(zipCode.orElse(null));
        baseThirdParty.setCity(city.orElse(null));
        baseThirdParty.setCountryCode(countryCode.orElse(null));
        baseThirdParty.setPhoneNumber(phoneNumber.orElse(null));
        baseThirdParty.setBankAccountNumber(bankAccount.orElse(null));
        baseThirdParty.setLanguage(languageOptional.orElse(null));
        return baseThirdParty;
    }

    private boolean isNotEmptyString(String s) {
        return !s.trim().isEmpty();
    }

    private ATAccountingEntry convertToTrollAccountingEntry(BobAccountHistoryEntry entry) {
        ATBookYear bookYear = entry.getHfyearOptional()
                .flatMap(this::findBookYearByName)
                .orElseThrow(() -> new BobException("No book year found with name " + entry.getHfyearOptional()));

        ATBookPeriod bookPeriod = listYearPeriods(bookYear).stream()
                .filter(p -> this.isSameBookYearPeriod(p, entry))
                .findAny()
                .orElseThrow(() -> new BobException("No period found in entry for book year " + bookYear));

        String dbkCode = entry.getHdbkOptional().orElseThrow(() -> new BobException("No dbk entry for accounting entry"));


        String accountNumber = entry.getHid();
        ATAccount account = this.findAccountByCode(accountNumber)
                .orElseThrow(() -> new BobException("No account found with code" + accountNumber));

        BigDecimal amount = entry.getHamountOptional().orElseThrow(() -> new BobException("No amount for entry"));
        BigDecimal signedAmount = getSignedAmount(account, amount);

        Optional<String> thirdPartyName = entry.getHcussupOptional();
        ATThirdPartyType thirdPartyType = entry.getHcstypeOptional()
                .flatMap(this::parseThirdPartyTypeFromHSType)
                .orElse(ATThirdPartyType.CLIENT);
        Optional<ATThirdParty> thirdPartyOptional = thirdPartyName
                .filter(this::isNotEmptyString)
                .flatMap(name -> this.findThirdPartyByIdAndType(name, thirdPartyType));

        LocalDate entryDate = entry.getHdocdateOptional().orElseThrow(() -> new BobException("No date for entry"));
        Optional<LocalDate> documentDateOptional = entry.getHdocdateOptional();
        Optional<LocalDate> dueDateOptional = entry.getHduedateOptional();
        Optional<String> commentOptional = entry.getHremOptional();
        Integer docNumber = entry.getHdocnoOptional().orElse(-1);
        Integer orderingNumber = entry.getHordernoOptional().orElse(-1);

        Optional<ATTax> taxOptional = Optional.empty(); //TODO
        Optional<ATDocument> documentOptional = Optional.empty(); // TODO

        ATAccountingEntry accountingEntry = new ATAccountingEntry();
        accountingEntry.setBookPeriod(bookPeriod);
        accountingEntry.setDate(entryDate);
        accountingEntry.setAmount(signedAmount);
        accountingEntry.setDbkCode(dbkCode);
        accountingEntry.setAccount(account);
        accountingEntry.setDocNumber(docNumber);
        accountingEntry.setOrderingNumber(orderingNumber);

        accountingEntry.setThirdPartyOptional(thirdPartyOptional);
        accountingEntry.setDocumentDateOptional(documentDateOptional);
        accountingEntry.setDueDateOptional(dueDateOptional);
        accountingEntry.setCommentOptional(commentOptional);
        accountingEntry.setTaxOptional(taxOptional);
        accountingEntry.setDocumentOptional(documentOptional);
        return accountingEntry;
    }


    private ATDocument convertToTrollDocument(BobDocument bobDocument) {
        String id = bobDocument.getId();
        String fyear = bobDocument.getFyear();
        int year = bobDocument.getYear();
        int month = bobDocument.getMonth();
        String dbk = bobDocument.getDbk();
        int docNo = bobDocument.getDocNo();

        ATBookYear bookYear = this.findBookYearByName(fyear)
                .orElseThrow(() -> new BobException("No book year found with name " + fyear));

        ATBookPeriod bookPeriod = listYearPeriods(bookYear).stream()
                .filter(p -> this.isSamePeriod(p, year, month))
                .findAny()
                .orElseThrow(() -> new BobException("No period found in entry for book year " + bookYear));


        ATDocument atDocument = new ATDocument();
        atDocument.setId(id);
        atDocument.setBookPeriod(bookPeriod);
        atDocument.setDbkCode(dbk);
        atDocument.setDocNumber(docNo);
        return atDocument;
    }

    private BigDecimal getSignedAmount(ATAccount account, BigDecimal amount) {
        // Amount are negated.
        // We want positive amount for credit, negative for debit
        return amount.negate();
    }

    private Optional<ATThirdPartyType> parseThirdPartyTypeFromHSType(String hsType) {
        switch (hsType) {
            case "":
                return Optional.empty();
            case "S":
                return Optional.of(ATThirdPartyType.SUPPLIER);
            case "C":
                return Optional.of(ATThirdPartyType.CLIENT);
            default:
                throw new BobException("Unhandled hcsType: " + hsType);
        }
    }


    private boolean isSameBookYearPeriod(ATBookPeriod bookPeriod, BobAccountHistoryEntry accountingEntry) {
        int year = accountingEntry.getHyearOptional().orElseThrow(() -> new BobException("No year set in entry"));
        int month = accountingEntry.getHmonthOptional().orElseThrow(() -> new BobException("No month set in entry"));
        return isSamePeriod(bookPeriod, year, month);
    }

    private boolean isSameBookYearPeriod(ATBookPeriod bookPeriod, BobCompanyHistoryEntry historyEntry) {
        int year = historyEntry.getHyear().orElseThrow(() -> new BobException("No year set in entry"));
        int month = historyEntry.getHmonth().orElseThrow(() -> new BobException("No month set in entry"));
        return isSamePeriod(bookPeriod, year, month);
    }

    private boolean isSamePeriod(ATBookPeriod bookPeriod, int year, int month) {
        if (month == 0) {
            return bookPeriod.getPeriodType() == ATPeriodType.OPENING;
        } else {
            if (bookPeriod.getPeriodType() != ATPeriodType.GENERAL) {
                return false;
            }
        }
        LocalDate startDate = bookPeriod.getStartDate();
        int periodYear = startDate.get(ChronoField.YEAR);
        int periodMonth = startDate.get(ChronoField.MONTH_OF_YEAR);
        return periodYear == year && periodMonth == month;
    }


    private LocalDate getPeriodEndDate(BobPeriod period) {
        int lastYear = period.getYear();
        int lastMonth = period.getMonth();
        return LocalDate.now().withYear(lastYear).withMonth(lastMonth).withDayOfMonth(1)
                .plusMonths(1);
    }

    private LocalDate getPeriodStartDate(BobPeriod period) {
        int firstYear = period.getYear();
        int firstMonth = period.getMonth();
        return LocalDate.now().withYear(firstYear).withMonth(firstMonth).withDayOfMonth(1);
    }


    private List<ATBookYear> listBookYears() {
        return new ArrayList<>(getBookYears().values());
    }

    private List<ATBookPeriod> listPeriods() {
        return getBookPeriods().values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ATBookPeriod> listYearPeriods(ATBookYear bookYear) {
        List<ATBookPeriod> periodsNullable = getBookPeriods().get(bookYear);
        return Optional.ofNullable(periodsNullable).orElseGet(ArrayList::new);
    }

    private Optional<ATBookYear> findBookYearByName(String name) {
        ATBookYear bookYearNullable = getBookYears().get(name);
        return Optional.ofNullable(bookYearNullable);
    }

    private Optional<ATBookPeriod> findPeriodByName(ATBookYear bookYear, String name) {
        List<ATBookPeriod> bookYearPeriods = getBookPeriods().get(bookYear);
        return bookYearPeriods.stream()
                .filter(p -> p.getName().equals(name))
                .findAny();
    }

    private Optional<ATAccount> findAccountByCode(String code) {
        ATAccount accountNullable = getAccounts().get(code);
        return Optional.ofNullable(accountNullable);
    }

    private Optional<ATThirdParty> findThirdPartyByIdAndType(String id, ATThirdPartyType type) {
        String typedThirdPartyId = this.getTypedThirdPartyId(id, type);
        ATThirdParty thirdPartyNullable = getThirdParties().get(typedThirdPartyId);
        return Optional.ofNullable(thirdPartyNullable);
    }

    private String getTypedThirdPartyId(String id, ATThirdPartyType type) {
        return MessageFormat.format("{0}::{1}", type, id);
    }

    private String getTypedThirdPartyId(ATThirdParty thirdParty) {
        String id = thirdParty.getId();
        ATThirdPartyType type = thirdParty.getTypeOptional().orElse(ATThirdPartyType.CLIENT);
        return getTypedThirdPartyId(id, type);
    }


    private Map<String, ATBookYear> getBookYears() {
        if (bookYears == null) {
            readBookYears();
        }
        return bookYears;
    }

    private Map<ATBookYear, List<ATBookPeriod>> getBookPeriods() {
        if (bookPeriods == null) {
            readPeriods();
        }
        return this.bookPeriods;
    }


    private Map<String, ATAccount> getAccounts() {
        if (accounts == null) {
            readAccounts();
        }
        return accounts;
    }


    private Map<String, ATThirdParty> getThirdParties() {
        if (thirdParties == null) {
            readThirdParties();
        }
        return thirdParties;
    }

    private void readPeriods() {
        this.bookPeriods = streamPeriods()
                .collect(Collectors.groupingBy(ATBookPeriod::getBookYear));
    }

    private void readBookYears() {
        this.bookYears = streamBookYears()
                .collect(Collectors.toMap(
                        ATBookYear::getName,
                        Function.identity()
                ));
    }


    private void readAccounts() {
        this.accounts = streamAccounts()
                .collect(Collectors.toMap(
                        ATAccount::getCode,
                        Function.identity()
                ));
    }


    private void readThirdParties() {
        this.thirdParties = streamThirdParties()
                .collect(Collectors.toMap(
                        this::getTypedThirdPartyId,
                        Function.identity()
                ));
    }


    private AccountBalance resetAccountBalance(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode) {
        LocalDate date = accountingEntry.getDate();
        BigDecimal amount = accountingEntry.getAmount();

        // For a single period opening, we might have multiple entries for a single account: one debit, one credit for instance.
        BigDecimal newBalance = getYearlyAdjustedAccountBalanceOptional(accountingEntry, balancesPerAccountCode)
                .filter(balance -> isSamePeriod(balance, accountingEntry))
                .map(AccountBalance::getBalance)
                .map(amount::add)
                .orElse(amount);

        AccountBalance accountBalance = setAccountBalance(accountingEntry, balancesPerAccountCode, date, newBalance);
        return accountBalance;
    }


    private AccountBalance updateAccountBalanceAfterAccountingEntry(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode) {
        Optional<AccountBalance> yearlyAdjustedAccountBalanceOptional = getYearlyAdjustedAccountBalanceOptional(accountingEntry, balancesPerAccountCode);
        return updateAccountBalanceAfterAccountingEntry(accountingEntry, balancesPerAccountCode, yearlyAdjustedAccountBalanceOptional);
    }

    private AccountBalance updateAccountBalanceAfterAccountingEntry(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode, Optional<AccountBalance> currentBalanceOptional) {
        BigDecimal entryAmount = accountingEntry.getAmount();
        LocalDate date = accountingEntry.getDate();
        BigDecimal newBalance = currentBalanceOptional
                .map(AccountBalance::getBalance)
                .map(entryAmount::add)
                .orElse(entryAmount);
        AccountBalance newAccountBalance = setAccountBalance(accountingEntry, balancesPerAccountCode, date, newBalance);
        return newAccountBalance;
    }

    private Optional<AccountBalance> getYearlyAdjustedAccountBalanceOptional(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode) {
        ATAccount account = accountingEntry.getAccount();
        String accountCode = account.getCode();
        boolean yearlyBalanceReset = account.isYearlyBalanceReset();
        AccountBalance accountBalanceNullable = balancesPerAccountCode.get(accountCode);
        Optional<AccountBalance> accountBalanceOptional = Optional.ofNullable(accountBalanceNullable);

        if (!yearlyBalanceReset) {
            return accountBalanceOptional;
        } else {
            return accountBalanceOptional
                    .map(accountBalance -> getResettedBalanceOnBookYearChange(accountBalance, accountingEntry));
        }
    }

    private Optional<AccountBalance> getResettedBalanceOnBookYearChange(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode) {
        ATAccount account = accountingEntry.getAccount();
        String accountCode = account.getCode();
        AccountBalance curBalanceNullable = balancesPerAccountCode.get(accountCode);
        Optional<AccountBalance> accountBalanceOptional = Optional.ofNullable(curBalanceNullable);
        return accountBalanceOptional
                .map(balance -> getResettedBalanceOnBookYearChange(balance, accountingEntry));
    }

    private AccountBalance getResettedBalanceOnBookYearChange(AccountBalance accountBalance, ATAccountingEntry accountingEntry) {
        ATAccount account = accountBalance.getAccount();
        if (isSameBookYear(accountBalance, accountingEntry)) {
            return accountBalance;
        } else {
            LocalDate date = accountingEntry.getDate();
            ATBookPeriod bookPeriod = accountingEntry.getBookPeriod();
            return createAccountBalance(account, date, bookPeriod, ZERO_EURO);
        }
    }


    private boolean isSamePeriod(AccountBalance accountBalance, ATAccountingEntry accountingEntry) {
        ATBookPeriod balancePeriod = accountBalance.getPeriod();
        ATBookPeriod entryPeriod = accountingEntry.getBookPeriod();
        return balancePeriod.equals(entryPeriod);
    }


    private boolean isSameBookYear(AccountBalance accountBalance, ATAccountingEntry accountingEntry) {
        ATBookPeriod balancePeriod = accountBalance.getPeriod();
        ATBookYear balanceBookYear = balancePeriod.getBookYear();
        ATBookPeriod entryPeriod = accountingEntry.getBookPeriod();
        ATBookYear entryBookyear = entryPeriod.getBookYear();
        return balanceBookYear.equals(entryBookyear);
    }

    private AccountBalance createAccountBalance(ATAccount atAccount, LocalDate date, ATBookPeriod bookPeriod, BigDecimal newBalanceAmount) {
        AccountBalance newBalance = new AccountBalance();
        newBalance.setAccount(atAccount);
        newBalance.setBalance(newBalanceAmount);
        newBalance.setPeriod(bookPeriod);
        newBalance.setDate(date);

        return newBalance;
    }

    private AccountBalance setAccountBalance(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode, LocalDate date, BigDecimal amount) {
        ATBookPeriod bookPeriod = accountingEntry.getBookPeriod();
        ATAccount account = accountingEntry.getAccount();
        String accountCode = account.getCode();

        AccountBalance accountBalance = createAccountBalance(account, date, bookPeriod, amount);
        balancesPerAccountCode.put(accountCode, accountBalance);
        return accountBalance;
    }


    private <T> Stream<T> streamOptional(Optional<T> optionalValue) {
        return optionalValue.map(Stream::of)
                .orElseGet(Stream::empty);
    }
}
