package be.valuya.bob.core;

import be.valuya.advantaje.core.AdvantajeRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class BobAccountHistoryEntryRecordReader {

    public BobAccountHistoryEntry readEntry(AdvantajeRecord advantajeRecord) {
        String hid = advantajeRecord.getValue("HID"); //: (STRING, 10): 1
        Optional<String> hdbk = advantajeRecord.getValueOptional("HDBK"); // STRING - 4 -
        Optional<String> hfyear = advantajeRecord.getValueOptional("HFYEAR"); // STRING - 5 -
        Optional<Integer> hmonth = advantajeRecord.getValueOptional("HMONTH"); // INTEGER - 4 -
        Optional<Integer> hdocno = advantajeRecord.getValueOptional("HDOCNO"); // INTEGER - 4 -
        Optional<Integer> horderno = advantajeRecord.getValueOptional("HORDERNO"); // INTEGER - 4 -
        Optional<String> hdbtype = advantajeRecord.getValueOptional("HDBTYPE"); // STRING - 3 -
        Optional<Integer> hyear = advantajeRecord.getValueOptional("HYEAR"); // INTEGER - 4 -
        Optional<String> hcussup = advantajeRecord.getValueOptional("HCUSSUP"); // STRING - 10 -
        Optional<LocalDate> hdocdate = advantajeRecord.getValueOptional("HDOCDATE"); // DATE - 4 -
        Optional<LocalDate> hduedate = advantajeRecord.getValueOptional("HDUEDATE"); // DATE - 4 -
        Optional<String> hrem = advantajeRecord.getValueOptional("HREM"); // STRING - 40 -
        Optional<Double> hamount = advantajeRecord.getValueOptional("HAMOUNT"); // CURRENCY - 8 -
        Optional<String> hcurrency = advantajeRecord.getValueOptional("HCURRENCY"); // STRING - 3 -
        Optional<Double> hcuramn = advantajeRecord.getValueOptional("HCURAMN"); // CURRENCY - 8 -
        Optional<Double> hbase = advantajeRecord.getValueOptional("HBASE"); // CURRENCY - 8 -
        Optional<String> hvatcode = advantajeRecord.getValueOptional("HVATCODE"); // STRING - 10 -
        Optional<Double> htax = advantajeRecord.getValueOptional("HTAX"); // CURRENCY - 8 -
        Optional<Double> htaxnd = advantajeRecord.getValueOptional("HTAXND"); // CURRENCY - 8 -
        Optional<String> hstatus = advantajeRecord.getValueOptional("HSTATUS"); // STRING - 1 -
        Optional<Integer> hmatchno = advantajeRecord.getValueOptional("HMATCHNO"); // INTEGER - 4 -
        Optional<String> hclass = advantajeRecord.getValueOptional("HCLASS"); // STRING - 1 -
        Optional<Boolean> htemp = advantajeRecord.getValueOptional("HTEMP"); // LOGICAL - 1 -
        Optional<Boolean> hiscost = advantajeRecord.getValueOptional("HISCOST"); // LOGICAL - 1 -
        Optional<String> horigin = advantajeRecord.getValueOptional("HORIGIN"); // STRING - 1 -
        Optional<String> createdby = advantajeRecord.getValueOptional("CREATEDBY"); // STRING - 10 -
        Optional<LocalDateTime> createdon = advantajeRecord.getValueOptional("CREATEDON"); // TIMESTAMP - 8 -
        Optional<String> modifiedby = advantajeRecord.getValueOptional("MODIFIEDBY"); // STRING - 10 -
        Optional<LocalDateTime> modifiedon = advantajeRecord.getValueOptional("MODIFIEDON"); // TIMESTAMP - 8 -
        Optional<String> cntrprtacc = advantajeRecord.getValueOptional("CNTRPRTACC"); // STRING - 10 -
        Optional<String> htype = advantajeRecord.getValueOptional("HTYPE"); // STRING - 3 -
        Optional<Boolean> hdiscadv = advantajeRecord.getValueOptional("HDISCADV"); // LOGICAL - 1 -
        Optional<String> hmfyear = advantajeRecord.getValueOptional("HMFYEAR"); // STRING - 5 -
        Optional<Integer> hmyear = advantajeRecord.getValueOptional("HMYEAR"); // INTEGER - 4 -
        Optional<Integer> hmmonth = advantajeRecord.getValueOptional("HMMONTH"); // INTEGER - 4 -
        Optional<LocalDate> hmdate = advantajeRecord.getValueOptional("HMDATE"); // DATE - 4 -
        Optional<String> hcstype = advantajeRecord.getValueOptional("HCSTYPE"); // STRING - 1 -
        Optional<String> chkdigit = advantajeRecord.getValueOptional("CHKDIGIT"); // STRING - 10 -
        Optional<String> hvatmode = advantajeRecord.getValueOptional("HVATMODE"); // STRING - 1 -

        Optional<BigDecimal> amountBigDecimal = hamount.map(BigDecimal::new);
        Optional<BigDecimal> hcuramnBigDecimal = hcuramn.map(BigDecimal::new);
        Optional<BigDecimal> hbaseBigDecimal = hbase.map(BigDecimal::new);
        Optional<BigDecimal> htaxBigDecimal = htax.map(BigDecimal::new);
        Optional<BigDecimal> htaxndBigDecimal = htaxnd.map(BigDecimal::new);

        BobAccountHistoryEntry accountingEntry = new BobAccountHistoryEntry();
        accountingEntry.setHid(hid);
        accountingEntry.setHdbk(hdbk.orElse(null));
        accountingEntry.setHfyear(hfyear.orElse(null));
        accountingEntry.setHmonth(hmonth.orElse(null));
        accountingEntry.setHdocno(hdocno.orElse(null));
        accountingEntry.setHorderno(horderno.orElse(null));
        accountingEntry.setHdbtype(hdbtype.orElse(null));
        accountingEntry.setHyear(hyear.orElse(null));
        accountingEntry.setHcussup(hcussup.orElse(null));
        accountingEntry.setHdocdate(hdocdate.orElse(null));
        accountingEntry.setHduedate(hduedate.orElse(null));
        accountingEntry.setHrem(hrem.orElse(null));
        accountingEntry.setHamount(amountBigDecimal.orElse(null));
        accountingEntry.setHcurrency(hcurrency.orElse(null));
        accountingEntry.setHcuramn(hcuramnBigDecimal.orElse(null));
        accountingEntry.setHbase(hbaseBigDecimal.orElse(null));
        accountingEntry.setHvatcode(hvatcode.orElse(null));
        accountingEntry.setHtax(htaxBigDecimal.orElse(null));
        accountingEntry.setHtaxnd(htaxndBigDecimal.orElse(null));
        accountingEntry.setHstatus(hstatus.orElse(null));
        accountingEntry.setHmatchno(hmatchno.orElse(null));
        accountingEntry.setHclass(hclass.orElse(null));
        accountingEntry.setHtemp(htemp.orElse(null));
        accountingEntry.setHiscost(hiscost.orElse(null));
        accountingEntry.setHorigin(horigin.orElse(null));
        accountingEntry.setCreatedby(createdby.orElse(null));
        accountingEntry.setCreatedon(createdon.orElse(null));
        accountingEntry.setModifiedby(modifiedby.orElse(null));
        accountingEntry.setModifiedon(modifiedon.orElse(null));
        accountingEntry.setCntrprtacc(cntrprtacc.orElse(null));
        accountingEntry.setHtype(htype.orElse(null));
        accountingEntry.setHdiscadv(hdiscadv.orElse(null));
        accountingEntry.setHmfyear(hmfyear.orElse(null));
        accountingEntry.setHmyear(hmyear.orElse(null));
        accountingEntry.setHmmonth(hmmonth.orElse(null));
        accountingEntry.setHmdate(hmdate.orElse(null));
        accountingEntry.setHcstype(hcstype.orElse(null));
        accountingEntry.setChkdigit(chkdigit.orElse(null));
        accountingEntry.setHvatmode(hvatmode.orElse(null));
        return accountingEntry;
    }
}
