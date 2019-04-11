package be.valuya.bob.core;

import be.valuya.advantaje.core.AdvantajeRecord;
import be.valuya.advantaje.core.AdvantajeValue;

import java.time.LocalDateTime;
import java.util.Optional;

public class BobPeriodRecordReader {
    public BobPeriod readPeriod(AdvantajeRecord advantajeRecord) {
        String fYear = advantajeRecord.getValue("FYEAR");
        int year = advantajeRecord.getValue("YEAR");
        int month = advantajeRecord.getValue("MONTH");
        String status = advantajeRecord.getValue("STATUS");
        Optional<Boolean> isdefault = advantajeRecord.getValueOptional("ISDEFAULT");
        String label = advantajeRecord.getValue("LABEL");
        Optional<Boolean> isctrlprint = advantajeRecord.getValueOptional("ISCTRLPRINT");
        Optional<String> origin = advantajeRecord.getValueOptional("ORIGIN");
        Optional<Integer> curlevel = advantajeRecord.getValueOptional("CURLEVEL");
        Optional<Boolean> salclosed = advantajeRecord.getValueOptional("SALCLOSED");
        Optional<Boolean> purclosed = advantajeRecord.getValueOptional("PURCLOSED");
        Optional<Boolean> casclosed = advantajeRecord.getValueOptional("CASCLOSED");
        Optional<Boolean> priclosed = advantajeRecord.getValueOptional("PRICLOSED");
        Optional<String> conversion = advantajeRecord.getValueOptional("CONVERSION");
        Optional<Boolean> fxactive = advantajeRecord.getValueOptional("FXACTIVE");
        Optional<Boolean> fxclosed = advantajeRecord.getValueOptional("FXCLOSED");
        Optional<Boolean> fxdepcalc = advantajeRecord.getValueOptional("FXDEPCALC");
        Optional<Boolean> fxprigen = advantajeRecord.getValueOptional("FXPRIGEN");
        Optional<Boolean> fxnew = advantajeRecord.getValueOptional("FXNEW");
        Optional<Boolean> fxmod = advantajeRecord.getValueOptional("FXMOD");
        Optional<LocalDateTime> fxmodifydate = advantajeRecord.getValueOptional("FXMODIFYDATE");
        Optional<LocalDateTime> fxdepdate = advantajeRecord.getValueOptional("FXDEPDATE");
        Optional<LocalDateTime> fxpridate = advantajeRecord.getValueOptional("FXPRIDATE");
        Optional<String> heading1 = advantajeRecord.getValueOptional("HEADING1");
        Optional<String> heading2 = advantajeRecord.getValueOptional("HEADING2");

        BobPeriod bobPeriod = new BobPeriod();
        bobPeriod.setfYear(fYear);
        bobPeriod.setYear(year);
        bobPeriod.setMonth(month);
        bobPeriod.setLabel(label);

        return bobPeriod;
    }

    private String getFieldName(AdvantajeValue<?> value) {
        return value.getField().getName();
    }
}
