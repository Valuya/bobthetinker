package be.valuya.bob.core.api.troll;

import be.valuya.bob.core.BobFileConfiguration;
import be.valuya.bob.core.BobSession;
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
                .forEach(this::debug);
    }

    @Test
    public void testStreamBookYears() {
        bobAccountingManager.streamBookYears()
                .forEach(this::debug);
    }

    @Test
    public void testStreamPeriods() {
        bobAccountingManager.streamPeriods()
                .forEach(this::debug);
    }

    @Test
    public void testStreamThirdParties() {
        bobAccountingManager.streamThirdParties()
                .forEach(this::debug);
    }

    @Test
    public void testStreamEntries() {
        bobAccountingManager.streamAccountingEntries()
                .forEach(this::debug);
    }

    private void debug(Object valueObject) {
        System.out.println(valueObject);
    }

}
