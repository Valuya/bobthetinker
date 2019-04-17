package be.valuya.bob.core;

import be.valuya.advantaje.core.AdvantajeService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
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

    public static final String ACCOUNT_TABLE_NAME = "ac_accoun";
    public static final String PERIOD_TABLE_NAME = "ac_period";
    public static final String COMPANY_TABLE_NAME = "ac_compan";
    public static final String ACCOUNT_HISTORY_TABLE_NAME = "ac_ahisto";
    public static final String COMPANY_HISTORY_TABLE_NAME = "ac_chisto";

    private static Logger LOGGER = Logger.getLogger(BobTheTinker.class.getName());

    private AdvantajeService advantajeService = new AdvantajeService();
    private BobTheReader bobTheReader = new BobTheReader();


    public Optional<LocalDateTime> getLastAccountModifiactionDate(BobFileConfiguration fileConfiguration) {
        try {
            Path tableFilePath = bobTheReader.getTableFilePath(fileConfiguration, ACCOUNT_TABLE_NAME);
            FileTime lastModifiedTime = Files.getLastModifiedTime(tableFilePath);
            Instant lastModifiedInstant = lastModifiedTime.toInstant();
            LocalDateTime dateTime = LocalDateTime.from(lastModifiedInstant);
            return Optional.of(dateTime);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public Stream<BobPeriod> readPeriods(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, PERIOD_TABLE_NAME);
        BobPeriodRecordReader bobPeriodRecordReader = new BobPeriodRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(bobPeriodRecordReader::readPeriod);
    }

    public Stream<BobCompany> readCompanies(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, COMPANY_TABLE_NAME);
        BobCompanyRecordReader bobCompanyRecordReader = new BobCompanyRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(bobCompanyRecordReader::readCompany);
    }

    public Stream<BobAccount> readAccounts(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, ACCOUNT_TABLE_NAME);
        BobAccountRecordReader accountRecordReader = new BobAccountRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(accountRecordReader::readAccount);
    }

    public Stream<BobAccountHistoryEntry> readAccountHistoryEntries(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, ACCOUNT_HISTORY_TABLE_NAME);
        BobAccountHistoryEntryRecordReader accountingEntryRecordReader = new BobAccountHistoryEntryRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(accountingEntryRecordReader::readEntry);
    }

    public Stream<BobCompanyHistoryEntry> readCompanyHistoryEntries(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, COMPANY_HISTORY_TABLE_NAME);
        BobCompanyHistoryEntryRecordReader recordReader = new BobCompanyHistoryEntryRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(recordReader::readEntry);
    }

}
