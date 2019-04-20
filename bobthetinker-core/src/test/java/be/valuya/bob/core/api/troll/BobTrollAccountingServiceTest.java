package be.valuya.bob.core.api.troll;

import be.valuya.bob.core.BobFileConfiguration;
import be.valuya.bob.core.test.categories.LocalDev;
import be.valuya.bob.core.util.print.BobThePrinter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.file.Path;
import java.nio.file.Paths;

@Category(LocalDev.class)
@RunWith(JUnit4.class)
public class BobTrollAccountingServiceTest {

    private MemoryCachingBobAccountingManager bobAccountingManager;
    private BobThePrinter bobThePrinter;
    private TestAccountingEventListener eventListener;

    @Before
    public void setup() {
        String baseFolderLocation = System.getProperty("bob.test.folder");
        Path baseFolderPath = Paths.get(baseFolderLocation);

        BobFileConfiguration fileConfiguration = new BobFileConfiguration(baseFolderPath);
        bobAccountingManager = new MemoryCachingBobAccountingManager(fileConfiguration);
        bobThePrinter = new BobThePrinter();
        eventListener = new TestAccountingEventListener();
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
        bobAccountingManager.streamAccountingEntries(eventListener)
                .forEach(bobThePrinter::printAccountingEntry);
    }

}
