package be.valuya.bob.core.api.troll;

import be.valuya.accountingtroll.AccountingEventListener;
import be.valuya.accountingtroll.event.ArchiveFileNotFoundIgnoredEvent;
import be.valuya.accountingtroll.event.ArchiveFolderNotFoundIgnoredEvent;
import be.valuya.accountingtroll.event.BalanceChangeEvent;
import be.valuya.bob.core.util.print.BobThePrinter;

public class TestAccountingEventListener implements AccountingEventListener {

    private final BobThePrinter bobThePrinter;

    public TestAccountingEventListener() {
        bobThePrinter = new BobThePrinter();
    }


    @Override
    public void handleBalanceChangeEvent(BalanceChangeEvent balanceChangeEvent) {
        bobThePrinter.printBalanceChangeEvent(balanceChangeEvent);
    }

    @Override
    public void handleArchiveFileNotFoundIgnoredEvent(ArchiveFileNotFoundIgnoredEvent archiveFileNotFoundIgnoredEvent) {

    }

    @Override
    public void handleArchiveFolderNotFoundIgnoredEvent(ArchiveFolderNotFoundIgnoredEvent archiveFolderNotFoundIgnoredEvent) {

    }
}
