package be.valuya.bob.core;

import be.valuya.advantaje.core.AdvantajeService;
import be.valuya.bob.core.util.print.BobThePrinter;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

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


    public Stream<BobPeriod> readPeriods(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, "ac_period");
        BobPeriodRecordReader bobPeriodRecordReader = new BobPeriodRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(bobPeriodRecordReader::readPeriod);
    }

    public Stream<BobCompany> readCompanies(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, "ac_compan");
        BobCompanyRecordReader bobCompanyRecordReader = new BobCompanyRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(bobCompanyRecordReader::readCompany);
    }

    public Stream<BobAccount> readAccounts(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, "ac_accoun");
        BobAccountRecordReader accountRecordReader = new BobAccountRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(accountRecordReader::readAccount);
    }

    public Stream<BobAccountingEntry> readAccountingEntries(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, "ac_ahisto");
        BobAccountingEntryRecordReader accountingEntryRecordReader = new BobAccountingEntryRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(accountingEntryRecordReader::readEntry);
    }

    public static void main(String... args) {
        Path baseFolderPath = Paths.get("/home/cghislai/dev/valuya/gestemps/res/bob-data/APIZMEOData/");
        BobFileConfiguration bobFileConfiguration = new BobFileConfiguration(baseFolderPath);

        BobTheTinker bobTheTinker = new BobTheTinker();

        bobTheTinker.readCompanies(bobFileConfiguration)
                .forEach(BobThePrinter::printCompany);
    }

}
