package be.valuya.bob.core;

import be.valuya.advantaje.core.AdvantajeService;
import be.valuya.bob.core.config.BobFileConfiguration;
import be.valuya.bob.core.domain.BobAccount;
import be.valuya.bob.core.domain.BobAccountHistoryEntry;
import be.valuya.bob.core.domain.BobCompany;
import be.valuya.bob.core.domain.BobCompanyHistoryEntry;
import be.valuya.bob.core.domain.BobDocument;
import be.valuya.bob.core.domain.BobPeriod;
import be.valuya.bob.core.reader.BobAccountHistoryEntryRecordReader;
import be.valuya.bob.core.reader.BobAccountRecordReader;
import be.valuya.bob.core.reader.BobCompanyHistoryEntryRecordReader;
import be.valuya.bob.core.reader.BobCompanyRecordReader;
import be.valuya.bob.core.reader.BobDocumentRecordReader;
import be.valuya.bob.core.reader.BobPeriodRecordReader;
import be.valuya.bob.core.reader.BobTheReader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final String ACCOUNT_TABLE_NAME = "ac_accoun";
    private static final String PERIOD_TABLE_NAME = "ac_period";
    private static final String COMPANY_TABLE_NAME = "ac_compan";
    private static final String ACCOUNT_HISTORY_TABLE_NAME = "ac_ahisto";
    private static final String COMPANY_HISTORY_TABLE_NAME = "ac_chisto";
    private static final String DOCUMENTS_TABLE_NAME = "dm_invdoc";
    private static final String DOCUMENT_RELATIVE_PATH_TEMPLATE = "Sage-box/BOBDOCS/{0}/{1}/{2}/{1} - {0} - {3} - {4}.pdf";

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

    public Stream<BobDocument> readDocuments(BobFileConfiguration bobFileConfiguration) {
        InputStream tableInputStream = bobTheReader.getTableInputStream(bobFileConfiguration, DOCUMENTS_TABLE_NAME);
        BobDocumentRecordReader recordReader = new BobDocumentRecordReader();
        return advantajeService.streamTable(tableInputStream)
                .map(recordReader::readDocument);
    }

    public Path getDocumentPath(BobFileConfiguration fileConfiguration,
                                String bookYear, String dbk, String docNo,
                                int bookPeriodYear, int bookPeriodMonth, LocalDate date) {
        String yearPathName = String.format("%04d", bookPeriodYear);
        String monthPathName = String.format("%02d", bookPeriodMonth);
        String periodPathName = yearPathName + monthPathName;
        DateTimeFormatter dateFileNamePartFormat = DateTimeFormatter.ofPattern("yyMMdd");
        String dateFileNamePartName = date.format(dateFileNamePartFormat);

        String relativePath = MessageFormat.format(DOCUMENT_RELATIVE_PATH_TEMPLATE,
                bookYear, dbk, periodPathName, docNo, dateFileNamePartName);
        Path baseFolderPath = fileConfiguration.getBaseFolderPath();
        return baseFolderPath.resolve(relativePath);
    }
}
