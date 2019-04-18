package be.valuya.bob.core.util.print;

import be.valuya.accountingtroll.domain.ATAccount;
import be.valuya.accountingtroll.domain.ATAccountingEntry;
import be.valuya.accountingtroll.domain.ATBookPeriod;
import be.valuya.accountingtroll.domain.ATBookYear;
import be.valuya.accountingtroll.domain.ATThirdParty;
import be.valuya.accountingtroll.event.BalanceChangeEvent;
import be.valuya.bob.core.BobCompany;
import be.valuya.bob.core.BobPeriod;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Optional;

public class BobThePrinter {

    public static final String ABSENT_PLACEHOLDER = "[-]";

    public void printPeriod(BobPeriod bobPeriod) {
        String label = bobPeriod.getLabel();
        String message = MessageFormat.format("period:{0}: {1}", label, bobPeriod.getStatus());
        System.out.println(message);
    }

    public void printPeriod(ATBookPeriod period) {
        String name = period.getName();
        String message = MessageFormat.format("period: {0}", name);
        System.out.println(message);
    }

    public void printCompany(BobCompany bobCompany) {
        String cid = bobCompany.getcId();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cid);

        bobCompany.getcName1Optional()
                .ifPresent(stringBuilder::append);
        bobCompany.getcName2Optional()
                .ifPresent(stringBuilder::append);

        System.out.println(stringBuilder);
    }

    public void printAccountingEntry(ATAccountingEntry accountingEntry) {
        LocalDate date = accountingEntry.getDate();
        BigDecimal amount = accountingEntry.getAmount();
        Optional<ATAccount> accountOptional = accountingEntry.getAccountOptional();
        String accountStr = accountOptional.map(ATAccount::getName).orElse(ABSENT_PLACEHOLDER);
        String accountCode = accountOptional.map(ATAccount::getCode).orElse(ABSENT_PLACEHOLDER);
        String dbkCode = accountingEntry.getDbkCode();
        String periodName = accountingEntry.getBookPeriod().getName();
        String yearName = accountingEntry.getBookPeriod().getBookYear().getName();
        String message = MessageFormat.format("accounting entry: [{4}] {0} ({5} {6}), account {3} ({2}), {1} €",
                date, amount, accountStr, accountCode, dbkCode, periodName, yearName);
        System.out.println(message);
    }

    public void printAccount(ATAccount account) {
        String name = account.getName();
        System.out.println(name);
    }

    public void printBookYear(ATBookYear bookYear) {
        String name = bookYear.getName();
        System.out.println(name);
    }

    public void printThirdParty(ATThirdParty thirdParty) {
        String fullName = thirdParty.getFullNameOptional()
                .orElse(ABSENT_PLACEHOLDER);
        String message = MessageFormat.format("Third party: {0}", fullName);
        System.out.println(message);
    }

    public void printBalanceChangeEvent(BalanceChangeEvent balanceChangeEvent) {
        ATAccount account = balanceChangeEvent.getAccount();
        BigDecimal newBalance = balanceChangeEvent.getNewBalance();
        String message = MessageFormat.format("Balance change: account {0}, {1}", account, newBalance);
        System.out.println(message);
        balanceChangeEvent.getAccountingEntryOptional()
                .ifPresent(this::printAccountingEntry);
    }
}
