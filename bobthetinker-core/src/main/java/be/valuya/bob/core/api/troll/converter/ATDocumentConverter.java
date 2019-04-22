package be.valuya.bob.core.api.troll.converter;

import be.valuya.accountingtroll.domain.ATBookPeriod;
import be.valuya.accountingtroll.domain.ATBookYear;
import be.valuya.accountingtroll.domain.ATDocument;
import be.valuya.accountingtroll.domain.ATPeriodType;
import be.valuya.bob.core.api.troll.AccountingManagerCache;
import be.valuya.bob.core.domain.BobAccountHistoryEntry;
import be.valuya.bob.core.domain.BobDocument;
import be.valuya.bob.core.domain.BobException;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ATDocumentConverter {

    private AccountingManagerCache accountingManagerCache;

    public ATDocumentConverter(AccountingManagerCache accountingManagerCache) {
        this.accountingManagerCache = accountingManagerCache;
    }

    public ATDocument convertToTrollDocument(BobDocument bobDocument) {
        String id = bobDocument.getId();
        String fyear = bobDocument.getFyear();
        String dbk = bobDocument.getDbk();
        int docNo = bobDocument.getDocNo();

        ATBookYear bookYear = accountingManagerCache.getBookYeaOrThrow(fyear);
        ATBookPeriod bookPeriod = getBookPeriod(bobDocument, bookYear);
        String docNumberString = String.format("%d", docNo);

        ATDocument atDocument = new ATDocument();
        atDocument.setId(id);
        atDocument.setBookPeriod(bookPeriod);
        atDocument.setDbkCode(dbk);
        atDocument.setDocumentNumnber(docNumberString);
        return atDocument;
    }

    private ATBookPeriod getBookPeriod(BobDocument document, ATBookYear bookYear) {
        Map<ATBookYear, List<ATBookPeriod>> bookPeriods = accountingManagerCache.getBookPeriods();
        List<ATBookPeriod> periodListNullable = bookPeriods.get(bookYear);
        ATBookPeriod bookPeriod = Optional.ofNullable(periodListNullable)
                .map(List::stream)
                .orElse(Stream.empty())
                .filter(p -> this.isSameBookYearPeriod(p, document))
                .findAny()
                .orElseThrow(() -> new BobException("No period found in entry for book year " + bookYear));
        return bookPeriod;
    }

    private boolean isSameBookYearPeriod(ATBookPeriod bookPeriod, BobDocument document) {
        int docYear = document.getYear();
        int docMonth = document.getMonth();
        return isSamePeriod(bookPeriod, docYear, docMonth);
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
}
