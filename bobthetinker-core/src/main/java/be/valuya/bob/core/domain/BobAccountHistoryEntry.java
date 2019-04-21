package be.valuya.bob.core.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * table ac_ahisto
 */
public class BobAccountHistoryEntry {

    @NotNull
    @Size(min = 1)
    private String hid; // STRING - 10 -
    @NotNull
    @Size(min = 1)
    private String hdbk; // STRING - 4 -
    @NotNull
    @Size(min = 1)
    private String hfyear; // STRING - 5 -
    @NotNull
    @Min(0)
    private Integer hmonth; // INTEGER - 4 -
    @NotNull
    @Min(0)
    private Integer hdocno; // INTEGER - 4 -
    @NotNull
    @Min(0)
    private Integer horderno; // INTEGER - 4 -
    @NotNull
    @Size(min = 1)
    private String hdbtype; // STRING - 3 -
    @NotNull
    @Min(1)
    private Integer hyear; // INTEGER - 4 -

    private String hcussup; // STRING - 10 -
    private LocalDate hdocdate; // DATE - 4 -
    private LocalDate hduedate; // DATE - 4 -
    private String hrem; // STRING - 40 -
    private BigDecimal hamount; // CURRENCY - 8 -
    private String hcurrency; // STRING - 3 -
    private BigDecimal hcuramn; // CURRENCY - 8 -
    private BigDecimal hbase; // CURRENCY - 8 -
    private String hvatcode; // STRING - 10 -
    private BigDecimal htax; // CURRENCY - 8 -
    private BigDecimal htaxnd; // CURRENCY - 8 -
    private String hstatus; // STRING - 1 -
    private Integer hmatchno; // INTEGER - 4 -
    private String hclass; // STRING - 1 -
    private Boolean htemp; // LOGICAL - 1 -
    private Boolean hiscost; // LOGICAL - 1 -
    private String horigin; // STRING - 1 -
    private String createdby; // STRING - 10 -
    private LocalDateTime createdon; // TIMESTAMP - 8 -
    private String modifiedby; // STRING - 10 -
    private LocalDateTime modifiedon; // TIMESTAMP - 8 -
    /**
     * Most often point to another hid.
     * Sometimes '**', when not matched?
     */
    private String cntrprtacc; // STRING - 10 -
    /**
     * 'CAS', 'ACC', 'SUP'. When 'SUP', hcstype seems set to S
     */
    private String htype; // STRING - 3 -
    private Boolean hdiscadv; // LOGICAL - 1 -
    private String hmfyear; // STRING - 5 -
    private Integer hmyear; // INTEGER - 4 -
    private Integer hmmonth; // INTEGER - 4 -
    private LocalDate hmdate; // DATE - 4 -
    /**
     * Often empty, sometimes 'S'
     */
    private String hcstype; // STRING - 1 -
    private String chkdigit; // STRING - 10 -
    /**
     * Seems always D
     */
    private String hvatmode; // STRING - 1 -

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHdbk() {
        return hdbk;
    }

    public String getHfyear() {
        return hfyear;
    }

    public Integer getHmonth() {
        return hmonth;
    }

    public Integer getHdocno() {
        return hdocno;
    }

    public Integer getHorderno() {
        return horderno;
    }

    public Integer getHyear() {
        return hyear;
    }

    public BigDecimal getHamount() {
        return hamount;
    }

    public LocalDate getHdocdate() {
        return hdocdate;
    }

    public Optional<String> getHdbkOptional() {
        return Optional.ofNullable(hdbk);
    }

    public void setHdbk(String hdbk) {
        this.hdbk = hdbk;
    }

    public Optional<String> getHfyearOptional() {
        return Optional.ofNullable(hfyear);
    }

    public void setHfyear(String hfyear) {
        this.hfyear = hfyear;
    }

    public Optional<Integer> getHmonthOptional() {
        return Optional.ofNullable(hmonth);
    }

    public void setHmonth(Integer hmonth) {
        this.hmonth = hmonth;
    }

    public Optional<Integer> getHdocnoOptional() {
        return Optional.ofNullable(hdocno);
    }

    public void setHdocno(Integer hdocno) {
        this.hdocno = hdocno;
    }

    public Optional<Integer> getHordernoOptional() {
        return Optional.ofNullable(horderno);
    }

    public void setHorderno(Integer horderno) {
        this.horderno = horderno;
    }

    public Optional<String> getHdbtypeOptional() {
        return Optional.ofNullable(hdbtype);
    }

    public void setHdbtype(String hdbtype) {
        this.hdbtype = hdbtype;
    }

    public Optional<Integer> getHyearOptional() {
        return Optional.ofNullable(hyear);
    }

    public void setHyear(Integer hyear) {
        this.hyear = hyear;
    }

    public Optional<String> getHcussupOptional() {
        return Optional.ofNullable(hcussup);
    }

    public void setHcussup(String hcussup) {
        this.hcussup = hcussup;
    }

    public Optional<LocalDate> getHdocdateOptional() {
        return Optional.ofNullable(hdocdate);
    }

    public void setHdocdate(LocalDate hdocdate) {
        this.hdocdate = hdocdate;
    }

    public Optional<LocalDate> getHduedateOptional() {
        return Optional.ofNullable(hduedate);
    }

    public void setHduedate(LocalDate hduedate) {
        this.hduedate = hduedate;
    }

    public Optional<String> getHremOptional() {
        return Optional.ofNullable(hrem);
    }

    public void setHrem(String hrem) {
        this.hrem = hrem;
    }

    public Optional<BigDecimal> getHamountOptional() {
        return Optional.ofNullable(hamount);
    }

    public void setHamount(BigDecimal hamount) {
        this.hamount = hamount;
    }

    public Optional<String> getHcurrencyOptional() {
        return Optional.ofNullable(hcurrency);
    }

    public void setHcurrency(String hcurrency) {
        this.hcurrency = hcurrency;
    }

    public Optional<BigDecimal> getHcuramnOptional() {
        return Optional.ofNullable(hcuramn);
    }

    public void setHcuramn(BigDecimal hcuramn) {
        this.hcuramn = hcuramn;
    }

    public Optional<BigDecimal> getHbaseOptional() {
        return Optional.ofNullable(hbase);
    }

    public void setHbase(BigDecimal hbase) {
        this.hbase = hbase;
    }

    public Optional<String> getHvatcodeOptional() {
        return Optional.ofNullable(hvatcode);
    }

    public void setHvatcode(String hvatcode) {
        this.hvatcode = hvatcode;
    }

    public Optional<BigDecimal> getHtaxOptional() {
        return Optional.ofNullable(htax);
    }

    public void setHtax(BigDecimal htax) {
        this.htax = htax;
    }

    public Optional<BigDecimal> getHtaxndOptional() {
        return Optional.ofNullable(htaxnd);
    }

    public void setHtaxnd(BigDecimal htaxnd) {
        this.htaxnd = htaxnd;
    }

    public Optional<String> getHstatusOptional() {
        return Optional.ofNullable(hstatus);
    }

    public void setHstatus(String hstatus) {
        this.hstatus = hstatus;
    }

    public Optional<Integer> getHmatchnoOptional() {
        return Optional.ofNullable(hmatchno);
    }

    public void setHmatchno(Integer hmatchno) {
        this.hmatchno = hmatchno;
    }

    public Optional<String> getHclassOptional() {
        return Optional.ofNullable(hclass);
    }

    public void setHclass(String hclass) {
        this.hclass = hclass;
    }

    public Optional<Boolean> getHtempOptional() {
        return Optional.ofNullable(htemp);
    }

    public void setHtemp(Boolean htemp) {
        this.htemp = htemp;
    }

    public Optional<Boolean> getHiscostOptional() {
        return Optional.ofNullable(hiscost);
    }

    public void setHiscost(Boolean hiscost) {
        this.hiscost = hiscost;
    }

    public Optional<String> getHoriginOptional() {
        return Optional.ofNullable(horigin);
    }

    public void setHorigin(String horigin) {
        this.horigin = horigin;
    }

    public Optional<String> getCreatedbyOptional() {
        return Optional.ofNullable(createdby);
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Optional<LocalDateTime> getCreatedonOptional() {
        return Optional.ofNullable(createdon);
    }

    public void setCreatedon(LocalDateTime createdon) {
        this.createdon = createdon;
    }

    public Optional<String> getModifiedbyOptional() {
        return Optional.ofNullable(modifiedby);
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Optional<LocalDateTime> getModifiedonOptional() {
        return Optional.ofNullable(modifiedon);
    }

    public void setModifiedon(LocalDateTime modifiedon) {
        this.modifiedon = modifiedon;
    }

    public Optional<String> getCntrprtaccOptional() {
        return Optional.ofNullable(cntrprtacc);
    }

    public void setCntrprtacc(String cntrprtacc) {
        this.cntrprtacc = cntrprtacc;
    }

    public Optional<String> getHtypeOptional() {
        return Optional.ofNullable(htype);
    }

    public void setHtype(String htype) {
        this.htype = htype;
    }

    public Optional<Boolean> getHdiscadvOptional() {
        return Optional.ofNullable(hdiscadv);
    }

    public void setHdiscadv(Boolean hdiscadv) {
        this.hdiscadv = hdiscadv;
    }

    public Optional<String> getHmfyearOptional() {
        return Optional.ofNullable(hmfyear);
    }

    public void setHmfyear(String hmfyear) {
        this.hmfyear = hmfyear;
    }

    public Optional<Integer> getHmyearOptional() {
        return Optional.ofNullable(hmyear);
    }

    public void setHmyear(Integer hmyear) {
        this.hmyear = hmyear;
    }

    public Optional<Integer> getHmmonthOptional() {
        return Optional.ofNullable(hmmonth);
    }

    public void setHmmonth(Integer hmmonth) {
        this.hmmonth = hmmonth;
    }

    public Optional<LocalDate> getHmdateOptional() {
        return Optional.ofNullable(hmdate);
    }

    public void setHmdate(LocalDate hmdate) {
        this.hmdate = hmdate;
    }

    public Optional<String> getHcstypeOptional() {
        return Optional.ofNullable(hcstype);
    }

    public void setHcstype(String hcstype) {
        this.hcstype = hcstype;
    }

    public Optional<String> getChkdigitOptional() {
        return Optional.ofNullable(chkdigit);
    }

    public void setChkdigit(String chkdigit) {
        this.chkdigit = chkdigit;
    }

    public Optional<String> getHvatmodeOptional() {
        return Optional.ofNullable(hvatmode);
    }

    public void setHvatmode(String hvatmode) {
        this.hvatmode = hvatmode;
    }
}
