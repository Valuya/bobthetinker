package be.valuya.bob.core;

import java.util.List;

public class BobSession {

    private final BobFileConfiguration bobFileConfiguration;
    private final List<BobPeriod> bobPeriods;

    public BobSession(BobFileConfiguration bobFileConfiguration, List<BobPeriod> bobPeriods) {
        this.bobFileConfiguration = bobFileConfiguration;
        this.bobPeriods = bobPeriods;
    }

    public BobFileConfiguration getBobFileConfiguration() {
        return bobFileConfiguration;
    }

    public List<BobPeriod> getBobPeriods() {
        return bobPeriods;
    }
}
