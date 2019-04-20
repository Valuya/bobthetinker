package be.valuya.bob.core.reader;

import be.valuya.advantaje.core.AdvantajeRecord;
import be.valuya.bob.core.domain.BobDocument;

import java.time.LocalDateTime;
import java.util.Optional;

public class BobDocumentRecordReader {

    public BobDocument readDocument(AdvantajeRecord advantajeRecord) {
        String docId = advantajeRecord.getValue("DOCID");
        String fYear = advantajeRecord.getValue("FYEAR");
        int year = advantajeRecord.getValue("YEAR");
        int month = advantajeRecord.getValue("MONTH");
        String dbk = advantajeRecord.getValue("DBK");
        int docno = advantajeRecord.getValue("DOCNO");

        Optional<String> dbTypeOptional = advantajeRecord.getValueOptional("DBTYPE");
        Optional<String> createdBy = advantajeRecord.getValueOptional("CREATEDBY");
        Optional<LocalDateTime> createdOn = advantajeRecord.getValueOptional("CREATEDON");
        Optional<String> modifiedBy = advantajeRecord.getValueOptional("MODIFIEDBY");
        Optional<LocalDateTime> modifiedOn = advantajeRecord.getValueOptional("MODIFIEDON");
        Optional<String> appcircuitid = advantajeRecord.getValueOptional("APPCIRCUITID");


        BobDocument bobDocument = new BobDocument();
        bobDocument.setId(docId);
        bobDocument.setFyear(fYear);
        bobDocument.setYear(year);
        bobDocument.setMonth(month);
        bobDocument.setDbk(dbk);
        bobDocument.setDocNo(docno);
        bobDocument.setDbType(dbTypeOptional);
        bobDocument.setCreatedBy(createdBy);
        bobDocument.setCreatedOn(createdOn);
        bobDocument.setModifiedBy(modifiedBy);
        bobDocument.setModifiedOn(modifiedOn);
        bobDocument.setAppCirtcuitId(appcircuitid);
        return bobDocument;
    }
}
