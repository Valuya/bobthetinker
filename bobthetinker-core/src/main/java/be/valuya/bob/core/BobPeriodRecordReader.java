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
        boolean defaultPeriod = advantajeRecord.getValue("ISDEFAULT");
        String label = advantajeRecord.getValue("LABEL");
        Optional<Boolean> ctrlPrintOptional = advantajeRecord.getValueOptional("ISCTRLPRINT");
        Optional<String> originOptional = advantajeRecord.getValueOptional("ORIGIN");
        Optional<Integer> curLevelOptional = advantajeRecord.getValueOptional("CURLEVEL");
        Optional<Boolean> salClosedOptional = advantajeRecord.getValueOptional("SALCLOSED");
        Optional<Boolean> purClosedOptional = advantajeRecord.getValueOptional("PURCLOSED");
        Optional<Boolean> casClosedOptional = advantajeRecord.getValueOptional("CASCLOSED");
        Optional<Boolean> priClosedOptional = advantajeRecord.getValueOptional("PRICLOSED");
        Optional<String> conversionOptional = advantajeRecord.getValueOptional("CONVERSION");
        Optional<Boolean> fxActiveOptional = advantajeRecord.getValueOptional("FXACTIVE");
        Optional<Boolean> fxClosedOptional = advantajeRecord.getValueOptional("FXCLOSED");
        Optional<Boolean> fxDepcalcOptional = advantajeRecord.getValueOptional("FXDEPCALC");
        Optional<Boolean> fxPrigenOptional = advantajeRecord.getValueOptional("FXPRIGEN");
        Optional<Boolean> fxNewOptional = advantajeRecord.getValueOptional("FXNEW");
        Optional<Boolean> fxModOptional = advantajeRecord.getValueOptional("FXMOD");
        Optional<LocalDateTime> fxModificationDateTimeOptional = advantajeRecord.getValueOptional("FXMODIFYDATE");
        Optional<LocalDateTime> fxDepDateTimeOptional = advantajeRecord.getValueOptional("FXDEPDATE");
        Optional<LocalDateTime> fxpriDateTimeOptional = advantajeRecord.getValueOptional("FXPRIDATE");
        Optional<String> heading1Optional = advantajeRecord.getValueOptional("HEADING1");
        Optional<String> heading2Optional = advantajeRecord.getValueOptional("HEADING2");

        BobPeriod bobPeriod = new BobPeriod();
        bobPeriod.setfYear(fYear);
        bobPeriod.setYear(year);
        bobPeriod.setMonth(month);
        bobPeriod.setStatus(status);
        bobPeriod.setLabel(label);
        bobPeriod.setDefaultPeriod(defaultPeriod);
        bobPeriod.setFxNewOptional(fxNewOptional);
        bobPeriod.setCtrlPrintOptional(ctrlPrintOptional);
        bobPeriod.setOriginOptional(originOptional);
        bobPeriod.setCurLevelOptional(curLevelOptional);
        bobPeriod.setSalesClosedOptional(salClosedOptional);
        bobPeriod.setPurchasesClosedOptional(purClosedOptional);
        bobPeriod.setCasClosedOptional(casClosedOptional);
        bobPeriod.setPriClosedOptional(priClosedOptional);
        bobPeriod.setConversionOptional(conversionOptional);
        bobPeriod.setFxActiveOptional(fxActiveOptional);
        bobPeriod.setFxClosedOptional(fxClosedOptional);
        bobPeriod.setFxDepcalcOptional(fxDepcalcOptional);
        bobPeriod.setFxPrigenOptional(fxPrigenOptional);
        bobPeriod.setFxNewOptional(fxNewOptional);
        bobPeriod.setFxModOptional(fxModOptional);
        bobPeriod.setFxModificationDateTimeOptional(fxModificationDateTimeOptional);
        bobPeriod.setFxDepDateTimeOptional(fxDepDateTimeOptional);
        bobPeriod.setFxpriDateTimeOptional(fxpriDateTimeOptional);
        bobPeriod.setHeading1Optional(heading1Optional);
        bobPeriod.setHeading2Optional(heading2Optional);

        return bobPeriod;
    }

    private String getFieldName(AdvantajeValue<?> value) {
        return value.getField().getName();
    }
}
