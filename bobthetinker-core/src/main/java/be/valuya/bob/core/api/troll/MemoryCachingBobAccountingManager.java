package be.valuya.bob.core.api.troll;

import be.valuya.accountingtroll.AccountingEventListener;
import be.valuya.accountingtroll.AccountingManager;
import be.valuya.accountingtroll.domain.Account;
import be.valuya.accountingtroll.domain.AccountingEntry;
import be.valuya.accountingtroll.domain.BookPeriod;
import be.valuya.accountingtroll.domain.BookYear;
import be.valuya.accountingtroll.domain.ThirdParty;
import be.valuya.accountingtroll.domain.ThirdPartyType;
import be.valuya.bob.core.BobAccount;
import be.valuya.bob.core.BobAccountingEntry;
import be.valuya.bob.core.BobCompany;
import be.valuya.bob.core.BobException;
import be.valuya.bob.core.BobFileConfiguration;
import be.valuya.bob.core.BobPeriod;
import be.valuya.bob.core.BobTheTinker;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemoryCachingBobAccountingManager implements AccountingManager {

    private final BobTheTinker bobTheTinker;

    private final BobFileConfiguration bobFileConfiguration;
    private Map<String, BookYear> bookYears;
    private Map<BookYear, List<BookPeriod>> bookPeriods;
    private Map<String, Account> accounts;
    private Map<String, ThirdParty> thirdParties;

    public MemoryCachingBobAccountingManager(BobFileConfiguration bobFileConfiguration) {
        bobTheTinker = new BobTheTinker();
        this.bobFileConfiguration = bobFileConfiguration;
    }

    @Override
    public Stream<Account> streamAccounts() {
        return bobTheTinker.readAccounts(bobFileConfiguration)
                .map(bobAccount -> this.convertToTrollAccount(bobAccount));
    }


    @Override
    public Stream<BookYear> streamBookYears() {
        Comparator<BobPeriod> comparator = Comparator.comparing(BobPeriod::getfYear)
                .thenComparing(BobPeriod::getYear)
                .thenComparing(BobPeriod::getMonth);
        // Seems periods are grouped by the 'fYear' field which can span more than 1 civil year.
        return bobTheTinker.readPeriods(bobFileConfiguration)
                .sorted(comparator)
                .collect(Collectors.groupingBy(BobPeriod::getfYear))
                .entrySet()
                .stream()
                .map(entry -> this.convertToTrollBookYear(entry.getKey(), entry.getValue()));
    }


    @Override
    public Stream<BookPeriod> streamPeriods() {
        return bobTheTinker.readPeriods(bobFileConfiguration)
                .filter(this::isValidPeriod)
                .map(period -> this.convertToTrollPeriod(period));
    }


    @Override
    public Stream<ThirdParty> streamThirdParties() {
        return bobTheTinker.readCompanies(bobFileConfiguration)
                .filter(this::isValidCompany)
                .map(company -> this.convertToTrollThirdParty(company));
    }


    @Override
    public Stream<AccountingEntry> streamAccountingEntries(AccountingEventListener accountingEventListener) {
        return bobTheTinker.readAccountingEntries(bobFileConfiguration)
                .map(entry -> this.convertToTrollAccountingEntry(entry));
    }

    private boolean isValidCompany(BobCompany bobCompany) {
        return bobCompany.getcName1Optional().isPresent();
    }

    private boolean isValidPeriod(BobPeriod bobPeriod) {
        // There is a wildcard period
        return bobPeriod.getMonth() > 0;
    }

    private Account convertToTrollAccount(BobAccount bobAccount) {
        String accountNumber = bobAccount.getAid();
        String name = bobAccount.getHeading1Optional()
                .orElse("-");

        Account account = new Account();
        account.setCode(accountNumber);
        account.setName(name);
        return account;
    }

    private BookYear convertToTrollBookYear(String fYear, List<BobPeriod> periods) {
        BobPeriod firstPeriod = periods.stream()
                .filter(this::isValidPeriod)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        BobPeriod lastPeriod = periods.get(periods.size() - 1);
        LocalDate periodStartDate = getPeriodStartDate(firstPeriod);
        LocalDate periodEndDate = getPeriodEndDate(lastPeriod);

        BookYear bookYear = new BookYear();
        bookYear.setName(fYear);
        bookYear.setStartDate(periodStartDate);
        bookYear.setEndDate(periodEndDate);

        return bookYear;
    }


    private BookPeriod convertToTrollPeriod(BobPeriod bobPeriod) {
        String yearName = bobPeriod.getfYear();
        BookYear bookYear = findBookYearByName(yearName)
                .orElseThrow(() -> new BobException("No book year matching " + yearName));
        String periodShortName = bobPeriod.getLabel();
        LocalDate periodStartDate = getPeriodStartDate(bobPeriod);
        LocalDate periodEndDate = getPeriodEndDate(bobPeriod);

        BookPeriod bookPeriod = new BookPeriod();
        bookPeriod.setBookYear(bookYear);
        bookPeriod.setName(periodShortName);
        bookPeriod.setStartDate(periodStartDate);
        bookPeriod.setEndDate(periodEndDate);
        return bookPeriod;
    }


    private ThirdParty convertToTrollThirdParty(BobCompany bobCompany) {
        // A single company for both client/suppliers, while a thirdparty has a single purpose
        Optional<String> vatNumberOptional = bobCompany.getcVatNumberOptional()
                .map(Optional::of)
                .orElseGet(bobCompany::getsVatNumberOptional);
        Optional<String> vatCat = bobCompany.getcVatCategoryOptional();

        ThirdParty thirdParty = createBaseThirdParty(bobCompany);
        thirdParty.setType(ThirdPartyType.CLIENT);
        thirdParty.setVatNumber(vatNumberOptional.orElse(null));
        thirdParty.setVatCode(vatCat.orElse(null));
        return thirdParty;
    }

    private ThirdParty createBaseThirdParty(BobCompany bobCompany) {
        String id = bobCompany.getcId();
        Optional<String> fullName = bobCompany.getcName1Optional();
        Optional<String> address = bobCompany.getcAddress1Optional();
        Optional<String> zipCode = bobCompany.getcZipCodeOptional();
        Optional<String> city = bobCompany.getcLocalityOptional();
        Optional<String> countryCode = bobCompany.getcCountryCodeOptional();
        Optional<String> phoneNumber = bobCompany.getcPhoneNumberOptional();
        Optional<String> bankAccount = bobCompany.getcBankNumberOptional();
        Optional<String> languageOptional = bobCompany.getcLanguageOptional();

        ThirdParty baseThirdParty = new ThirdParty();
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

    private AccountingEntry convertToTrollAccountingEntry(BobAccountingEntry entry) {
        BookYear bookYear = entry.getHfyearOptional()
                .flatMap(this::findBookYearByName)
                .orElseThrow(() -> new BobException("No book year found with name " + entry.getHfyearOptional()));

        BookPeriod bookPeriod = listYearPeriods(bookYear).stream()
                .filter(p -> this.isSamePeriod(p, entry))
                .findAny()
                .orElseThrow(() -> new BobException("No period found in entry for book year " + bookYear));

        BigDecimal amount = entry.getHamountOptional().orElseThrow(() -> new BobException("No amount for entry"));
        // TODO: only a vat code in entries. Needs to lookup another table?

        Optional<String> accountNumber = entry.getCntrprtaccOptional();
        Optional<Account> accountOptional = accountNumber.flatMap(number -> findAccountByCode(number));

        Optional<String> thirdPartyName = entry.getHcussupOptional();
        Optional<ThirdParty> thirdPartyOptional = thirdPartyName.flatMap(name -> findThirdPartyById(name));


        LocalDate entryDate = entry.getHdocdateOptional().orElseThrow(() -> new BobException("No date for entry"));
        Optional<LocalDate> documentDateOptional = entry.getHdocdateOptional();
        Optional<LocalDate> dueDateOptional = entry.getHduedateOptional();
        Optional<String> commentOptional = entry.getHremOptional();

        AccountingEntry accountingEntry = new AccountingEntry();
        accountingEntry.setBookPeriod(bookPeriod);
        accountingEntry.setDate(entryDate);
        accountingEntry.setAmount(amount);
        accountingEntry.setVatRate(BigDecimal.ZERO); //FIXME --> not necessarily part of AccountingEntry
        accountingEntry.setBalance(BigDecimal.ZERO); //FIXME --> not necessarily part of AccountingEntry

        accountingEntry.setAccountOptional(accountOptional);
        accountingEntry.setThirdPartyOptional(thirdPartyOptional);
        accountingEntry.setDocumentDateOptional(documentDateOptional);
        accountingEntry.setDueDateOptional(dueDateOptional);
        accountingEntry.setCommentOptional(commentOptional);

        return accountingEntry;
    }

    private boolean isSamePeriod(BookPeriod bookPeriod, BobAccountingEntry accountingEntry) {
        int year = accountingEntry.getHyearOptional().orElseThrow(() -> new BobException("No year set in entry"));
        int month = accountingEntry.getHmonthOptional().orElseThrow(() -> new BobException("No month set in entry"));
        if (month == 0) {
            // FIXME: openings are set to the wildcard period **/2018, with month index 0
            return true; // use the first period
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


    public List<BookYear> listBookYears() {
        return new ArrayList<>(getBookYears().values());
    }

    public List<BookPeriod> listPeriods() {
        return getBookPeriods().values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<BookPeriod> listYearPeriods(BookYear bookYear) {
        List<BookPeriod> periodsNullable = getBookPeriods().get(bookYear);
        return Optional.ofNullable(periodsNullable).orElseGet(ArrayList::new);
    }

    public Optional<BookYear> findBookYearByName(String name) {
        BookYear bookYearNullable = getBookYears().get(name);
        return Optional.ofNullable(bookYearNullable);
    }

    public Optional<BookPeriod> findPeriodByName(BookYear bookYear, String name) {
        List<BookPeriod> bookYearPeriods = getBookPeriods().get(bookYear);
        return bookYearPeriods.stream()
                .filter(p -> p.getName().equals(name))
                .findAny();
    }

    public Optional<Account> findAccountByCode(String code) {
        Account accountNullable = getAccounts().get(code);
        return Optional.ofNullable(accountNullable);
    }

    public Optional<ThirdParty> findThirdPartyById(String id) {
        ThirdParty thirdPartyNullable = getThirdParties().get(id);
        return Optional.ofNullable(thirdPartyNullable);
    }


    private Map<String, BookYear> getBookYears() {
        if (bookYears == null) {
            readBookYears();
        }
        return bookYears;
    }

    private Map<BookYear, List<BookPeriod>> getBookPeriods() {
        if (bookPeriods == null) {
            readPeriods();
        }
        return this.bookPeriods;
    }


    private Map<String, Account> getAccounts() {
        if (accounts == null) {
            readAccounts();
        }
        return accounts;
    }


    private Map<String, ThirdParty> getThirdParties() {
        if (thirdParties == null) {
            readThirdParties();
        }
        return thirdParties;
    }

    private void readPeriods() {
        this.bookPeriods = streamPeriods()
                .collect(Collectors.groupingBy(BookPeriod::getBookYear));
    }

    private void readBookYears() {
        this.bookYears = streamBookYears()
                .collect(Collectors.toMap(
                        BookYear::getName,
                        Function.identity()
                ));
    }


    private void readAccounts() {
        this.accounts = streamAccounts()
                .collect(Collectors.toMap(
                        Account::getCode,
                        Function.identity()
                ));
    }


    private void readThirdParties() {
        this.thirdParties = streamThirdParties()
                .collect(Collectors.toMap(
                        ThirdParty::getId,
                        Function.identity()
                ));
    }
}
