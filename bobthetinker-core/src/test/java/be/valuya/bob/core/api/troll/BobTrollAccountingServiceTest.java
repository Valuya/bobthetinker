package be.valuya.bob.core.api.troll;

import be.valuya.bob.core.BobFileConfiguration;
import be.valuya.bob.core.BobSession;
import be.valuya.bob.core.BobTheTinker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(JUnit4.class)
public class BobTrollAccountingServiceTest {


    private BobTrollAccountingService trollSrervice;
    private BobSession session;

    @Before
    public void setup() {
        trollSrervice = new BobTrollAccountingService();
        BobTheTinker theTinker = new BobTheTinker();

        String baseFolderLocation = System.getProperty("bob.test.folder");

        Path baseFolderPath = Paths.get(baseFolderLocation);
        BobFileConfiguration fileConfiguration = new BobFileConfiguration(baseFolderPath);
        session = theTinker.openSession(fileConfiguration);

//        eventHandler = winbooksEvent -> logger.info(winbooksEvent.getMessage());
    }

    @Test
    public void testStreamAccounts() {
        trollSrervice.streamAccounts(session)
                .forEach(this::debug);
    }

    @Test
    public void testStreamBookYears() {
        trollSrervice.streamBookYears(session)
                .forEach(this::debug);
    }

    @Test
    public void testStreamPeriods() {
        trollSrervice.streamPeriods(session)
                .forEach(this::debug);
    }

    @Test
    public void testStreamThirdParties() {
        trollSrervice.streamThirdParties(session)
                .forEach(this::debug);
    }

    @Test
    public void testStreamEntries() {
        trollSrervice.streamAccountingEntries(session)
                .forEach(this::debug);
    }

    private void debug(Object valueObject) {
        System.out.println(valueObject);
    }

}
