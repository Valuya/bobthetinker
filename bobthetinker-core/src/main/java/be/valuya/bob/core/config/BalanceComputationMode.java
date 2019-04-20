package be.valuya.bob.core.config;

public enum BalanceComputationMode {
    /**
     * Mimic bob's 'Balance at period' behaviour. When computing the balance for a period X of bookyear Y,
     * entries for the Y book year only are processed by period order, until X included.
     * If no opening period is present on the year, 0 is assumed.
     */
    BOOK_YEAR_ENTRIES_ONLY,
    /**
     * Recompute the correct balance from the very beginning. Opening periods will only be considered if it is the
     * first entry for that account. Entries are processed in order and their amount summed.
     */
    IGNORE_OPENINGS_FOR_INTERMEDIATE_YEARS,

}
