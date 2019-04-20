package be.valuya.bob.core;

import be.valuya.bob.core.config.BobFileConfiguration;

public class BobSession {

    private final BobFileConfiguration bobFileConfiguration;

    public BobSession(BobFileConfiguration bobFileConfiguration) {
        this.bobFileConfiguration = bobFileConfiguration;
    }

    public BobFileConfiguration getBobFileConfiguration() {
        return bobFileConfiguration;
    }


}
