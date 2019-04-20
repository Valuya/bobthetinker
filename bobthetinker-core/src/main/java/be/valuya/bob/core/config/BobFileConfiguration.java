package be.valuya.bob.core.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class BobFileConfiguration {

    private Path baseFolderPath;
    private Charset charset = StandardCharsets.ISO_8859_1;

    private boolean readTablesToMemory;

    private BalanceComputationMode balanceComputationMode;

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

    public BalanceComputationMode getBalanceComputationMode() {
        return balanceComputationMode;
    }

    public void setBalanceComputationMode(BalanceComputationMode balanceComputationMode) {
        this.balanceComputationMode = balanceComputationMode;
    }
}
