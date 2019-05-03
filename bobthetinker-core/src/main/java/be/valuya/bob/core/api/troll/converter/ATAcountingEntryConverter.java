package be.valuya.bob.core.api.troll.converter;

import be.valuya.accountingtroll.domain.ATAccount;
import be.valuya.accountingtroll.domain.ATAccountingEntry;
import be.valuya.accountingtroll.domain.ATBookPeriod;
import be.valuya.accountingtroll.domain.ATBookYear;
import be.valuya.accountingtroll.domain.ATDocument;
import be.valuya.accountingtroll.domain.ATPeriodType;
import be.valuya.accountingtroll.domain.ATTax;
import be.valuya.accountingtroll.domain.ATThirdParty;
import be.valuya.accountingtroll.domain.ATThirdPartyType;
import be.valuya.bob.core.api.troll.AccountingManagerCache;
import be.valuya.bob.core.domain.BobAccountHistoryEntry;
import be.valuya.bob.core.domain.BobException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ATAcountingEntryConverter {

    private AccountingManagerCache accountingManagerCache;

    public ATAcountingEntryConverter(AccountingManagerCache accountingManagerCache) {
        this.accountingManagerCache = accountingManagerCache;
    }

    public ATAccountingEntry convertToTrollAccountingEntry(BobAccountHistoryEntry entry) {
        String hfyear = entry.getHfyear();
        String dbkCode = entry.getHdbk();
        String accountNumber = entry.getHid();
        BigDecimal amount = entry.getHamount();
        Integer docNumber = entry.getHdocno();
        String docNumberString = String.format("%d", docNumber);
        Integer orderingNumber = entry.getHorderno();
        LocalDate entryDate = entry.getHdocdate();

        ATBookYear bookYear = accountingManagerCache.getBookYeaOrThrow(hfyear);
        ATBookPeriod bookPeriod = getBookPeriod(entry, bookYear);
        ATAccount account = getAccount(accountNumber);
        BigDecimal signedAmount = getSignedAmount(account, amount);
        Optional<ATThirdParty> thirdPartyOptional = getThirdPartyOptional(entry);
        Optional<ATDocument> documentOptional = getDocumentOptional(entry, bookPeriod);

        Optional<LocalDate> documentDateOptional = entry.getHdocdateOptional();
        Optional<LocalDate> dueDateOptional = entry.getHduedateOptional();
        Optional<String> commentOptional = entry.getHremOptional();
        Optional<ATTax> taxOptional = Optional.empty(); //TODO

        ATAccountingEntry accountingEntry = new ATAccountingEntry();
        accountingEntry.setBookPeriod(bookPeriod);
        accountingEntry.setDate(entryDate);
        accountingEntry.setAmount(signedAmount);
        accountingEntry.setDbkCode(dbkCode);
        accountingEntry.setAccount(account);
        accountingEntry.setDocNumber(docNumberString);
        accountingEntry.setOrderingNumber(orderingNumber);

        accountingEntry.setThirdPartyOptional(thirdPartyOptional);
        accountingEntry.setDocumentDateOptional(documentDateOptional);
        accountingEntry.setDueDateOptional(dueDateOptional);
        accountingEntry.setCommentOptional(commentOptional);
        accountingEntry.setTaxOptional(taxOptional);
        accountingEntry.setDocumentOptional(documentOptional);


        // FIXME: side effect
        documentOptional.ifPresent(doc -> doc.setDateOptional(Optional.of(entryDate)));
        return accountingEntry;
    }

    private Optional<ATDocument> getDocumentOptional(BobAccountHistoryEntry entry, ATBookPeriod bookPeriod) {
        Map<String, ATDocument> documents = accountingManagerCache.getDocuments();
        return documents.values()
                .stream()
                .filter(doc -> isSameDocument(doc, entry, bookPeriod))
                .findAny();
    }

    private boolean isSameDocument(ATDocument doc, BobAccountHistoryEntry entry, ATBookPeriod bookPeriod) {
        ATBookPeriod docPeriod = doc.getBookPeriod();
        String docDbk = doc.getDbkCode();
        String docDocNumber = doc.getDocumentNumnber();

        int entryDocNumber = entry.getHdocno();
        String entryDbk = entry.getHdbk();
        String entryDocNumberString = String.format("%d", entryDocNumber);

        return docPeriod.equals(bookPeriod)
                && docDbk.equals(entryDbk)
                && entryDocNumberString.equals(docDocNumber);
    }

    private Optional<ATThirdParty> getThirdPartyOptional(BobAccountHistoryEntry entry) {
        Optional<String> thirdPartyName = entry.getHcussupOptional();
        ATThirdPartyType thirdPartyType = entry.getHcstypeOptional()
                .flatMap(this::parseThirdPartyTypeFromHSType)
                .orElse(ATThirdPartyType.CLIENT);
        return thirdPartyName
                .flatMap(name -> this.getThirdPartyOptional(name, thirdPartyType));
    }

    private boolean isNotEmptyString(String stringValue) {
        return stringValue != null && !stringValue.isEmpty();
    }

    private BigDecimal getSignedAmount(ATAccount account, BigDecimal amount) {
        // We want positive for credit, negative for debit.
        // It seems inversed in the bob tables.
        return amount.negate();
    }

    private Optional<ATThirdParty> getThirdPartyOptional(String name, ATThirdPartyType type) {
        Map<String, ATThirdParty> thirdParties = accountingManagerCache.getThirdParties();
        String typedThirdPartyId = accountingManagerCache.getTypedThirdPartyId(name, type);
        ATThirdParty thirdPartyNullable = thirdParties.get(typedThirdPartyId);
        return Optional.ofNullable(thirdPartyNullable);
    }

    private ATAccount getAccount(String accountNumber) {
        Map<String, ATAccount> accounts = accountingManagerCache.getAccounts();
        ATAccount accountNullable = accounts.get(accountNumber);
        return Optional.ofNullable(accountNullable)
                .orElseThrow(() -> new BobException("No account found with code" + accountNumber));
    }


    private ATBookPeriod getBookPeriod(BobAccountHistoryEntry entry, ATBookYear bookYear) {
        Map<ATBookYear, List<ATBookPeriod>> bookPeriods = accountingManagerCache.getBookPeriods();
        List<ATBookPeriod> periodListNullable = bookPeriods.get(bookYear);
        ATBookPeriod bookPeriod = Optional.ofNullable(periodListNullable)
                .map(List::stream)
                .orElse(Stream.empty())
                .filter(p -> this.isSameBookYearPeriod(p, entry))
                .findAny()
                .orElseThrow(() -> new BobException("No period found in entry for book year " + bookYear));
        return bookPeriod;
    }

    private boolean isSameBookYearPeriod(ATBookPeriod bookPeriod, BobAccountHistoryEntry entry) {
        Integer entryYear = entry.getHyear();
        Integer entryMonth = entry.getHmonth();
        return isSamePeriod(bookPeriod, entryYear, entryMonth);
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

}
