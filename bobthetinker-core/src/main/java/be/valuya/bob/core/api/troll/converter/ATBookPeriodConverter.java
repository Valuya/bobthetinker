package be.valuya.bob.core.api.troll.converter;

import be.valuya.accountingtroll.domain.ATBookPeriod;
import be.valuya.accountingtroll.domain.ATBookYear;
import be.valuya.accountingtroll.domain.ATPeriodType;
import be.valuya.bob.core.api.troll.AccountingManagerCache;
import be.valuya.bob.core.domain.BobException;
import be.valuya.bob.core.domain.BobPeriod;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ATBookPeriodConverter {
    private AccountingManagerCache accountingManagerCache;

    public ATBookPeriodConverter(AccountingManagerCache accountingManagerCache) {
        this.accountingManagerCache = accountingManagerCache;
    }

    public ATBookPeriod convertToTrollPeriod(BobPeriod bobPeriod) {
        Map<String, ATBookYear> bookYears = accountingManagerCache.getBookYears();

        String yearName = bobPeriod.getfYear();
        ATBookYear atBookYearNullable = bookYears.get(yearName);
        ATBookYear bookYear = Optional.ofNullable(atBookYearNullable)
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

    public ATBookYear convertToTrollBookYear(String fYear, List<BobPeriod> periods) {
        BobPeriod firstPeriod = periods.stream()
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

}
