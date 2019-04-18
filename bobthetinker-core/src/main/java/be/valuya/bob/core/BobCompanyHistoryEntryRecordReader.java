package be.valuya.bob.core;

import be.valuya.advantaje.core.AdvantajeRecord;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


public class BobCompanyHistoryEntryRecordReader {

    public BobCompanyHistoryEntry readEntry(AdvantajeRecord advantajeRecord) {
        String htype = advantajeRecord.getValue("HTYPE"); //: (STRING, 1): S
        String hid = advantajeRecord.getValue("HID"); //: (STRING, 10): VERHOEVEN
        String hfyear = advantajeRecord.getValue("HFYEAR"); //: (STRING, 5): 2013
        Optional<String> hdbk = advantajeRecord.getValueOptional("HDBK"); //: (STRING, 4): ACH
        Optional<Integer> hdocno = advantajeRecord.getValueOptional("HDOCNO"); //: (INTEGER, 4): 128
        Optional<Integer> horderno = advantajeRecord.getValueOptional("HORDERNO"); //: (INTEGER, 4): 1
        Optional<String> hdbtype = advantajeRecord.getValueOptional("HDBTYPE"); //: (STRING, 3): PUR
        Optional<Integer> hyear = advantajeRecord.getValueOptional("HYEAR"); //: (INTEGER, 4): 2014
        Optional<Integer> hmonth = advantajeRecord.getValueOptional("HMONTH"); //: (INTEGER, 4): 9
        Optional<LocalDate> hdocdate = advantajeRecord.getValueOptional("HDOCDATE"); //: (DATE, 4): 2014-07-14
        Optional<LocalDate> hduedate = advantajeRecord.getValueOptional("HDUEDATE"); //: (DATE, 4): 2014-07-14
        Optional<LocalDate> hdisdate = advantajeRecord.getValueOptional("HDISDATE"); //: (DATE, 4): 2014-07-14
        Optional<Double> hdisrate = advantajeRecord.getValueOptional("HDISRATE"); //: (DOUBLE, 8): 0.0
        Optional<Double> hdisamount = advantajeRecord.getValueOptional("HDISAMOUNT"); //: (CURRENCY, 8): 0.0
        Optional<String> hremint = advantajeRecord.getValueOptional("HREMINT"); //: (STRING, 40):
        Optional<String> hremext = advantajeRecord.getValueOptional("HREMEXT"); //: (STRING, 40):
        Optional<String> hcurrency = advantajeRecord.getValueOptional("HCURRENCY"); //: (STRING, 3):
        Optional<Double> hcuramn = advantajeRecord.getValueOptional("HCURAMN"); //: (CURRENCY, 8): [-]
        Optional<Double> hamount = advantajeRecord.getValueOptional("HAMOUNT"); //: (CURRENCY, 8): -79.86
        Optional<Double> hbase = advantajeRecord.getValueOptional("HBASE"); //: (CURRENCY, 8): 79.86
        Optional<Double> htax = advantajeRecord.getValueOptional("HTAX"); //: (CURRENCY, 8): 0.0
        Optional<Boolean> htemp = advantajeRecord.getValueOptional("HTEMP"); //: (LOGICAL, 1): true
        Optional<String> hstatus = advantajeRecord.getValueOptional("HSTATUS"); //: (STRING, 1): T
        Optional<Integer> hmatchno = advantajeRecord.getValueOptional("HMATCHNO"); //: (INTEGER, 4): 1
        Optional<Short> hremlev = advantajeRecord.getValueOptional("HREMLEV"); //: (SHORTINT, 2): 0
        Optional<String> vstored = advantajeRecord.getValueOptional("VSTORED"); //: (STRING, 10):
        Optional<LocalDate> hremdate = advantajeRecord.getValueOptional("HREMDATE"); //: (DATE, 4): [-]
        Optional<String> hremstatus = advantajeRecord.getValueOptional("HREMSTATUS"); //: (STRING, 1):
        Optional<Boolean> hisprocessed = advantajeRecord.getValueOptional("HISPROCESSED"); //: (LOGICAL, 1): false
        Optional<String> hprocstatus = advantajeRecord.getValueOptional("HPROCSTATUS"); //: (STRING, 1):
        Optional<String> horigin = advantajeRecord.getValueOptional("HORIGIN"); //: (STRING, 1):
        Optional<String> hclass = advantajeRecord.getValueOptional("HCLASS"); //: (STRING, 1):
        Optional<Boolean> hnotvalidated = advantajeRecord.getValueOptional("HNOTVALIDATED"); //: (LOGICAL, 1): [-]
        Optional<String> hmfyear = advantajeRecord.getValueOptional("HMFYEAR"); //: (STRING, 5): 2014
        Optional<Integer> hmyear = advantajeRecord.getValueOptional("HMYEAR"); //: (INTEGER, 4): 2015
        Optional<Integer> hmmonth = advantajeRecord.getValueOptional("HMMONTH"); //: (INTEGER, 4): 9
        Optional<LocalDate> hmdate = advantajeRecord.getValueOptional("HMDATE"); //: (DATE, 4): 2015-09-30
        Optional<String> createdby = advantajeRecord.getValueOptional("CREATEDBY"); //: (STRING, 10): CHRISTOF
        Optional<LocalDateTime> createdon = advantajeRecord.getValueOptional("CREATEDON"); //: (TIMESTAMP, 8): 2015-03-12T13:49:51.647
        Optional<String> modifiedby = advantajeRecord.getValueOptional("MODIFIEDBY"); //: (STRING, 10): FISCALIT
        Optional<LocalDateTime> modifiedon = advantajeRecord.getValueOptional("MODIFIEDON"); //: (TIMESTAMP, 8): 2016-03-10T12:06:51
        Optional<String> chkdigit = advantajeRecord.getValueOptional("CHKDIGIT"); //: (STRING, 10): $DDA0BADC
        Optional<String> hcollectacc = advantajeRecord.getValueOptional("HCOLLECTACC"); //: (STRING, 10): 440000
        Optional<Boolean> hmanualpay = advantajeRecord.getValueOptional("HMANUALPAY"); //: (LOGICAL, 1): false
        Optional<String> hsuppdocno = advantajeRecord.getValueOptional("HSUPPDOCNO"); //: (STRING, 20):


        Optional<BigDecimal> hDisAmountBigDecimal = hdisamount.map(this::toBigDecimal);
        Optional<BigDecimal> hcuramnBigDecimal = hcuramn.map(this::toBigDecimal);
        Optional<BigDecimal> hAmountBigDecimal = hamount.map(this::toBigDecimal);
        Optional<BigDecimal> hbaseBigDecimal = hbase.map(this::toBigDecimal);
        Optional<BigDecimal> htaxBigDecimal = htax.map(this::toBigDecimal);

        BobCompanyHistoryEntry historyEntry = new BobCompanyHistoryEntry();

        historyEntry.setHtype(htype);
        historyEntry.setHid(hid);
        historyEntry.setHfyear(hfyear);
        historyEntry.setHdbk(hdbk);
        historyEntry.setHdocno(hdocno);
        historyEntry.setHorderno(horderno);
        historyEntry.setHdbtype(hdbtype);
        historyEntry.setHyear(hyear);
        historyEntry.setHmonth(hmonth);
        historyEntry.setHdocdate(hdocdate);
        historyEntry.setHduedate(hduedate);
        historyEntry.setHdisdate(hdisdate);
        historyEntry.setHdisrate(hdisrate);
        historyEntry.setHdisamount(hDisAmountBigDecimal);
        historyEntry.setHremint(hremint);
        historyEntry.setHremext(hremext);
        historyEntry.setHcurrency(hcurrency);
        historyEntry.setHcuramn(hcuramnBigDecimal);
        historyEntry.setHamount(hAmountBigDecimal);
        historyEntry.setHbase(hbaseBigDecimal);
        historyEntry.setHtax(htaxBigDecimal);
        historyEntry.setHtemp(htemp);
        historyEntry.setHstatus(hstatus);
        historyEntry.setHmatchno(hmatchno);
        historyEntry.setHremlev(hremlev);
        historyEntry.setVstored(vstored);
        historyEntry.setHremdate(hremdate);
        historyEntry.setHremstatus(hremstatus);
        historyEntry.setHisprocessed(hisprocessed);
        historyEntry.setHprocstatus(hprocstatus);
        historyEntry.setHorigin(horigin);
        historyEntry.setHclass(hclass);
        historyEntry.setHnotvalidated(hnotvalidated);
        historyEntry.setHmfyear(hmfyear);
        historyEntry.setHmyear(hmyear);
        historyEntry.setHmmonth(hmmonth);
        historyEntry.setHmdate(hmdate);
        historyEntry.setCreatedby(createdby);
        historyEntry.setCreatedon(createdon);
        historyEntry.setModifiedby(modifiedby);
        historyEntry.setModifiedon(modifiedon);
        historyEntry.setChkdigit(chkdigit);
        historyEntry.setHcollectacc(hcollectacc);
        historyEntry.setHmanualpay(hmanualpay);
        historyEntry.setHsuppdocno(hsuppdocno);
        return historyEntry;
    }


    private BigDecimal toBigDecimal(double doubleValue) {
        return new BigDecimal(doubleValue).setScale(3, RoundingMode.HALF_UP);
    }
}
