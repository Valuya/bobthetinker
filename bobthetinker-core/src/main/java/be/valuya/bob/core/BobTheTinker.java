package be.valuya.bob.core;

import be.valuya.advantaje.core.AdvantajeService;
import be.valuya.bob.core.util.print.BobThePrinter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Utility to access Sage BOB proprietary data.
 * <br/>
 * <br/>
 * <p>
 * Tinker
 * /ˈtɪŋkə/
 * noun
 * 1.
 * (especially in former times) a person who makes a living by travelling from place to place mending pans and other metal utensils.
 * 2.
 * INFORMAL•BRITISH
 * a mischievous child.
 * "little tinkers, we were"
 */
public class BobTheTinker {

    private static Logger LOGGER = Logger.getLogger(BobTheTinker.class.getName());

    private AdvantajeService advantajeService = new AdvantajeService();
    private BobTheReader bobTheReader = new BobTheReader();

    public BobSession openSession(BobFileConfiguration bobFileConfiguration) {
        List<BobPeriod> bobPeriods = readPeriods(bobFileConfiguration);
        return new BobSession(bobFileConfiguration, bobPeriods);
    }

    public List<BobPeriod> readPeriods(BobFileConfiguration bobFileConfiguration) {
        try (InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, "ac_period")) {
            BobPeriodRecordReader bobPeriodRecordReader = new BobPeriodRecordReader();
            return advantajeService.streamTable(tableInputStream)
                    .map(bobPeriodRecordReader::readPeriod)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new BobException("Cannot read periods.", exception);
        }
    }

    public static void main(String... args) {
        Path baseFolderPath = Paths.get("c:\\dev\\wbdata\\apizmeo-bob");
        BobFileConfiguration bobFileConfiguration = new BobFileConfiguration(baseFolderPath);

        BobTheTinker bobTheTinker = new BobTheTinker();
        BobSession bobSession = bobTheTinker.openSession(bobFileConfiguration);

        bobSession.getBobPeriods()
                .stream()
                .forEach(BobThePrinter::printPeriod);

    }

}
