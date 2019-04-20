package be.valuya.bob.core;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Table ac_accoun
 */
public class BobAccount {

    @NotNull
    private String aid;

    private Boolean aIsTitle;
    private String heading1;
    private String heading2;
    private String longHeading1;
    private String longHeading2;
    private String secondId;
    private String free;
    private String aCat;
    private String aIntCat;
    private String aCatComm;
    /**
     * D or C depending on whether debit or credit account.
     */
    private String adbcd;

    private Boolean aiscost; //: (LOGICAL, 1): [-]
    private String avattype; //: (STRING, 1):
    private String avatenat1; //: (STRING, 3):
    private String avatenat2; //: (STRING, 3):
    private BigDecimal avatecmp; //: (DOUBLE, 8): [-]
    private String avatnnat1; //: (STRING, 3):
    private String avatnnat2; //: (STRING, 3):
    private BigDecimal avatncmp; //: (DOUBLE, 8): [-]
    private String avatinat1; //: (STRING, 3):
    private String avatinat2; //: (STRING, 3):
    private BigDecimal avaticmp; //: (DOUBLE, 8): [-]
    private Boolean aissummary; //: (LOGICAL, 1): false
    private Boolean aisstatus; //: (LOGICAL, 1): [-]
    private Boolean aisreadonl; //: (LOGICAL, 1): false
    private Boolean aissecret; //: (LOGICAL, 1): false
    private Boolean vtravfa; //: (LOGICAL, 1): [-]
    private Boolean aismatch; //: (LOGICAL, 1): false
    private String depacc; //: (STRING, 10):
    private String provacc; //: (STRING, 10):
    private Boolean hisintrastat; //: (LOGICAL, 1): [-]
    private Integer amatchno; //: (INTEGER, 4): [-]
    private String abalance; //: (STRING, 10): LIABILIT
    private String arem; //: (STRING, 35):
    private Boolean avatcas; //: (LOGICAL, 1): [-]
    private Boolean acctsecondid; //: (LOGICAL, 1): [-]
    private byte[] amemo; //: (BINARY, 9): [B@3cda1055
    private BigDecimal prcndcharges; //: (DOUBLE, 8): 0.0
    private BigDecimal prcprivate; //: (DOUBLE, 8): 0.0
    private String typendcharges; //: (STRING, 12):
    private String createdby; //: (STRING, 10): LICOPPE
    private LocalDateTime createdon; //: (TIMESTAMP, 8): 2017-01-18T14:04:14.595
    private String modifiedby; //: (STRING, 10): LICOPPE
    private LocalDateTime modifiedon; //: (TIMESTAMP, 8): 2017-01-18T14:04:14.595
    private String trftstatus; //: (STRING, 3): A
    private String stationid; //: (STRING, 3):
    private String afixtype; //: (STRING, 10):
    private Boolean asleeping; //: (LOGICAL, 1): false
    private Boolean discadvnot; //: (LOGICAL, 1): false
    private String subtype; //: (STRING, 10): LEQUITY
    private String provaccexc; //: (STRING, 10):
    private String annexid; //: (STRING, 15):
    private String altacct; //: (STRING, 10):
    private String aautoop; //: (STRING, 2):
    private String aoldid; //: (STRING, 10):
    private String aprivaccount; //: (STRING, 10):
    private String oldheading1; //: (STRING, 40):
    private String oldheading2; //: (STRING, 40):
    private Boolean naeprior; //: (LOGICAL, 1): [-]
    private Boolean asynchro; //: (LOGICAL, 1): true
    private String apcnid; //: (STRING, 10):

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Optional<Boolean> getaIsTitleOptional() {
        return Optional.ofNullable(aIsTitle);
    }

    public void setaIsTitle(Boolean aIsTitle) {
        this.aIsTitle = aIsTitle;
    }

    public Optional<String> getHeading1Optional() {
        return Optional.ofNullable(heading1);
    }

    public void setHeading1(String heading1) {
        this.heading1 = heading1;
    }

    public Optional<String> getHeading2Optional() {
        return Optional.ofNullable(heading2);
    }

    public void setHeading2(String heading2) {
        this.heading2 = heading2;
    }

    public Optional<String> getLongHeading1Optional() {
        return Optional.ofNullable(longHeading1);
    }

    public void setLongHeading1(String longHeading1) {
        this.longHeading1 = longHeading1;
    }

    public Optional<String> getLongHeading2Optional() {
        return Optional.ofNullable(longHeading2);
    }

    public void setLongHeading2(String longHeading2) {
        this.longHeading2 = longHeading2;
    }

    public Optional<String> getSecondIdOptional() {
        return Optional.ofNullable(secondId);
    }

    public void setSecondId(String secondId) {
        this.secondId = secondId;
    }

    public Optional<String> getFreeOptional() {
        return Optional.ofNullable(free);
    }

    public void setFree(String free) {
        this.free = free;
    }

    public Optional<String> getaCatOptional() {
        return Optional.ofNullable(aCat);
    }

    public void setaCat(String aCat) {
        this.aCat = aCat;
    }

    public Optional<String> getaIntCatOptional() {
        return Optional.ofNullable(aIntCat);
    }

    public void setaIntCat(String aIntCat) {
        this.aIntCat = aIntCat;
    }

    public Optional<String> getaCatCommOptional() {
        return Optional.ofNullable(aCatComm);
    }

    public void setaCatComm(String aCatComm) {
        this.aCatComm = aCatComm;
    }

    public Optional<String> getAdbcdOptional() {
        return Optional.ofNullable(adbcd);
    }

    public void setAdbcd(String adbcd) {
        this.adbcd = adbcd;
    }

    public Optional<Boolean> getAiscostOptional() {
        return Optional.ofNullable(aiscost);
    }

    public void setAiscost(Boolean aiscost) {
        this.aiscost = aiscost;
    }

    public Optional<String> getAvattypeOptional() {
        return Optional.ofNullable(avattype);
    }

    public void setAvattype(String avattype) {
        this.avattype = avattype;
    }

    public Optional<String> getAvatenat1Optional() {
        return Optional.ofNullable(avatenat1);
    }

    public void setAvatenat1(String avatenat1) {
        this.avatenat1 = avatenat1;
    }

    public Optional<String> getAvatenat2Optional() {
        return Optional.ofNullable(avatenat2);
    }

    public void setAvatenat2(String avatenat2) {
        this.avatenat2 = avatenat2;
    }

    public Optional<BigDecimal> getAvatecmpOptional() {
        return Optional.ofNullable(avatecmp);
    }

    public void setAvatecmp(BigDecimal avatecmp) {
        this.avatecmp = avatecmp;
    }

    public Optional<String> getAvatnnat1Optional() {
        return Optional.ofNullable(avatnnat1);
    }

    public void setAvatnnat1(String avatnnat1) {
        this.avatnnat1 = avatnnat1;
    }

    public Optional<String> getAvatnnat2Optional() {
        return Optional.ofNullable(avatnnat2);
    }

    public void setAvatnnat2(String avatnnat2) {
        this.avatnnat2 = avatnnat2;
    }

    public Optional<BigDecimal> getAvatncmpOptional() {
        return Optional.ofNullable(avatncmp);
    }

    public void setAvatncmp(BigDecimal avatncmp) {
        this.avatncmp = avatncmp;
    }

    public Optional<String> getAvatinat1Optional() {
        return Optional.ofNullable(avatinat1);
    }

    public void setAvatinat1(String avatinat1) {
        this.avatinat1 = avatinat1;
    }

    public Optional<String> getAvatinat2Optional() {
        return Optional.ofNullable(avatinat2);
    }

    public void setAvatinat2(String avatinat2) {
        this.avatinat2 = avatinat2;
    }

    public Optional<BigDecimal> getAvaticmpOptional() {
        return Optional.ofNullable(avaticmp);
    }

    public void setAvaticmp(BigDecimal avaticmp) {
        this.avaticmp = avaticmp;
    }

    public Optional<Boolean> getAissummaryOptional() {
        return Optional.ofNullable(aissummary);
    }

    public void setAissummary(Boolean aissummary) {
        this.aissummary = aissummary;
    }

    public Optional<Boolean> getAisstatusOptional() {
        return Optional.ofNullable(aisstatus);
    }

    public void setAisstatus(Boolean aisstatus) {
        this.aisstatus = aisstatus;
    }

    public Optional<Boolean> getAisreadonlOptional() {
        return Optional.ofNullable(aisreadonl);
    }

    public void setAisreadonl(Boolean aisreadonl) {
        this.aisreadonl = aisreadonl;
    }

    public Optional<Boolean> getAissecretOptional() {
        return Optional.ofNullable(aissecret);
    }

    public void setAissecret(Boolean aissecret) {
        this.aissecret = aissecret;
    }

    public Optional<Boolean> getVtravfaOptional() {
        return Optional.ofNullable(vtravfa);
    }

    public void setVtravfa(Boolean vtravfa) {
        this.vtravfa = vtravfa;
    }

    public Optional<Boolean> getAismatchOptional() {
        return Optional.ofNullable(aismatch);
    }

    public void setAismatch(Boolean aismatch) {
        this.aismatch = aismatch;
    }

    public Optional<String> getDepaccOptional() {
        return Optional.ofNullable(depacc);
    }

    public void setDepacc(String depacc) {
        this.depacc = depacc;
    }

    public Optional<String> getProvaccOptional() {
        return Optional.ofNullable(provacc);
    }

    public void setProvacc(String provacc) {
        this.provacc = provacc;
    }

    public Optional<Boolean> getHisintrastatOptional() {
        return Optional.ofNullable(hisintrastat);
    }

    public void setHisintrastat(Boolean hisintrastat) {
        this.hisintrastat = hisintrastat;
    }

    public Optional<Integer> getAmatchnoOptional() {
        return Optional.ofNullable(amatchno);
    }

    public void setAmatchno(Integer amatchno) {
        this.amatchno = amatchno;
    }

    public Optional<String> getAbalanceOptional() {
        return Optional.ofNullable(abalance);
    }

    public void setAbalance(String abalance) {
        this.abalance = abalance;
    }

    public Optional<String> getAremOptional() {
        return Optional.ofNullable(arem);
    }

    public void setArem(String arem) {
        this.arem = arem;
    }

    public Optional<Boolean> getAvatcasOptional() {
        return Optional.ofNullable(avatcas);
    }

    public void setAvatcas(Boolean avatcas) {
        this.avatcas = avatcas;
    }

    public Optional<Boolean> getAcctsecondidOptional() {
        return Optional.ofNullable(acctsecondid);
    }

    public void setAcctsecondid(Boolean acctsecondid) {
        this.acctsecondid = acctsecondid;
    }

    public Optional<byte[]> getAmemoOptional() {
        return Optional.ofNullable(amemo);
    }

    public void setAmemo(byte[] amemo) {
        this.amemo = amemo;
    }

    public Optional<BigDecimal> getPrcndchargesOptional() {
        return Optional.ofNullable(prcndcharges);
    }

    public void setPrcndcharges(BigDecimal prcndcharges) {
        this.prcndcharges = prcndcharges;
    }

    public Optional<BigDecimal> getPrcprivateOptional() {
        return Optional.ofNullable(prcprivate);
    }

    public void setPrcprivate(BigDecimal prcprivate) {
        this.prcprivate = prcprivate;
    }

    public Optional<String> getTypendchargesOptional() {
        return Optional.ofNullable(typendcharges);
    }

    public void setTypendcharges(String typendcharges) {
        this.typendcharges = typendcharges;
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

    public Optional<String> getTrftstatusOptional() {
        return Optional.ofNullable(trftstatus);
    }

    public void setTrftstatus(String trftstatus) {
        this.trftstatus = trftstatus;
    }

    public Optional<String> getStationidOptional() {
        return Optional.ofNullable(stationid);
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    public Optional<String> getAfixtypeOptional() {
        return Optional.ofNullable(afixtype);
    }

    public void setAfixtype(String afixtype) {
        this.afixtype = afixtype;
    }

    public Optional<Boolean> getAsleepingOptional() {
        return Optional.ofNullable(asleeping);
    }

    public void setAsleeping(Boolean asleeping) {
        this.asleeping = asleeping;
    }

    public Optional<Boolean> getDiscadvnotOptional() {
        return Optional.ofNullable(discadvnot);
    }

    public void setDiscadvnot(Boolean discadvnot) {
        this.discadvnot = discadvnot;
    }

    public Optional<String> getSubtypeOptional() {
        return Optional.ofNullable(subtype);
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Optional<String> getProvaccexcOptional() {
        return Optional.ofNullable(provaccexc);
    }

    public void setProvaccexc(String provaccexc) {
        this.provaccexc = provaccexc;
    }

    public Optional<String> getAnnexidOptional() {
        return Optional.ofNullable(annexid);
    }

    public void setAnnexid(String annexid) {
        this.annexid = annexid;
    }

    public Optional<String> getAltacctOptional() {
        return Optional.ofNullable(altacct);
    }

    public void setAltacct(String altacct) {
        this.altacct = altacct;
    }

    public Optional<String> getAautoopOptional() {
        return Optional.ofNullable(aautoop);
    }

    public void setAautoop(String aautoop) {
        this.aautoop = aautoop;
    }

    public Optional<String> getAoldidOptional() {
        return Optional.ofNullable(aoldid);
    }

    public void setAoldid(String aoldid) {
        this.aoldid = aoldid;
    }

    public Optional<String> getAprivaccountOptional() {
        return Optional.ofNullable(aprivaccount);
    }

    public void setAprivaccount(String aprivaccount) {
        this.aprivaccount = aprivaccount;
    }

    public Optional<String> getOldheading1Optional() {
        return Optional.ofNullable(oldheading1);
    }

    public void setOldheading1(String oldheading1) {
        this.oldheading1 = oldheading1;
    }

    public Optional<String> getOldheading2Optional() {
        return Optional.ofNullable(oldheading2);
    }

    public void setOldheading2(String oldheading2) {
        this.oldheading2 = oldheading2;
    }

    public Optional<Boolean> getNaepriorOptional() {
        return Optional.ofNullable(naeprior);
    }

    public void setNaeprior(Boolean naeprior) {
        this.naeprior = naeprior;
    }

    public Optional<Boolean> getAsynchroOptional() {
        return Optional.ofNullable(asynchro);
    }

    public void setAsynchro(Boolean asynchro) {
        this.asynchro = asynchro;
    }

    public Optional<String> getApcnidOptional() {
        return Optional.ofNullable(apcnid);
    }

    public void setApcnid(String apcnid) {
        this.apcnid = apcnid;
    }
}
