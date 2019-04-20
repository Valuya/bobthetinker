package be.valuya.bob.core.reader;

import be.valuya.advantaje.core.AdvantajeRecord;
import be.valuya.bob.core.domain.BobAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class BobAccountRecordReader {

    public BobAccount readAccount(AdvantajeRecord advantajeRecord) {
        String aid = advantajeRecord.getValue("AID"); //: (STRING, 10): 1
        Optional<Boolean> aistitle = advantajeRecord.getValueOptional("AISTITLE"); //: (LOGICAL, 1): true
        Optional<String> heading1 = advantajeRecord.getValueOptional("HEADING1"); //: (STRING, 40): Fonds propres
        Optional<String> heading2 = advantajeRecord.getValueOptional("HEADING2"); //: (STRING, 40): Eigen vermogen
        Optional<String> longheading1 = advantajeRecord.getValueOptional("LONGHEADING1"); //: (STRING, 120): Fonds propres, provisions pour risques et charges et dettes à plus d'un an
        Optional<String> longheading2 = advantajeRecord.getValueOptional("LONGHEADING2"); //: (STRING, 120): Eigen vermogen, voorzieningen voor risico's en kosten en schulden op meer dan een jaar
        Optional<String> asecondid = advantajeRecord.getValueOptional("ASECONDID"); //: (STRING, 10):
        Optional<String> afree = advantajeRecord.getValueOptional("AFREE"); //: (STRING, 40):
        Optional<String> acat = advantajeRecord.getValueOptional("ACAT"); //: (STRING, 3):
        Optional<String> aintcat = advantajeRecord.getValueOptional("AINTCAT"); //: (STRING, 10):
        Optional<String> acatcomm = advantajeRecord.getValueOptional("ACATCOMM"); //: (STRING, 1):
        Optional<String> adbcd = advantajeRecord.getValueOptional("ADBCD"); //: (STRING, 1): C
        Optional<Boolean> aiscost = advantajeRecord.getValueOptional("AISCOST"); //: (LOGICAL, 1): [-]
        Optional<String> avattype = advantajeRecord.getValueOptional("AVATTYPE"); //: (STRING, 1):
        Optional<String> avatenat1 = advantajeRecord.getValueOptional("AVATENAT1"); //: (STRING, 3):
        Optional<String> avatenat2 = advantajeRecord.getValueOptional("AVATENAT2"); //: (STRING, 3):
        Optional<Double> avatecmp = advantajeRecord.getValueOptional("AVATECMP"); //: (DOUBLE, 8): [-]
        Optional<String> avatnnat1 = advantajeRecord.getValueOptional("AVATNNAT1"); //: (STRING, 3):
        Optional<String> avatnnat2 = advantajeRecord.getValueOptional("AVATNNAT2"); //: (STRING, 3):
        Optional<Double> avatncmp = advantajeRecord.getValueOptional("AVATNCMP"); //: (DOUBLE, 8): [-]
        Optional<String> avatinat1 = advantajeRecord.getValueOptional("AVATINAT1"); //: (STRING, 3):
        Optional<String> avatinat2 = advantajeRecord.getValueOptional("AVATINAT2"); //: (STRING, 3):
        Optional<Double> avaticmp = advantajeRecord.getValueOptional("AVATICMP"); //: (DOUBLE, 8): [-]
        Optional<Boolean> aissummary = advantajeRecord.getValueOptional("AISSUMMARY"); //: (LOGICAL, 1): false
        Optional<Boolean> aisstatus = advantajeRecord.getValueOptional("AISSTATUS"); //: (LOGICAL, 1): [-]
        Optional<Boolean> aisreadonl = advantajeRecord.getValueOptional("AISREADONL"); //: (LOGICAL, 1): false
        Optional<Boolean> aissecret = advantajeRecord.getValueOptional("AISSECRET"); //: (LOGICAL, 1): false
        Optional<Boolean> vtravfa = advantajeRecord.getValueOptional("VTRAVFA"); //: (LOGICAL, 1): [-]
        Optional<Boolean> aismatch = advantajeRecord.getValueOptional("AISMATCH"); //: (LOGICAL, 1): false
        Optional<String> depacc = advantajeRecord.getValueOptional("DEPACC"); //: (STRING, 10):
        Optional<String> provacc = advantajeRecord.getValueOptional("PROVACC"); //: (STRING, 10):
        Optional<Boolean> hisintrastat = advantajeRecord.getValueOptional("HISINTRASTAT"); //: (LOGICAL, 1): [-]
        Optional<Integer> amatchno = advantajeRecord.getValueOptional("AMATCHNO"); //: (INTEGER, 4): [-]
        Optional<String> abalance = advantajeRecord.getValueOptional("ABALANCE"); //: (STRING, 10): LIABILIT
        Optional<String> arem = advantajeRecord.getValueOptional("AREM"); //: (STRING, 35):
        Optional<Boolean> avatcas = advantajeRecord.getValueOptional("AVATCAS"); //: (LOGICAL, 1): [-]
        Optional<Boolean> acctsecondid = advantajeRecord.getValueOptional("ACCTSECONDID"); //: (LOGICAL, 1): [-]
        Optional<byte[]> amemo = advantajeRecord.getValueOptional("AMEMO"); //: (BINARY, 9): [B@3cda1055
        Optional<Double> prcndcharges = advantajeRecord.getValueOptional("PRCNDCHARGES"); //: (DOUBLE, 8): 0.0
        Optional<Double> prcprivate = advantajeRecord.getValueOptional("PRCPRIVATE"); //: (DOUBLE, 8): 0.0
        Optional<String> typendcharges = advantajeRecord.getValueOptional("TYPENDCHARGES"); //: (STRING, 12):
        Optional<String> createdby = advantajeRecord.getValueOptional("CREATEDBY"); //: (STRING, 10): LICOPPE
        Optional<LocalDateTime> createdon = advantajeRecord.getValueOptional("CREATEDON"); //: (TIMESTAMP, 8): 2017-01-18T14:04:14.595
        Optional<String> modifiedby = advantajeRecord.getValueOptional("MODIFIEDBY"); //: (STRING, 10): LICOPPE
        Optional<LocalDateTime> modifiedon = advantajeRecord.getValueOptional("MODIFIEDON"); //: (TIMESTAMP, 8): 2017-01-18T14:04:14.595
        Optional<String> trftstatus = advantajeRecord.getValueOptional("TRFTSTATUS"); //: (STRING, 3): A
        Optional<String> stationid = advantajeRecord.getValueOptional("STATIONID"); //: (STRING, 3):
        Optional<String> afixtype = advantajeRecord.getValueOptional("AFIXTYPE"); //: (STRING, 10):
        Optional<Boolean> asleeping = advantajeRecord.getValueOptional("ASLEEPING"); //: (LOGICAL, 1): false
        Optional<Boolean> discadvnot = advantajeRecord.getValueOptional("DISCADVNOT"); //: (LOGICAL, 1): false
        Optional<String> subtype = advantajeRecord.getValueOptional("SUBTYPE"); //: (STRING, 10): LEQUITY
        Optional<String> provaccexc = advantajeRecord.getValueOptional("PROVACCEXC"); //: (STRING, 10):
        Optional<String> annexid = advantajeRecord.getValueOptional("ANNEXID"); //: (STRING, 15):
        Optional<String> altacct = advantajeRecord.getValueOptional("ALTACCT"); //: (STRING, 10):
        Optional<String> aautoop = advantajeRecord.getValueOptional("AAUTOOP"); //: (STRING, 2):
        Optional<String> aoldid = advantajeRecord.getValueOptional("AOLDID"); //: (STRING, 10):
        Optional<String> aprivaccount = advantajeRecord.getValueOptional("APRIVACCOUNT"); //: (STRING, 10):
        Optional<String> oldheading1 = advantajeRecord.getValueOptional("OLDHEADING1"); //: (STRING, 40):
        Optional<String> oldheading2 = advantajeRecord.getValueOptional("OLDHEADING2"); //: (STRING, 40):
        Optional<Boolean> naeprior = advantajeRecord.getValueOptional("NAEPRIOR"); //: (LOGICAL, 1): [-]
        Optional<Boolean> asynchro = advantajeRecord.getValueOptional("ASYNCHRO"); //: (LOGICAL, 1): true
        Optional<String> apcnid = advantajeRecord.getValueOptional("APCNID"); //: (STRING, 10):


        Optional<BigDecimal> avatecmpOptional  = avatecmp.map(this::toBigDecimal);
        Optional<BigDecimal> avatncmpOptional  = avatncmp.map(this::toBigDecimal);
        Optional<BigDecimal> avaticmpOptional  = avaticmp.map(this::toBigDecimal);
        Optional<BigDecimal> prcndchargesOptional  = prcndcharges.map(this::toBigDecimal);
        Optional<BigDecimal> prcprivateOptional  = prcprivate.map(this::toBigDecimal);


        BobAccount bobAccount = new BobAccount();
        bobAccount.setAid(aid);
        bobAccount.setaIsTitle(aistitle.orElse(null));
        bobAccount.setHeading1(heading1.orElse(null));
        bobAccount.setHeading2(heading2.orElse(null));
        bobAccount.setLongHeading1(longheading1.orElse(null));
        bobAccount.setLongHeading2(longheading2.orElse(null));
        bobAccount.setSecondId(asecondid.orElse(null));
        bobAccount.setFree(afree.orElse(null));
        bobAccount.setaCat(acat.orElse(null));
        bobAccount.setaIntCat(aintcat.orElse(null));
        bobAccount.setaCatComm(acatcomm.orElse(null));
        bobAccount.setAdbcd(adbcd.orElse(null));
        bobAccount.setAiscost(aiscost.orElse(null));
        bobAccount.setAvattype(avattype.orElse(null));
        bobAccount.setAvatenat1(avatenat1.orElse(null));
        bobAccount.setAvatenat2(avatenat2.orElse(null));
        bobAccount.setAvatecmp(avatecmpOptional.orElse(null));
        bobAccount.setAvatnnat1(avatnnat1.orElse(null));
        bobAccount.setAvatnnat2(avatnnat2.orElse(null));
        bobAccount.setAvatncmp(avatncmpOptional.orElse(null));
        bobAccount.setAvatinat1(avatinat1.orElse(null));
        bobAccount.setAvatinat2(avatinat2.orElse(null));
        bobAccount.setAvaticmp(avaticmpOptional.orElse(null));
        bobAccount.setAissummary(aissummary.orElse(null));
        bobAccount.setAisstatus(aisstatus.orElse(null));
        bobAccount.setAisreadonl(aisreadonl.orElse(null));
        bobAccount.setAissecret(aissecret.orElse(null));
        bobAccount.setVtravfa(vtravfa.orElse(null));
        bobAccount.setAismatch(aismatch.orElse(null));
        bobAccount.setDepacc(depacc.orElse(null));
        bobAccount.setProvacc(provacc.orElse(null));
        bobAccount.setHisintrastat(hisintrastat.orElse(null));
        bobAccount.setAmatchno(amatchno.orElse(null));
        bobAccount.setAbalance(abalance.orElse(null));
        bobAccount.setArem(arem.orElse(null));
        bobAccount.setAvatcas(avatcas.orElse(null));
        bobAccount.setAcctsecondid(acctsecondid.orElse(null));
        bobAccount.setAmemo(amemo.orElse(null));
        bobAccount.setPrcndcharges(prcndchargesOptional.orElse(null));
        bobAccount.setPrcprivate(prcprivateOptional.orElse(null));
        bobAccount.setTypendcharges(typendcharges.orElse(null));
        bobAccount.setCreatedby(createdby.orElse(null));
        bobAccount.setCreatedon(createdon.orElse(null));
        bobAccount.setModifiedby(modifiedby.orElse(null));
        bobAccount.setModifiedon(modifiedon.orElse(null));
        bobAccount.setTrftstatus(trftstatus.orElse(null));
        bobAccount.setStationid(stationid.orElse(null));
        bobAccount.setAfixtype(afixtype.orElse(null));
        bobAccount.setAsleeping(asleeping.orElse(null));
        bobAccount.setDiscadvnot(discadvnot.orElse(null));
        bobAccount.setSubtype(subtype.orElse(null));
        bobAccount.setProvaccexc(provaccexc.orElse(null));
        bobAccount.setAnnexid(annexid.orElse(null));
        bobAccount.setAltacct(altacct.orElse(null));
        bobAccount.setAautoop(aautoop.orElse(null));
        bobAccount.setAoldid(aoldid.orElse(null));
        bobAccount.setAprivaccount(aprivaccount.orElse(null));
        bobAccount.setOldheading1(oldheading1.orElse(null));
        bobAccount.setOldheading2(oldheading2.orElse(null));
        bobAccount.setNaeprior(naeprior.orElse(null));
        bobAccount.setAsynchro(asynchro.orElse(null));
        bobAccount.setApcnid(apcnid.orElse(null));
        return bobAccount;
    }

    private BigDecimal toBigDecimal(Double aDouble) {
        return BigDecimal.valueOf(aDouble);
    }
}
