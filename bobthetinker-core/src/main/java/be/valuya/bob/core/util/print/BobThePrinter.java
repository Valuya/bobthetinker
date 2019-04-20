package be.valuya.bob.core.util.print;

import be.valuya.accountingtroll.domain.ATAccount;
import be.valuya.accountingtroll.domain.ATAccountingEntry;
import be.valuya.accountingtroll.domain.ATBookPeriod;
import be.valuya.accountingtroll.domain.ATBookYear;
import be.valuya.accountingtroll.domain.ATDocument;
import be.valuya.accountingtroll.domain.ATThirdParty;
import be.valuya.accountingtroll.event.BalanceChangeEvent;
import be.valuya.bob.core.domain.BobCompany;
import be.valuya.bob.core.domain.BobPeriod;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;

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
        ATAccount account = accountingEntry.getAccount();
        String accountStr = account.getName();
        String accountCode = account.getCode();
        String dbkCode = accountingEntry.getDbkCode();
        String periodName = accountingEntry.getBookPeriod().getName();
        String yearName = accountingEntry.getBookPeriod().getBookYear().getName();
        int docNumber = accountingEntry.getDocNumber();
        String docNumberString = Integer.toString(docNumber);
        String docPresentString = accountingEntry.getDocumentOptional()
                .map(a -> "[doc]")
                .orElse("[ - ]");

        String message = MessageFormat.format("accounting entry: [{4} {7}] {0} ({5} {6}) {8}, account {3} ({2}), {1} €",
                date, amount, accountStr, accountCode, dbkCode, periodName, yearName, docNumberString, docPresentString);
        System.out.println(message);
    }

    public void printDocument(ATDocument document) {
        String id = document.getId();
        ATBookPeriod bookPeriod = document.getBookPeriod();
        String dbkCode = document.getDbkCode();
        int docNumber = document.getDocNumber();

        String docNumberString = Integer.toString(docNumber);
        String periodName = bookPeriod.getName();
        String yearName = bookPeriod.getBookYear().getName();

        String message = MessageFormat.format("document: [{0} {1}] ({2} {3}) {4}",
                dbkCode, docNumberString, periodName, yearName, id);
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
