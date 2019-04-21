package be.valuya.bob.core.api.troll;

import be.valuya.accountingtroll.AccountingEventListener;
import be.valuya.accountingtroll.AccountingManager;
import be.valuya.accountingtroll.domain.ATAccount;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Stream;

public class MemoryCachingBobAccountingManager implements AccountingManager {

    private static final BigDecimal ZERO_EURO = BigDecimal.ZERO.setScale(3, RoundingMode.UNNECESSARY);

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
    public Stream<ATAccountingEntry> streamAccountingEntries(AccountingEventListener accountingEventListener) {
        // Balance cache
        Map<String, AccountBalance> balancesPerAccountCode = new ConcurrentSkipListMap<>();

        Stream<ATAccountingEntry> entryStream = accountingManagerCache.getAccountingEntries()
                .stream()
                .sorted()
                .flatMap(entry -> streamWithBalanceCheck(entry, balancesPerAccountCode, accountingEventListener));

        // Ensure all accounts with the yealryReset flag will fire a balance change event if relevant.
        streamAccounts().filter(ATAccount::isYearlyBalanceReset)
                .map(account -> this.createYearlyResetBalanceChangeEventOptional(account, balancesPerAccountCode))
                .flatMap(this::streamOptional)
                .forEach(accountingEventListener::handleBalanceChangeEvent);

        return entryStream;
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
        if (atDocument.getDateOptional().isPresent()) {
            Path documentPath = getDocumentFilePath(atDocument);
            return Files.newInputStream(documentPath);
        } else {
            DocumentFileReconciliationMode reconciliationMode = bobFileConfiguration.getDocumentFileReconciliationMode();
            if (reconciliationMode == DocumentFileReconciliationMode.EAGERLY_STREAM_ENTRIES) {
                throw new BobException("Could not reconciliate documenbt date for doc: " + atDocument);
            }

            Path filePath = findDocumentFilePathFromParentDirectory(atDocument)
                    .orElseThrow(() -> new BobException("Could not find any file matching document " + atDocument));
            return Files.newInputStream(filePath);
        }
    }

    private Optional<Path> findDocumentFilePathFromParentDirectory(ATDocument atDocument) throws IOException {
        Path documentDirectoryPath = getDocumentDirectoryPath(atDocument);
        int docNumber = atDocument.getDocNumber();
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

    private Path getDocumentFilePath(ATDocument atDocument) {
        ATBookPeriod bookPeriod = atDocument.getBookPeriod();
        String dbkCode = atDocument.getDbkCode();
        int docNumber = atDocument.getDocNumber();
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

    private Optional<BalanceChangeEvent> createYearlyResetBalanceChangeEventOptional(ATAccount atAccount, Map<String, AccountBalance> balancesPerAccountCode) {
        ATBookPeriod lastBookYearOpening = streamPeriods().filter(p -> p.getPeriodType() == ATPeriodType.OPENING)
                .reduce((c, n) -> n)
                .orElseThrow(() -> new BobException("No period opening"));
        LocalDate balanceResetDate = lastBookYearOpening.getStartDate();

        String accountCode = atAccount.getCode();
        AccountBalance accountBalanceNullable = balancesPerAccountCode.get(accountCode);

        // If there is already a balance dated in this book year, skip
        boolean hasValidBalance = Optional.ofNullable(accountBalanceNullable)
                .filter(b -> !b.getDate().isBefore(balanceResetDate))
                .isPresent();
        if (hasValidBalance) {
            return Optional.empty();
        }

        // Otherwise, reset balance to 0 and fire event
        AccountBalance accountBalance = createAccountBalance(atAccount, balanceResetDate, lastBookYearOpening, ZERO_EURO);
        BalanceChangeEvent balanceChangeEvent = createBalanceChangeEvent(accountBalance, Optional.empty());
        return Optional.of(balanceChangeEvent);
    }

    private Stream<ATAccountingEntry> streamWithBalanceCheck(ATAccountingEntry entry, Map<String, AccountBalance> balancesPerAccountCode, AccountingEventListener accountingEventListener) {
        BalanceComputationMode balanceComputationMode = bobFileConfiguration.getBalanceComputationMode();
        switch (balanceComputationMode) {
            case BOOK_YEAR_ENTRIES_ONLY:
                return this.checkBalanceResetingOnNewBookYear(entry, balancesPerAccountCode, accountingEventListener);
            case IGNORE_OPENINGS_FOR_INTERMEDIATE_YEARS:
                return this.checkBalanceChangeIgnoringIntermediateOpeningPeriods(entry, balancesPerAccountCode, accountingEventListener);
            default:
                throw new BobException("Unhandled balance computation mode: " + balanceComputationMode);
        }
    }

    private Stream<ATAccountingEntry> checkBalanceResetingOnNewBookYear(ATAccountingEntry atAccountingEntry, Map<String, AccountBalance> balancesPerAccountCode, AccountingEventListener accountingEventListener) {
        ATBookPeriod bookPeriod = atAccountingEntry.getBookPeriod();
        ATPeriodType periodType = bookPeriod.getPeriodType();

        Optional<AccountBalance> currentBalance = getResettedBalanceOnBookYearChange(atAccountingEntry, balancesPerAccountCode);
        AccountBalance newBalance;
        if (periodType == ATPeriodType.OPENING) {
            newBalance = resetAccountBalance(atAccountingEntry, balancesPerAccountCode);
        } else {
            newBalance = updateAccountBalanceAfterAccountingEntry(atAccountingEntry, balancesPerAccountCode, currentBalance);
        }
        BalanceChangeEvent balanceChangeEvent = createBalanceChangeEvent(newBalance, Optional.of(atAccountingEntry));
        accountingEventListener.handleBalanceChangeEvent(balanceChangeEvent);

        return Stream.of(atAccountingEntry);
    }

    private Stream<ATAccountingEntry> checkBalanceChangeIgnoringIntermediateOpeningPeriods(ATAccountingEntry atAccountingEntry, Map<String, AccountBalance> balancesPerAccountCode, AccountingEventListener accountingEventListener) {
        ATAccount atAccount = atAccountingEntry.getAccount();
        String accountCode = atAccount.getCode();
        ATBookPeriod bookPeriod = atAccountingEntry.getBookPeriod();
        ATPeriodType periodType = bookPeriod.getPeriodType();

        AccountBalance curBalance = balancesPerAccountCode.get(accountCode);
        AccountBalance newBalance;
        if (curBalance == null) {
            if (periodType != ATPeriodType.OPENING) {
                // TODO: warn? Without any amount for the opening of the first book year, we have to assume it was 0
            }
            newBalance = resetAccountBalance(atAccountingEntry, balancesPerAccountCode);
        } else if (periodType != ATPeriodType.OPENING) {
            newBalance = updateAccountBalanceAfterAccountingEntry(atAccountingEntry, balancesPerAccountCode);
        } else {
            // Ignore
            return Stream.empty();
        }
        BalanceChangeEvent balanceChangeEvent = createBalanceChangeEvent(newBalance, Optional.of(atAccountingEntry));
        accountingEventListener.handleBalanceChangeEvent(balanceChangeEvent);
        return Stream.of(atAccountingEntry);
    }


    private BalanceChangeEvent createBalanceChangeEvent(AccountBalance accountBalance, Optional<ATAccountingEntry> accountingEntryOptional) {
        ATAccount account = accountBalance.getAccount();
        BigDecimal balanceAmount = accountBalance.getBalance();
        LocalDate balanceDate = accountBalance.getDate();

        BalanceChangeEvent changeEvent = new BalanceChangeEvent();
        changeEvent.setAccount(account);
        changeEvent.setNewBalance(balanceAmount);
        changeEvent.setDate(balanceDate);
        changeEvent.setAccountingEntryOptional(accountingEntryOptional);
        return changeEvent;
    }


    private AccountBalance resetAccountBalance(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode) {
        LocalDate date = accountingEntry.getDate();
        BigDecimal amount = accountingEntry.getAmount();

        // For a single period opening, we might have multiple entries for a single account: one debit, one credit for instance.
        BigDecimal newBalance = getYearlyAdjustedAccountBalanceOptional(accountingEntry, balancesPerAccountCode)
                .filter(balance -> isSamePeriod(balance, accountingEntry))
                .map(AccountBalance::getBalance)
                .map(amount::add)
                .orElse(amount);

        AccountBalance accountBalance = setAccountBalance(accountingEntry, balancesPerAccountCode, date, newBalance);
        return accountBalance;
    }


    private AccountBalance updateAccountBalanceAfterAccountingEntry(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode) {
        Optional<AccountBalance> yearlyAdjustedAccountBalanceOptional = getYearlyAdjustedAccountBalanceOptional(accountingEntry, balancesPerAccountCode);
        return updateAccountBalanceAfterAccountingEntry(accountingEntry, balancesPerAccountCode, yearlyAdjustedAccountBalanceOptional);
    }

    private AccountBalance updateAccountBalanceAfterAccountingEntry(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode, Optional<AccountBalance> currentBalanceOptional) {
        BigDecimal entryAmount = accountingEntry.getAmount();
        LocalDate date = accountingEntry.getDate();
        BigDecimal newBalance = currentBalanceOptional
                .map(AccountBalance::getBalance)
                .map(entryAmount::add)
                .orElse(entryAmount);
        AccountBalance newAccountBalance = setAccountBalance(accountingEntry, balancesPerAccountCode, date, newBalance);
        return newAccountBalance;
    }

    private Optional<AccountBalance> getYearlyAdjustedAccountBalanceOptional(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode) {
        ATAccount account = accountingEntry.getAccount();
        String accountCode = account.getCode();
        boolean yearlyBalanceReset = account.isYearlyBalanceReset();
        AccountBalance accountBalanceNullable = balancesPerAccountCode.get(accountCode);
        Optional<AccountBalance> accountBalanceOptional = Optional.ofNullable(accountBalanceNullable);

        if (!yearlyBalanceReset) {
            return accountBalanceOptional;
        } else {
            return accountBalanceOptional
                    .map(accountBalance -> getResettedBalanceOnBookYearChange(accountBalance, accountingEntry));
        }
    }

    private Optional<AccountBalance> getResettedBalanceOnBookYearChange(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode) {
        ATAccount account = accountingEntry.getAccount();
        String accountCode = account.getCode();
        AccountBalance curBalanceNullable = balancesPerAccountCode.get(accountCode);
        Optional<AccountBalance> accountBalanceOptional = Optional.ofNullable(curBalanceNullable);
        return accountBalanceOptional
                .map(balance -> getResettedBalanceOnBookYearChange(balance, accountingEntry));
    }

    private AccountBalance getResettedBalanceOnBookYearChange(AccountBalance accountBalance, ATAccountingEntry accountingEntry) {
        ATAccount account = accountBalance.getAccount();
        if (isSameBookYear(accountBalance, accountingEntry)) {
            return accountBalance;
        } else {
            LocalDate date = accountingEntry.getDate();
            ATBookPeriod bookPeriod = accountingEntry.getBookPeriod();
            return createAccountBalance(account, date, bookPeriod, ZERO_EURO);
        }
    }


    private boolean isSamePeriod(AccountBalance accountBalance, ATAccountingEntry accountingEntry) {
        ATBookPeriod balancePeriod = accountBalance.getPeriod();
        ATBookPeriod entryPeriod = accountingEntry.getBookPeriod();
        return balancePeriod.equals(entryPeriod);
    }


    private boolean isSameBookYear(AccountBalance accountBalance, ATAccountingEntry accountingEntry) {
        ATBookPeriod balancePeriod = accountBalance.getPeriod();
        ATBookYear balanceBookYear = balancePeriod.getBookYear();
        ATBookPeriod entryPeriod = accountingEntry.getBookPeriod();
        ATBookYear entryBookyear = entryPeriod.getBookYear();
        return balanceBookYear.equals(entryBookyear);
    }

    private AccountBalance createAccountBalance(ATAccount atAccount, LocalDate date, ATBookPeriod bookPeriod, BigDecimal newBalanceAmount) {
        AccountBalance newBalance = new AccountBalance();
        newBalance.setAccount(atAccount);
        newBalance.setBalance(newBalanceAmount);
        newBalance.setPeriod(bookPeriod);
        newBalance.setDate(date);

        return newBalance;
    }

    private AccountBalance setAccountBalance(ATAccountingEntry accountingEntry, Map<String, AccountBalance> balancesPerAccountCode, LocalDate date, BigDecimal amount) {
        ATBookPeriod bookPeriod = accountingEntry.getBookPeriod();
        ATAccount account = accountingEntry.getAccount();
        String accountCode = account.getCode();

        AccountBalance accountBalance = createAccountBalance(account, date, bookPeriod, amount);
        balancesPerAccountCode.put(accountCode, accountBalance);
        return accountBalance;
    }


    private <T> Stream<T> streamOptional(Optional<T> optionalValue) {
        return optionalValue.map(Stream::of)
                .orElseGet(Stream::empty);
    }
}
