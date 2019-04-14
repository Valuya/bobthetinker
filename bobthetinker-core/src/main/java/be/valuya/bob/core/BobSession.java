package be.valuya.bob.core;

import be.valuya.accountingtroll.Session;
import be.valuya.accountingtroll.cache.AccountingCache;
import be.valuya.accountingtroll.cache.MemorySessionCache;

public class BobSession implements Session {

    public final static String SESSION_TYPE = "be.valuya.bob";
    private final BobFileConfiguration bobFileConfiguration;
    private final MemorySessionCache accountingCache;

    public BobSession(BobFileConfiguration bobFileConfiguration) {
        this.bobFileConfiguration = bobFileConfiguration;
        this.accountingCache = new MemorySessionCache(this);
    }

    public BobFileConfiguration getBobFileConfiguration() {
        return bobFileConfiguration;
    }

    @Override
    public String getSessionType() {
        return SESSION_TYPE;
    }

    @Override
    public AccountingCache getCache() {
        return accountingCache;
    }
}
