package be.valuya.bob.core.util.print;

import be.valuya.accountingtroll.BalanceChangeEvent;
import be.valuya.accountingtroll.domain.Account;
import be.valuya.accountingtroll.domain.AccountingEntry;
import be.valuya.accountingtroll.domain.BookPeriod;
import be.valuya.accountingtroll.domain.BookYear;
import be.valuya.accountingtroll.domain.ThirdParty;
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

    public void printPeriod(BookPeriod period) {
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

    public void printAccountingEntry(AccountingEntry accountingEntry) {
        LocalDate date = accountingEntry.getDate();
        BigDecimal amount = accountingEntry.getAmount();
        Optional<Account> accountOptional = accountingEntry.getAccountOptional();
        String accountStr = accountOptional.map(Account::getName).orElse(ABSENT_PLACEHOLDER);
        String message = MessageFormat.format("accounting entry,: account {3}, {0}, {1} €", date, amount, accountStr);
        System.out.println(message);
    }

    public void printAccount(Account account) {
        String name = account.getName();
        System.out.println(name);
    }

    public void printBookYear(BookYear bookYear) {
        String name = bookYear.getName();
        System.out.println(name);
    }

    public void printThirdParty(ThirdParty thirdParty) {
        String fullName = thirdParty.getFullNameOptional()
                .orElse(ABSENT_PLACEHOLDER);
        String message = MessageFormat.format("Third party: {0}", fullName);
        System.out.println(message);
    }

    public void printBalanceChangeEvent(BalanceChangeEvent balanceChangeEvent) {
        Account account = balanceChangeEvent.getAccount();
        BigDecimal newBalance = balanceChangeEvent.getNewBalance();
        String message = MessageFormat.format("Balance change: account {0}, {1}", account, newBalance);
        System.out.println(message);
        balanceChangeEvent.getAccountingEntryOptional()
                .ifPresent(this::printAccountingEntry);
    }
}
