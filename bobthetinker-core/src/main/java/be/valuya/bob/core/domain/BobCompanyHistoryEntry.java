package be.valuya.bob.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * table ac_chisto
 */
public class BobCompanyHistoryEntry {
    private String htype; //: (STRING, 1): S
    private String hid; //: (STRING, 10): VERHOEVEN
    private String hfyear; //: (STRING, 5): 2013
    private Optional<String> hdbk; //: (STRING, 4): ACH
    private Optional<Integer> hdocno; //: (INTEGER, 4): 128
    private Optional<Integer> horderno; //: (INTEGER, 4): 1
    private Optional<String> hdbtype; //: (STRING, 3): PUR
    private Optional<Integer> hyear; //: (INTEGER, 4): 2014
    private Optional<Integer> hmonth; //: (INTEGER, 4): 9
    private Optional<LocalDate> hdocdate; //: (DATE, 4): 2014-07-14
    private Optional<LocalDate> hduedate; //: (DATE, 4): 2014-07-14
    private Optional<LocalDate> hdisdate; //: (DATE, 4): 2014-07-14
    private Optional<Double> hdisrate; //: (DOUBLE, 8): 0.0
    private Optional<BigDecimal> hdisamount; //: (CURRENCY, 8): 0.0
    private Optional<String> hremint; //: (STRING, 40):
    private Optional<String> hremext; //: (STRING, 40):
    private Optional<String> hcurrency; //: (STRING, 3):
    private Optional<BigDecimal> hcuramn; //: (CURRENCY, 8): [-]
    private Optional<BigDecimal> hamount; //: (CURRENCY, 8): -79.86
    private Optional<BigDecimal> hbase; //: (CURRENCY, 8): 79.86
    private Optional<BigDecimal> htax; //: (CURRENCY, 8): 0.0
    private Optional<Boolean> htemp; //: (LOGICAL, 1): true
    private Optional<String> hstatus; //: (STRING, 1): T
    private Optional<Integer> hmatchno; //: (INTEGER, 4): 1
    private Optional<Short> hremlev; //: (SHORTINT, 2): 0
    private Optional<String> vstored; //: (STRING, 10):
    private Optional<LocalDate> hremdate; //: (DATE, 4): [-]
    private Optional<String> hremstatus; //: (STRING, 1):
    private Optional<Boolean> hisprocessed; //: (LOGICAL, 1): false
    private Optional<String> hprocstatus; //: (STRING, 1):
    private Optional<String> horigin; //: (STRING, 1):
    private Optional<String> hclass; //: (STRING, 1):
    private Optional<Boolean> hnotvalidated; //: (LOGICAL, 1): [-]
    private Optional<String> hmfyear; //: (STRING, 5): 2014
    private Optional<Integer> hmyear; //: (INTEGER, 4): 2015
    private Optional<Integer> hmmonth; //: (INTEGER, 4): 9
    private Optional<LocalDate> hmdate; //: (DATE, 4): 2015-09-30
    private Optional<String> createdby; //: (STRING, 10): CHRISTOF
    private Optional<LocalDateTime> createdon; //: (TIMESTAMP, 8): 2015-03-12T13:49:51.647
    private Optional<String> modifiedby; //: (STRING, 10): FISCALIT
    private Optional<LocalDateTime> modifiedon; //: (TIMESTAMP, 8): 2016-03-10T12:06:51
    private Optional<String> chkdigit; //: (STRING, 10): $DDA0BADC
    private Optional<String> hcollectacc; //: (STRING, 10): 440000
    private Optional<Boolean> hmanualpay; //: (LOGICAL, 1): false
    private Optional<String> hsuppdocno; //: (STRING, 20):

    public String getHtype() {
        return htype;
    }

    public void setHtype(String htype) {
        this.htype = htype;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHfyear() {
        return hfyear;
    }

    public void setHfyear(String hfyear) {
        this.hfyear = hfyear;
    }

    public Optional<String> getHdbk() {
        return hdbk;
    }

    public void setHdbk(Optional<String> hdbk) {
        this.hdbk = hdbk;
    }

    public Optional<Integer> getHdocno() {
        return hdocno;
    }

    public void setHdocno(Optional<Integer> hdocno) {
        this.hdocno = hdocno;
    }

    public Optional<Integer> getHorderno() {
        return horderno;
    }

    public void setHorderno(Optional<Integer> horderno) {
        this.horderno = horderno;
    }

    public Optional<String> getHdbtype() {
        return hdbtype;
    }

    public void setHdbtype(Optional<String> hdbtype) {
        this.hdbtype = hdbtype;
    }

    public Optional<Integer> getHyear() {
        return hyear;
    }

    public void setHyear(Optional<Integer> hyear) {
        this.hyear = hyear;
    }

    public Optional<Integer> getHmonth() {
        return hmonth;
    }

    public void setHmonth(Optional<Integer> hmonth) {
        this.hmonth = hmonth;
    }

    public Optional<LocalDate> getHdocdate() {
        return hdocdate;
    }

    public void setHdocdate(Optional<LocalDate> hdocdate) {
        this.hdocdate = hdocdate;
    }

    public Optional<LocalDate> getHduedate() {
        return hduedate;
    }

    public void setHduedate(Optional<LocalDate> hduedate) {
        this.hduedate = hduedate;
    }

    public Optional<LocalDate> getHdisdate() {
        return hdisdate;
    }

    public void setHdisdate(Optional<LocalDate> hdisdate) {
        this.hdisdate = hdisdate;
    }

    public Optional<Double> getHdisrate() {
        return hdisrate;
    }

    public void setHdisrate(Optional<Double> hdisrate) {
        this.hdisrate = hdisrate;
    }

    public Optional<BigDecimal> getHdisamount() {
        return hdisamount;
    }

    public void setHdisamount(Optional<BigDecimal> hdisamount) {
        this.hdisamount = hdisamount;
    }

    public Optional<String> getHremint() {
        return hremint;
    }

    public void setHremint(Optional<String> hremint) {
        this.hremint = hremint;
    }

    public Optional<String> getHremext() {
        return hremext;
    }

    public void setHremext(Optional<String> hremext) {
        this.hremext = hremext;
    }

    public Optional<String> getHcurrency() {
        return hcurrency;
    }

    public void setHcurrency(Optional<String> hcurrency) {
        this.hcurrency = hcurrency;
    }

    public Optional<BigDecimal> getHcuramn() {
        return hcuramn;
    }

    public void setHcuramn(Optional<BigDecimal> hcuramn) {
        this.hcuramn = hcuramn;
    }

    public Optional<BigDecimal> getHamount() {
        return hamount;
    }

    public void setHamount(Optional<BigDecimal> hamount) {
        this.hamount = hamount;
    }

    public Optional<BigDecimal> getHbase() {
        return hbase;
    }

    public void setHbase(Optional<BigDecimal> hbase) {
        this.hbase = hbase;
    }

    public Optional<BigDecimal> getHtax() {
        return htax;
    }

    public void setHtax(Optional<BigDecimal> htax) {
        this.htax = htax;
    }

    public Optional<Boolean> getHtemp() {
        return htemp;
    }

    public void setHtemp(Optional<Boolean> htemp) {
        this.htemp = htemp;
    }

    public Optional<String> getHstatus() {
        return hstatus;
    }

    public void setHstatus(Optional<String> hstatus) {
        this.hstatus = hstatus;
    }

    public Optional<Integer> getHmatchno() {
        return hmatchno;
    }

    public void setHmatchno(Optional<Integer> hmatchno) {
        this.hmatchno = hmatchno;
    }

    public Optional<Short> getHremlev() {
        return hremlev;
    }

    public void setHremlev(Optional<Short> hremlev) {
        this.hremlev = hremlev;
    }

    public Optional<String> getVstored() {
        return vstored;
    }

    public void setVstored(Optional<String> vstored) {
        this.vstored = vstored;
    }

    public Optional<LocalDate> getHremdate() {
        return hremdate;
    }

    public void setHremdate(Optional<LocalDate> hremdate) {
        this.hremdate = hremdate;
    }

    public Optional<String> getHremstatus() {
        return hremstatus;
    }

    public void setHremstatus(Optional<String> hremstatus) {
        this.hremstatus = hremstatus;
    }

    public Optional<Boolean> getHisprocessed() {
        return hisprocessed;
    }

    public void setHisprocessed(Optional<Boolean> hisprocessed) {
        this.hisprocessed = hisprocessed;
    }

    public Optional<String> getHprocstatus() {
        return hprocstatus;
    }

    public void setHprocstatus(Optional<String> hprocstatus) {
        this.hprocstatus = hprocstatus;
    }

    public Optional<String> getHorigin() {
        return horigin;
    }

    public void setHorigin(Optional<String> horigin) {
        this.horigin = horigin;
    }

    public Optional<String> getHclass() {
        return hclass;
    }

    public void setHclass(Optional<String> hclass) {
        this.hclass = hclass;
    }

    public Optional<Boolean> getHnotvalidated() {
        return hnotvalidated;
    }

    public void setHnotvalidated(Optional<Boolean> hnotvalidated) {
        this.hnotvalidated = hnotvalidated;
    }

    public Optional<String> getHmfyear() {
        return hmfyear;
    }

    public void setHmfyear(Optional<String> hmfyear) {
        this.hmfyear = hmfyear;
    }

    public Optional<Integer> getHmyear() {
        return hmyear;
    }

    public void setHmyear(Optional<Integer> hmyear) {
        this.hmyear = hmyear;
    }

    public Optional<Integer> getHmmonth() {
        return hmmonth;
    }

    public void setHmmonth(Optional<Integer> hmmonth) {
        this.hmmonth = hmmonth;
    }

    public Optional<LocalDate> getHmdate() {
        return hmdate;
    }

    public void setHmdate(Optional<LocalDate> hmdate) {
        this.hmdate = hmdate;
    }

    public Optional<String> getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Optional<String> createdby) {
        this.createdby = createdby;
    }

    public Optional<LocalDateTime> getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Optional<LocalDateTime> createdon) {
        this.createdon = createdon;
    }

    public Optional<String> getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(Optional<String> modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Optional<LocalDateTime> getModifiedon() {
        return modifiedon;
    }

    public void setModifiedon(Optional<LocalDateTime> modifiedon) {
        this.modifiedon = modifiedon;
    }

    public Optional<String> getChkdigit() {
        return chkdigit;
    }

    public void setChkdigit(Optional<String> chkdigit) {
        this.chkdigit = chkdigit;
    }

    public Optional<String> getHcollectacc() {
        return hcollectacc;
    }

    public void setHcollectacc(Optional<String> hcollectacc) {
        this.hcollectacc = hcollectacc;
    }

    public Optional<Boolean> getHmanualpay() {
        return hmanualpay;
    }

    public void setHmanualpay(Optional<Boolean> hmanualpay) {
        this.hmanualpay = hmanualpay;
    }

    public Optional<String> getHsuppdocno() {
        return hsuppdocno;
    }

    public void setHsuppdocno(Optional<String> hsuppdocno) {
        this.hsuppdocno = hsuppdocno;
    }
}
