package be.valuya.bob.core;

public class BobSession {

    private final BobFileConfiguration bobFileConfiguration;

    public BobSession(BobFileConfiguration bobFileConfiguration) {
        this.bobFileConfiguration = bobFileConfiguration;
    }

    public BobFileConfiguration getBobFileConfiguration() {
        return bobFileConfiguration;
    }


}
