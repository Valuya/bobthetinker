package be.valuya.bob.core.api.troll;

import be.valuya.accountingtroll.domain.ATAccount;
import be.valuya.accountingtroll.domain.ATAccountingEntry;
import be.valuya.accountingtroll.domain.ATBookPeriod;
import be.valuya.accountingtroll.domain.ATBookYear;
import be.valuya.accountingtroll.domain.ATDocument;
import be.valuya.accountingtroll.domain.ATThirdParty;
import be.valuya.accountingtroll.domain.ATThirdPartyType;
import be.valuya.bob.core.BobTheTinker;
import be.valuya.bob.core.api.troll.converter.ATAccountConverter;
import be.valuya.bob.core.api.troll.converter.ATAcountingEntryConverter;
import be.valuya.bob.core.api.troll.converter.ATBookPeriodConverter;
import be.valuya.bob.core.api.troll.converter.ATDocumentConverter;
import be.valuya.bob.core.api.troll.converter.ATThirdPartyConverter;
import be.valuya.bob.core.config.BobFileConfiguration;
import be.valuya.bob.core.domain.BobException;
import be.valuya.bob.core.domain.BobPeriod;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountingManagerCache {

    private Map<String, ATBookYear> bookYearsByName;
    private Map<ATBookYear, List<ATBookPeriod>> bookPeriodsByBookYear;
    private Map<String, ATAccount> accountsByCode;
    private Map<String, ATThirdParty> thirdPartiesById;
    private Map<String, ATDocument> documentsById;
    private List<ATAccountingEntry> accountingEntries;

    private final BobTheTinker bobTheTinker;
    private final BobFileConfiguration bobFileConfiguration;
    private final Validator validator;

    private final ATAccountConverter atAccountConverter;
    private final ATAcountingEntryConverter atAcountingEntryConverter;
    private final ATBookPeriodConverter atBookPeriodConverter;
    private final ATDocumentConverter atDocumentConverter;
    private final ATThirdPartyConverter atThirdPartyConverter;

    public AccountingManagerCache(BobFileConfiguration bobFileConfiguration) {
        this.bobFileConfiguration = bobFileConfiguration;
        bobTheTinker = new BobTheTinker();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        atAccountConverter = new ATAccountConverter();
        atAcountingEntryConverter = new ATAcountingEntryConverter(this);
        atBookPeriodConverter = new ATBookPeriodConverter(this);
        atDocumentConverter = new ATDocumentConverter(this);
        atThirdPartyConverter = new ATThirdPartyConverter();
    }


    public Map<String, ATBookYear> getBookYears() {
        if (bookYearsByName == null) {
            readBookYears();
        }
        return bookYearsByName;
    }

    public ATBookYear getBookYeaOrThrow(String bookYearName) {
        Map<String, ATBookYear> bookYears = getBookYears();
        ATBookYear bookYearNullable = bookYears.get(bookYearName);
        ATBookYear bookYear = Optional.ofNullable(bookYearNullable)
                .orElseThrow(() -> new BobException("No book year found with name " + bookYearName));
        return bookYear;
    }


    public Map<ATBookYear, List<ATBookPeriod>> getBookPeriods() {
        if (bookPeriodsByBookYear == null) {
            readPeriods();
        }
        return this.bookPeriodsByBookYear;
    }


    public Map<String, ATAccount> getAccounts() {
        if (accountsByCode == null) {
            readAccounts();
        }
        return accountsByCode;
    }


    public Map<String, ATThirdParty> getThirdParties() {
        if (thirdPartiesById == null) {
            readThirdParties();
        }
        return thirdPartiesById;
    }

    public String getTypedThirdPartyId(String id, ATThirdPartyType type) {
        return MessageFormat.format("{0}::{1}", type, id);
    }

    public String getTypedThirdPartyId(ATThirdParty thirdParty) {
        String id = thirdParty.getId();
        ATThirdPartyType type = thirdParty.getTypeOptional().orElse(ATThirdPartyType.CLIENT);
        return getTypedThirdPartyId(id, type);
    }

    public Map<String, ATDocument> getDocuments() {
        if (documentsById == null) {
            readDocuments();
        }
        return documentsById;
    }

    public List<ATAccountingEntry> getAccountingEntries() {
        if (accountingEntries == null) {
            readAccountingEntries();
        }
        return accountingEntries;
    }

    private void readPeriods() {
        this.bookPeriodsByBookYear = streamPeriods()
                .collect(Collectors.groupingBy(ATBookPeriod::getBookYear));
    }

    private void readBookYears() {
        this.bookYearsByName = streamBookYears()
                .collect(Collectors.toMap(
                        ATBookYear::getName,
                        Function.identity()
                ));
    }


    private void readAccounts() {
        this.accountsByCode = streamAccounts()
                .collect(Collectors.toMap(
                        ATAccount::getCode,
                        Function.identity()
                ));
    }


    private void readThirdParties() {
        this.thirdPartiesById = streamThirdParties()
                .collect(Collectors.toMap(
                        this::getTypedThirdPartyId,
                        Function.identity()
                ));
    }

    private void readDocuments() {
        this.documentsById = streamDocuments()
                .collect(Collectors.toMap(
                        ATDocument::getId,
                        Function.identity()
                ));
    }

    private void readAccountingEntries() {
        this.accountingEntries = streamAccountingEntries()
                .collect(Collectors.toList());
    }

    private Stream<ATAccount> streamAccounts() {
        return bobTheTinker.readAccounts(bobFileConfiguration)
                .filter(this::isValidRecord)
                .map(atAccountConverter::convertToTrollAccount);
    }

    private Stream<ATBookYear> streamBookYears() {
        // Seems periods are grouped by the 'fYear' field which can span more than 1 civil year.
        return bobTheTinker.readPeriods(bobFileConfiguration)
                .filter(this::isValidRecord)
                .collect(Collectors.groupingBy(BobPeriod::getfYear))
                .entrySet()
                .stream()
                .map(entry -> atBookPeriodConverter.convertToTrollBookYear(entry.getKey(), entry.getValue()));
    }


    private Stream<ATBookPeriod> streamPeriods() {
        return bobTheTinker.readPeriods(bobFileConfiguration)
                .filter(this::isValidRecord)
                .map(atBookPeriodConverter::convertToTrollPeriod);
    }

    private Stream<ATThirdParty> streamThirdParties() {
        return bobTheTinker.readCompanies(bobFileConfiguration)
                .filter(this::isValidRecord)
                .flatMap(atThirdPartyConverter::convertToTrollThirdParties);
    }

    private Stream<ATAccountingEntry> streamAccountingEntries() {
        return bobTheTinker.readAccountHistoryEntries(bobFileConfiguration)
                .filter(this::isValidRecord)
                .map(atAcountingEntryConverter::convertToTrollAccountingEntry);
    }

    private Stream<ATDocument> streamDocuments() {
        return bobTheTinker.readDocuments(bobFileConfiguration)
                .filter(this::isValidRecord)
                .map(atDocumentConverter::convertToTrollDocument);
    }

    private <T> boolean isValidRecord(T record) {
        boolean throwOnInvalidRecord = bobFileConfiguration.isThrowOnInvalidRecord();
        try {
            Set<ConstraintViolation<T>> constraintViolations = validator.validate(record);
            boolean validRecord = constraintViolations.isEmpty();
            if (!validRecord && throwOnInvalidRecord) {
                ConstraintViolationException constraintViolationException = new ConstraintViolationException(constraintViolations);
                throw new BobException("Invalid record: " + record, constraintViolationException);
            }
            return validRecord;
        } catch (Exception e) {
            if (throwOnInvalidRecord) {
                throw new BobException("Could not validate record: " + record, e);
            }
            return false;
        }
    }

}
