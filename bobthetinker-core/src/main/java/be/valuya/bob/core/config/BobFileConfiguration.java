package be.valuya.bob.core.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;

public class BobFileConfiguration {

    private Path baseFolderPath;
    private Charset charset = StandardCharsets.ISO_8859_1;

    private boolean readTablesToMemory;
    private boolean throwOnInvalidRecord;
    private BalanceComputationMode balanceComputationMode;
    private DocumentFileReconciliationMode documentFileReconciliationMode;

    private Optional<LocalDate> bookYearMinStartDateOptional = Optional.empty();
    private Optional<LocalDate> bookYearMaxStartDateOptional = Optional.empty();

    public BobFileConfiguration(Path baseFolderPath) {
        this.baseFolderPath = baseFolderPath;
        balanceComputationMode = BalanceComputationMode.BOOK_YEAR_ENTRIES_ONLY;
        documentFileReconciliationMode = DocumentFileReconciliationMode.LAZILY_LIST_FOLDER_CONTENT;
        throwOnInvalidRecord = true;
    }

    public Path getBaseFolderPath() {
        return baseFolderPath;
    }

    public void setBaseFolderPath(Path baseFolderPath) {
        this.baseFolderPath = baseFolderPath;
    }


    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public boolean isReadTablesToMemory() {
        return readTablesToMemory;
    }

    public void setReadTablesToMemory(boolean readTablesToMemory) {
        this.readTablesToMemory = readTablesToMemory;
    }

    public BalanceComputationMode getBalanceComputationMode() {
        return balanceComputationMode;
    }

    public void setBalanceComputationMode(BalanceComputationMode balanceComputationMode) {
        this.balanceComputationMode = balanceComputationMode;
    }

    public DocumentFileReconciliationMode getDocumentFileReconciliationMode() {
        return documentFileReconciliationMode;
    }

    public void setDocumentFileReconciliationMode(DocumentFileReconciliationMode documentFileReconciliationMode) {
        this.documentFileReconciliationMode = documentFileReconciliationMode;
    }

    public boolean isThrowOnInvalidRecord() {
        return throwOnInvalidRecord;
    }

    public void setThrowOnInvalidRecord(boolean throwOnInvalidRecord) {
        this.throwOnInvalidRecord = throwOnInvalidRecord;
    }

    public Optional<LocalDate> getBookYearMinStartDateOptional() {
        return bookYearMinStartDateOptional;
    }

    public void setBookYearMinStartDate(LocalDate bookYearMinStartDate) {
        this.bookYearMinStartDateOptional = Optional.of(bookYearMinStartDate);
    }

    public Optional<LocalDate> getBookYearMaxStartDateOptional() {
        return bookYearMaxStartDateOptional;
    }

    public void setBookYearMaxStartDate(LocalDate bookYearMaxStartDate) {
        this.bookYearMaxStartDateOptional = Optional.of(bookYearMaxStartDate);
    }
}
