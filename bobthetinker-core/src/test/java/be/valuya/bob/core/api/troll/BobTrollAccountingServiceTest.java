package be.valuya.bob.core.api.troll;

import be.valuya.accountingtroll.BalanceChangeEvent;
import be.valuya.bob.core.BobFileConfiguration;
import be.valuya.bob.core.BobSession;
import be.valuya.bob.core.util.print.BobThePrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(JUnit4.class)
public class BobTrollAccountingServiceTest {

    private MemoryCachingBobAccountingManager bobAccountingManager;
    private BobSession session;
    private BobThePrinter bobThePrinter;

    @Before
    public void setup() {
        String baseFolderLocation = System.getProperty("bob.test.folder");
        Path baseFolderPath = Paths.get(baseFolderLocation);

        BobFileConfiguration fileConfiguration = new BobFileConfiguration(baseFolderPath);
        bobAccountingManager = new MemoryCachingBobAccountingManager(fileConfiguration);
    }

    @Test
    public void testStreamAccounts() {
        bobAccountingManager.streamAccounts()
                .forEach(bobThePrinter::printAccount);
    }

    @Test
    public void testStreamBookYears() {
        bobAccountingManager.streamBookYears()
                .forEach(bobThePrinter::printBookYear);
    }

    @Test
    public void testStreamPeriods() {
        bobAccountingManager.streamPeriods()
                .forEach(bobThePrinter::printPeriod);
    }

    @Test
    public void testStreamThirdParties() {
        bobAccountingManager.streamThirdParties()
                .forEach(bobThePrinter::printThirdParty);
    }

    @Test
    public void testStreamEntries() {
        bobAccountingManager.streamAccountingEntries(event -> handleEvent(event))
                .forEach(bobThePrinter::printAccountingEntry);
    }

    private void handleEvent(BalanceChangeEvent balanceChangeEvent) {
        bobThePrinter = new BobThePrinter();
        bobThePrinter.printBalanceChangeEvent(balanceChangeEvent);
    }

}
