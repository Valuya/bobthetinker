package be.valuya.bob.core;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class BobFileConfiguration {

    private Path baseFolderPath;
    private Charset charset = StandardCharsets.ISO_8859_1;

    private boolean readTablesToMemory;
    /**
     * When set, opening periods for the first book year will be used only to compute account balances.
     * For the following book years, intermediate balances reported in the opening periods will be ignored,
     * and account operations will be summed to compute the balance amounts).
     */
    private boolean ignoreOpeningPeriodBalances;

    public BobFileConfiguration(Path baseFolderPath) {
        this.baseFolderPath = baseFolderPath;
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

    public boolean isIgnoreOpeningPeriodBalances() {
        return ignoreOpeningPeriodBalances;
    }

    public void setIgnoreOpeningPeriodBalances(boolean ignoreOpeningPeriodBalances) {
        this.ignoreOpeningPeriodBalances = ignoreOpeningPeriodBalances;
    }
}
