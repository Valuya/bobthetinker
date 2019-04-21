package be.valuya.bob.core.config;


/**
 * Bob document files, located in the Sage-box folder, contain the document date in their filename.
 * In the bob database, however, this information is not present.
 * In order to be able to retrieve a document, its date (or filename) must be known.
 */
public enum DocumentFileReconciliationMode {
    /**
     * All accounting entries will be streamed from the database table (and cached in memory),
     * so that the document date can be set (Document date is present in the entry table).
     * This is the preferable option on slow filesystems.
     */
    EAGERLY_STREAM_ENTRIES,
    /**
     * Document wont have their date set when listing them. When querying a document content, the folder content
     * will be listed, and filenames will be checked against the ATDocument for a match.
     * This is the preferred option on fast filesystems.
     */
    LAZILY_LIST_FOLDER_CONTENT,
}
