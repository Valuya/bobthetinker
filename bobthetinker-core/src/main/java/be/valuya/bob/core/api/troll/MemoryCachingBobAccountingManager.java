package be.valuya.bob.core.api.troll;

import be.valuya.accountingtroll.AccountingEventListener;
import be.valuya.accountingtroll.AccountingManager;
import be.valuya.accountingtroll.cache.AccountBalanceSpliterator;
import be.valuya.accountingtroll.domain.ATAccount;
import be.valuya.accountingtroll.domain.ATAccountBalance;
import be.valuya.accountingtroll.domain.ATAccountingEntry;
import be.valuya.accountingtroll.domain.ATBookPeriod;
import be.valuya.accountingtroll.domain.ATBookYear;
import be.valuya.accountingtroll.domain.ATDocument;
import be.valuya.accountingtroll.domain.ATPeriodType;
import be.valuya.accountingtroll.domain.ATThirdParty;
import be.valuya.accountingtroll.event.BalanceChangeEvent;
import be.valuya.bob.core.BobTheTinker;
import be.valuya.bob.core.config.BalanceComputationMode;
import be.valuya.bob.core.config.BobFileConfiguration;
import be.valuya.bob.core.config.DocumentFileReconciliationMode;
import be.valuya.bob.core.domain.BobException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MemoryCachingBobAccountingManager implements AccountingManager {

    private static final BigDecimal ZERO_EURO = BigDecimal.ZERO.setScale(3, RoundingMode.UNNECESSARY);
    private static final String DOCUMENT_UPLOAD_PATH_NAME = "Demat";

    private final BobTheTinker bobTheTinker;
    private final BobFileConfiguration bobFileConfiguration;
    private final AccountingManagerCache accountingManagerCache;

    public MemoryCachingBobAccountingManager(BobFileConfiguration bobFileConfiguration) {
        this.bobFileConfiguration = bobFileConfiguration;
        bobTheTinker = new BobTheTinker();
        accountingManagerCache = new AccountingManagerCache(bobFileConfiguration);
    }

    @Override
    public Optional<LocalDateTime> getLastAccountModificationTime() {
        return bobTheTinker.getLastAccountModifiactionDate(bobFileConfiguration);
    }

    @Override
    public Stream<ATAccount> streamAccounts() {
        return accountingManagerCache.getAccounts().values()
                .stream();
    }

    @Override
    public Stream<ATBookYear> streamBookYears() {
        return accountingManagerCache.getBookYears()
                .values()
                .stream();
    }


    @Override
    public Stream<ATBookPeriod> streamPeriods() {
        return accountingManagerCache.getBookPeriods()
                .values()
                .stream()
                .flatMap(List::stream);
    }

    @Override
    public Stream<ATThirdParty> streamThirdParties() {
        return accountingManagerCache.getThirdParties()
                .values()
                .stream();
    }

    @Override
    public Stream<ATAccountingEntry> streamAccountingEntries() {
        return accountingManagerCache.getAccountingEntries().stream()
                .sorted();
    }

    @Override
    public Stream<ATAccountBalance> streamAccountBalances() {
        List<ATBookPeriod> allPeriods = streamPeriods().sorted()
                .collect(Collectors.toList());
        Stream<ATAccountingEntry> entryStream = streamAccountingEntries();
        AccountBalanceSpliterator balanceSpliterator = new AccountBalanceSpliterator(entryStream, allPeriods);

        BalanceComputationMode balanceComputationMode = bobFileConfiguration.getBalanceComputationMode();

        switch (balanceComputationMode) {
            case BOOK_YEAR_ENTRIES_ONLY:
                balanceSpliterator.setIgnoreIntermediatePeriodOpeningEntry(false);
                balanceSpliterator.setResetEveryYear(true);
                break;
            case IGNORE_OPENINGS_FOR_INTERMEDIATE_YEARS:
                balanceSpliterator.setIgnoreIntermediatePeriodOpeningEntry(true);
                balanceSpliterator.setResetEveryYear(false);
                break;
        }

        return StreamSupport.stream(balanceSpliterator, false);
    }

    @Override
    public Stream<ATDocument> streamDocuments() {
        Collection<ATDocument> documents = accountingManagerCache.getDocuments().values();

        DocumentFileReconciliationMode documentFileReconciliationMode = bobFileConfiguration.getDocumentFileReconciliationMode();
        if (documentFileReconciliationMode == DocumentFileReconciliationMode.EAGERLY_STREAM_ENTRIES) {
            // Ensure the converter will set the date field
            accountingManagerCache.getAccountingEntries();
        }

        return documents.stream();
    }

    @Override
    public InputStream streamDocumentContent(ATDocument atDocument) throws Exception {
        Path documentAbsolutePath = getDocumentAbsolutePath(atDocument);
        return Files.newInputStream(documentAbsolutePath);
    }

    @Override
    public void uploadDocument(String documentRelativePathName, InputStream inputStream) throws Exception {
        Path baseFolderPath = bobFileConfiguration.getBaseFolderPath();
        Path documentFullPath = baseFolderPath.resolve(DOCUMENT_UPLOAD_PATH_NAME)
                .resolve(documentRelativePathName);
        Path documentDirectoryPath = documentFullPath.getParent();

        Files.createDirectories(documentDirectoryPath);
        Files.copy(inputStream, documentFullPath, StandardCopyOption.REPLACE_EXISTING);
    }

    private Optional<Path> findDocumentFilePathFromParentDirectory(ATDocument atDocument) throws IOException {
        Path documentDirectoryPath = getDocumentDirectoryPath(atDocument);
        String docNumber = atDocument.getDocumentNumnber();
        String dbkCode = atDocument.getDbkCode();
        ATBookPeriod bookPeriod = atDocument.getBookPeriod();
        ATBookYear bookYear = bookPeriod.getBookYear();
        String bookYearName = bookYear.getName();

        return Files.list(documentDirectoryPath)
                .filter(path -> bobTheTinker.matchesDocumentFilePath(path, bookYearName, dbkCode, docNumber))
                .findAny();
    }

    private Path getDocumentDirectoryPath(ATDocument atDocument) {
        ATBookPeriod bookPeriod = atDocument.getBookPeriod();
        String dbkCode = atDocument.getDbkCode();
        ATBookYear bookYear = bookPeriod.getBookYear();
        String bookYearName = bookYear.getName();
        int periodMonth = bookPeriod.getStartDate().getMonthValue();
        int periodYear = bookPeriod.getStartDate().getYear();

        Path documentPath = bobTheTinker.getDocumentDirectoryPath(bobFileConfiguration,
                bookYearName, dbkCode, periodYear, periodMonth);
        return documentPath;
    }


    private Path getDocumentAbsolutePath(ATDocument atDocument) throws IOException {
        if (atDocument.getDateOptional().isPresent()) {
            return getDatedDocumentFileAbsolutePath(atDocument);
        } else {
            DocumentFileReconciliationMode reconciliationMode = bobFileConfiguration.getDocumentFileReconciliationMode();
            if (reconciliationMode == DocumentFileReconciliationMode.EAGERLY_STREAM_ENTRIES) {
                throw new BobException("Could not reconciliate documenbt date for doc: " + atDocument);
            }

            return findDocumentFilePathFromParentDirectory(atDocument)
                    .orElseThrow(() -> new BobException("Could not find any file matching document " + atDocument));
        }
    }

    private Path getDatedDocumentFileAbsolutePath(ATDocument atDocument) {
        ATBookPeriod bookPeriod = atDocument.getBookPeriod();
        String dbkCode = atDocument.getDbkCode();
        String docNumber = atDocument.getDocumentNumnber();
        ATBookYear bookYear = bookPeriod.getBookYear();
        String bookYearName = bookYear.getName();
        int periodMonth = bookPeriod.getStartDate().getMonthValue();
        int periodYear = bookPeriod.getStartDate().getYear();
        LocalDate date = atDocument.getDateOptional()
                .orElseThrow(() -> new BobException("No document date to build filename"));

        Path documentPath = bobTheTinker.getDocumentPath(bobFileConfiguration,
                bookYearName, dbkCode, docNumber, periodYear, periodMonth, date);
        return documentPath;
    }
}
