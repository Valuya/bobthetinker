package be.valuya.bob.core.api.troll;

import be.valuya.accountingtroll.AccountingService;
import be.valuya.accountingtroll.Session;
import be.valuya.accountingtroll.cache.AccountingCache;
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
import be.valuya.bob.core.BobPeriod;
import be.valuya.bob.core.BobSession;
import be.valuya.bob.core.BobTheTinker;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BobTrollAccountingService implements AccountingService {


    private final BobTheTinker bobTheTinker;

    public BobTrollAccountingService() {
        bobTheTinker = new BobTheTinker();
    }

    @Override
    public Stream<Account> streamAccounts(Session session) {
        BobSession bobSession = checkSession(session);
        return bobTheTinker.readAccounts(bobSession)
                .map(bobAccount -> this.convertToTrollAccount(bobAccount, session));
    }


    @Override
    public Stream<BookYear> streamBookYears(Session session) {
        BobSession bobSession = checkSession(session);
        Comparator<BobPeriod> comparator = Comparator.comparing(BobPeriod::getfYear)
                .thenComparing(BobPeriod::getYear)
                .thenComparing(BobPeriod::getMonth);
        // Seems periods are grouped by the 'fYear' field which can span more than 1 civil year.
        return bobTheTinker.readPeriods(bobSession)
                .sorted(comparator)
                .collect(Collectors.groupingBy(BobPeriod::getfYear))
                .entrySet()
                .stream()
                .map(entry -> this.convertToTrollBookYear(entry.getKey(), entry.getValue(), session));
    }


    @Override
    public Stream<BookPeriod> streamPeriods(Session session) {
        BobSession bobSession = checkSession(session);
        return bobTheTinker.readPeriods(bobSession)
                .filter(this::isValidPeriod)
                .map(period -> this.convertToTrollPeriod(period, session));
    }


    @Override
    public Stream<ThirdParty> streamThirdParties(Session session) {
        BobSession bobSession = checkSession(session);
        return bobTheTinker.readCompanies(bobSession)
                .filter(this::isValidCompany)
                .map(company -> this.convertToTrollThirdParty(company, session));
    }


    @Override
    public Stream<AccountingEntry> streamAccountingEntries(Session session) {
        BobSession bobSession = checkSession(session);
        return bobTheTinker.readAccountingEntries(bobSession)
                .map(entry -> this.convertToTrollAccountingEntry(entry, session));
    }

    private BobSession checkSession(Session trollSession) {
        if (trollSession.getSessionType().equals(BobSession.SESSION_TYPE)) {
            return (BobSession) trollSession;
        } else {
            throw new BobException("Session type mismatch");
        }
    }

    private boolean isValidCompany(BobCompany bobCompany) {
        return bobCompany.getcName1Optional().isPresent();
    }

    private boolean isValidPeriod(BobPeriod bobPeriod) {
        // There is a wildcard period
        return bobPeriod.getMonth() > 0;
    }

    private Account convertToTrollAccount(BobAccount bobAccount, Session session) {
        String accountNumber = bobAccount.getAid();
        String name = bobAccount.getHeading1Optional()
                .orElse("-");

        Account account = new Account();
        account.setCode(accountNumber);
        account.setName(name);
        return account;
    }

    private BookYear convertToTrollBookYear(String fYear, List<BobPeriod> periods, Session session) {
        BobPeriod firstPeriod = periods.stream()
                .filter(this::isValidPeriod)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        BobPeriod lastPeriod = periods.get(periods.size() - 1);
        LocalDate firstDate = getPeriodStartDate(firstPeriod);
        LocalDate lastDateExclusive = getPeriodEndDate(lastPeriod);

        BookYear bookYear = new BookYear();
        bookYear.setName(fYear);
        bookYear.setStartDate(firstDate);
        bookYear.setEndDate(lastDateExclusive);
        return bookYear;
    }


    private BookPeriod convertToTrollPeriod(BobPeriod bobPeriod, Session session) {
        AccountingCache sessionCache = session.getCache();
        String yearName = bobPeriod.getfYear();
        BookYear bookYear = sessionCache.findBookYearByName(this, yearName)
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


    private ThirdParty convertToTrollThirdParty(BobCompany bobCompany, Session session) {
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

    private AccountingEntry convertToTrollAccountingEntry(BobAccountingEntry entry, Session session) {
        AccountingCache sessionCache = session.getCache();

        BookYear bookYear = entry.getHfyearOptional()
                .flatMap(y -> sessionCache.findBookYearByName(this, y))
                .orElseThrow(() -> new BobException("No book year found with name " + entry.getHfyearOptional()));

        BookPeriod bookPeriod = sessionCache.listYearPeriods(this, bookYear).stream()
                .filter(p -> this.isSamePeriod(p, entry))
                .findAny()
                .orElseThrow(() -> new BobException("No period found in entry for book year " + bookYear));

        BigDecimal amount = entry.getHamountOptional().orElseThrow(() -> new BobException("No amount for entry"));
        // TODO: only a vat code in entries. Needs to lookup another table?

        Optional<String> accountNumber = entry.getCntrprtaccOptional();
        Optional<Account> account = accountNumber.flatMap(number -> sessionCache.findAccountByCode(this, number));

        Optional<String> thirdPartyName = entry.getHcussupOptional();
        Optional<ThirdParty> thirdParty = thirdPartyName.flatMap(name -> sessionCache.findThirdPartyById(this, name));


        LocalDate entryDate = entry.getHdocdateOptional().orElseThrow(() -> new BobException("No date for entry"));
        Optional<LocalDate> documentDate = entry.getHdocdateOptional();
        Optional<LocalDate> dueDate = entry.getHduedateOptional();
        Optional<String> comment = entry.getHremOptional();

        AccountingEntry accountingEntry = new AccountingEntry();
        accountingEntry.setBookPeriod(bookPeriod);
        accountingEntry.setDate(entryDate);
        accountingEntry.setAmount(amount);
        accountingEntry.setVatRate(BigDecimal.ZERO); //FIXME
        accountingEntry.setBalance(BigDecimal.ZERO); //FIXME

        account.ifPresent(accountingEntry::setAccount);
        thirdParty.ifPresent(accountingEntry::setThirdParty);
        documentDate.ifPresent(accountingEntry::setDocumentDate);
        dueDate.ifPresent(accountingEntry::setDueDate);
        comment.ifPresent(accountingEntry::setComment);
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

    private LocalDate getPeriodEndDate(BobPeriod lastPeriod) {
        int lastYear = lastPeriod.getYear();
        int lastMonth = lastPeriod.getMonth();
        return LocalDate.now().withYear(lastYear).withMonth(lastMonth).withDayOfMonth(1)
                .plusMonths(1);
    }

    private LocalDate getPeriodStartDate(BobPeriod firstPeriod) {
        int firstYear = firstPeriod.getYear();
        int firstMonth = firstPeriod.getMonth();
        return LocalDate.now().withYear(firstYear).withMonth(firstMonth).withDayOfMonth(1);
    }

}
