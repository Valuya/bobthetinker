package be.valuya.bob.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class BobCompany {

    private String cId;
    private Optional<String> cCustomerTypeOptional;
    private Optional<String> cSupplierTypeOptional;
    private Optional<String> cName1Optional;
    private Optional<String> cName2Optional;
    private Optional<String> cAddress1Optional;
    private Optional<String> cAddress2Optional;
    private Optional<String> cZipCodeOptional;
    private Optional<String> sVatNumberOptional;
    private Optional<String> cLocalityOptional;
    private Optional<String> cCountryOptional;
    private Optional<String> cCouHeadOptional;
    private Optional<String> cLanguageOptional;
    private Optional<Boolean> cisPersOptional;
    private Optional<String> cProCategoryOptional;
    private Optional<String> cSupplierCategoryOptional;
    private Optional<String> cCustomerCategoryOptional;
    private Optional<String> cCurrencyOptional;
    private Optional<String> cVatRefOptional;
    private Optional<String> cVatNumberOptional;
    private Optional<String> cVatCategoryOptional;
    private Optional<String> cPhoneNumberOptional;
    private Optional<String> cFaxNumberOptional;
    //    private Optional<> cMemoOptional;
    private Optional<Integer> cCustomertLstMnoOptional;
    private Optional<Integer> cSupplierLstMnoOptional;
    private Optional<String> cCustomerVNat1Optional;
    private Optional<String> cCustomerVNat2Optional;
    private Optional<Double> cCustomerVatCmpOptional;
    private Optional<String> cSupplierVNat1Optional;
    private Optional<String> cSupplierVNat2Optional;
    private Optional<Double> cSupplierVatCmpOptional;
    private Optional<String> cCustomerCtrAccOptional;
    private Optional<String> cSupplierCtrAccOptional;
    private Optional<String> cCustomerImputAOptional;
    private Optional<String> cSupplierImputAOptional;
    private Optional<String> cSupplierCatCommOptional;
    private Optional<String> cCountryCodeOptional;
    private Optional<String> cBankCodeOptional;
    private Optional<String> cBankNumberOptional;
    private Optional<String> cBankIbanOptional;
    private Optional<String> dbnkQualifOptional;
    private Optional<String> cCustomerPayDelayOptional;
    private Optional<Double> cSupplierPayDiscOptional;
    private Optional<Integer> cSupplierDiscDelOptional;
    private Optional<String> cSupplierDiscCalcTypeOptional;
    private Optional<Boolean> cSupplierDiscAdvOptional;
    private Optional<String> cSupplierDiscAdvTypeOptional;
    private Optional<Boolean> cSupplierDiscAdvExtraOptional;
    private Optional<Double> cSupplierDiscAdvExtraDiscOptional;
    private Optional<Double> cTempCustomerDebitOptional;
    private Optional<Double> cTempCustomerCreditOptional;
    private Optional<Double> cTempSupplierDebitOptional;
    private Optional<Double> cTempSupplierCreditOptional;
    private Optional<LocalDateTime> cRemLastDateTimeOptional;
    private Optional<Integer> cRemLastLevelOptional;
    private Optional<String> cRemCcreMcatStriOptional;
    private Optional<String> cRemStatUsStriOptional;
    private Optional<String> cRemSendByStriOptional;
    private Optional<Double> cTotInteRestCurrOptional;
    private Optional<String> authorOptional;
    private Optional<String> cNationalRegistryIdOptional;
    private Optional<String> cSupplierPayDelayOptional;
    private Optional<Boolean> cManualPayOptional;
    private Optional<Boolean> cIndepPayeeOptional;
    private Optional<Double> cIndepPayAmnOptional;
    private Optional<String> cIndepPImputOptional;
    private Optional<String> cBankCivilityOptional;
    private Optional<String> cBankLangCodeOptional;
    private Optional<String> cBankBankDbkOptional;
    private Optional<Boolean> cBankNeverRegroupOptional;
    private Optional<Boolean> cFactoringOptional;
    private Optional<String> cFactorIdOptional;
    private Optional<Boolean> cBankOrderPayOptional;
    private Optional<String> cBankOrderPayNumberOptional;
    private Optional<String> cBankOrderMandateOptional;
    private Optional<Boolean> cBankOrderB2BOptional;
    private Optional<Boolean> cBankOrderMigrTodoOptional;
    private Optional<Boolean> cBankOrderBMigrDoneOptional;
    private Optional<LocalDate> cBankOrderMigrDateOptional;
    private Optional<Double> cCustomerPayDiscOptional;
    private Optional<Integer> cCustomerDiscDelOptional;
    private Optional<Boolean> cCustomerDiscAdvOptional;
    private Optional<String> cCustomerDiscAdvTypeOptional;
    private Optional<String> cCustomerTempLidOptional;
    private Optional<String> cSupplierTempLidOptional;
    private Optional<String> emailAddressOptional;
    private Optional<Boolean> checkEmailIOnvOptional;
    private Optional<String> emailAddressInvOptional;
    private Optional<String> httpAddressOptional;
    private Optional<String> cSupplierJobOptional;
    private Optional<String> createdByOptional;
    private Optional<LocalDateTime> createdOnOptional;
    private Optional<String> modifiedByOptional;
    private Optional<LocalDateTime> modifiedOnOptional;
    private Optional<String> trftStatusOptional;
    private Optional<String> stationIdOptional;
    private Optional<String> cBusinessNumberOptional;
    private Optional<Boolean> cCustomerInWarningOptional;
    private Optional<Boolean> cSupplierInWarningOptional;
    private Optional<Boolean> cCustomerReadOnlyOptional;
    private Optional<Boolean> cSupplierReadOnlyOptional;
    private Optional<Boolean> cCustomerBlockedOptional;
    private Optional<Boolean> cSupplierBlockedOptional;
    private Optional<Boolean> cCustomerSecretOptional;
    private Optional<Boolean> cSupplierSecretOptional;
    private Optional<Boolean> cCustomerSleepingOptional;
    private Optional<Boolean> cSupplierSleepingOptional;
    private Optional<String> cBankTypePayOptional;
    private Optional<LocalDateTime> boardDateTimeOptional;
    private Optional<Boolean> boardPublicOptional;
    private Optional<String> fiscalControlOptional;
    private Optional<String> fiscalControlPhoneNumberOptional;
    private Optional<String> folderIdOptional;
    private Optional<LocalDateTime> fYearEndDateTimeOptional;
    private Optional<Boolean> manFolderOptional;
    private Optional<Boolean> manReportOptional;
    private Optional<String> revisorOptional;
    private Optional<String> userRespOptional;
    private Optional<String> vatControlOptional;
    private Optional<String> vatControlPhoneNumberOptional;
    private Optional<String> vatTypeOptional;
    private Optional<String> tremExtOptional;
    private Optional<String> trftSiteOptional;
    private Optional<Integer> trftVersOptional;
    private Optional<Boolean> decSiteSelectedOptional;
    private Optional<Integer> cExtRefOptional;
    private Optional<String> cKeyIdOptional;
    private Optional<String> cEnsignLinkOptional;
    private Optional<String> c28150SportOptional;
    private Optional<String> crmCustomerOptional;
    private Optional<String> crmSupplierOptional;
    private Optional<String> uniqueIdOptional;
    private Optional<Integer> cIdDbFactOptional;
    private Optional<String> cCustomerEInvRecMethOptional;
    private Optional<String> cCustomerEInvSystemOptional;
    private Optional<LocalDateTime> cCustomerEInvFromDateTimeOptional;
    private Optional<String> cSupplierEInvRecMethOptional;
    private Optional<String> cSupplierEInvSystemOptional;
    private Optional<LocalDateTime> cSupplierEInvFromDateTimeOptional;
    private Optional<String> cCustomerInviteStatusOptional;
    private Optional<String> eInvIdOptional;
    private Optional<String> cSupplierEComIdOptional;
    private Optional<String> cCustomerEComIdOptional;
    //    private Optional<> cAcqTemplateOptional;
    private Optional<LocalDateTime> cCustomerEInvLastChangeDateTimeOptional;
    private Optional<LocalDateTime> cSupplierEInvLastChangeDateTimeOptional;
    private Optional<Boolean> cCustomerEInvExcludeOptional;
    private Optional<Boolean> cSupplierEInvExcludeOptional;
    private Optional<String> cSupplierInviteStatusOptional;
    private Optional<Integer> freedelityIdOptional;
    private Optional<String> cManecomIdOptional;
    private Optional<Boolean> dgdComplexOptional;
    private Optional<String> cCustomerDematKeyWordOptional;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public Optional<String> getcCustomerTypeOptional() {
        return cCustomerTypeOptional;
    }

    public void setcCustomerTypeOptional(Optional<String> cCustomerTypeOptional) {
        this.cCustomerTypeOptional = cCustomerTypeOptional;
    }

    public Optional<String> getcSupplierTypeOptional() {
        return cSupplierTypeOptional;
    }

    public void setcSupplierTypeOptional(Optional<String> cSupplierTypeOptional) {
        this.cSupplierTypeOptional = cSupplierTypeOptional;
    }

    public Optional<String> getcName1Optional() {
        return cName1Optional;
    }

    public void setcName1Optional(Optional<String> cName1Optional) {
        this.cName1Optional = cName1Optional;
    }

    public Optional<String> getcName2Optional() {
        return cName2Optional;
    }

    public void setcName2Optional(Optional<String> cName2Optional) {
        this.cName2Optional = cName2Optional;
    }

    public Optional<String> getcAddress1Optional() {
        return cAddress1Optional;
    }

    public void setcAddress1Optional(Optional<String> cAddress1Optional) {
        this.cAddress1Optional = cAddress1Optional;
    }

    public Optional<String> getcAddress2Optional() {
        return cAddress2Optional;
    }

    public void setcAddress2Optional(Optional<String> cAddress2Optional) {
        this.cAddress2Optional = cAddress2Optional;
    }

    public Optional<String> getcZipCodeOptional() {
        return cZipCodeOptional;
    }

    public void setcZipCodeOptional(Optional<String> cZipCodeOptional) {
        this.cZipCodeOptional = cZipCodeOptional;
    }

    public Optional<String> getsVatNumberOptional() {
        return sVatNumberOptional;
    }

    public void setsVatNumberOptional(Optional<String> sVatNumberOptional) {
        this.sVatNumberOptional = sVatNumberOptional;
    }

    public Optional<String> getcLocalityOptional() {
        return cLocalityOptional;
    }

    public void setcLocalityOptional(Optional<String> cLocalityOptional) {
        this.cLocalityOptional = cLocalityOptional;
    }

    public Optional<String> getcCountryOptional() {
        return cCountryOptional;
    }

    public void setcCountryOptional(Optional<String> cCountryOptional) {
        this.cCountryOptional = cCountryOptional;
    }

    public Optional<String> getcCouHeadOptional() {
        return cCouHeadOptional;
    }

    public void setcCouHeadOptional(Optional<String> cCouHeadOptional) {
        this.cCouHeadOptional = cCouHeadOptional;
    }

    public Optional<String> getcLanguageOptional() {
        return cLanguageOptional;
    }

    public void setcLanguageOptional(Optional<String> cLanguageOptional) {
        this.cLanguageOptional = cLanguageOptional;
    }

    public Optional<Boolean> getCisPersOptional() {
        return cisPersOptional;
    }

    public void setCisPersOptional(Optional<Boolean> cisPersOptional) {
        this.cisPersOptional = cisPersOptional;
    }

    public Optional<String> getcProCategoryOptional() {
        return cProCategoryOptional;
    }

    public void setcProCategoryOptional(Optional<String> cProCategoryOptional) {
        this.cProCategoryOptional = cProCategoryOptional;
    }

    public Optional<String> getcSupplierCategoryOptional() {
        return cSupplierCategoryOptional;
    }

    public void setcSupplierCategoryOptional(Optional<String> cSupplierCategoryOptional) {
        this.cSupplierCategoryOptional = cSupplierCategoryOptional;
    }

    public Optional<String> getcCustomerCategoryOptional() {
        return cCustomerCategoryOptional;
    }

    public void setcCustomerCategoryOptional(Optional<String> cCustomerCategoryOptional) {
        this.cCustomerCategoryOptional = cCustomerCategoryOptional;
    }

    public Optional<String> getcCurrencyOptional() {
        return cCurrencyOptional;
    }

    public void setcCurrencyOptional(Optional<String> cCurrencyOptional) {
        this.cCurrencyOptional = cCurrencyOptional;
    }

    public Optional<String> getcVatRefOptional() {
        return cVatRefOptional;
    }

    public void setcVatRefOptional(Optional<String> cVatRefOptional) {
        this.cVatRefOptional = cVatRefOptional;
    }

    public Optional<String> getcVatNumberOptional() {
        return cVatNumberOptional;
    }

    public void setcVatNumberOptional(Optional<String> cVatNumberOptional) {
        this.cVatNumberOptional = cVatNumberOptional;
    }

    public Optional<String> getcVatCategoryOptional() {
        return cVatCategoryOptional;
    }

    public void setcVatCategoryOptional(Optional<String> cVatCategoryOptional) {
        this.cVatCategoryOptional = cVatCategoryOptional;
    }

    public Optional<String> getcPhoneNumberOptional() {
        return cPhoneNumberOptional;
    }

    public void setcPhoneNumberOptional(Optional<String> cPhoneNumberOptional) {
        this.cPhoneNumberOptional = cPhoneNumberOptional;
    }

    public Optional<String> getcFaxNumberOptional() {
        return cFaxNumberOptional;
    }

    public void setcFaxNumberOptional(Optional<String> cFaxNumberOptional) {
        this.cFaxNumberOptional = cFaxNumberOptional;
    }

    public Optional<Integer> getcCustomertLstMnoOptional() {
        return cCustomertLstMnoOptional;
    }

    public void setcCustomertLstMnoOptional(Optional<Integer> cCustomertLstMnoOptional) {
        this.cCustomertLstMnoOptional = cCustomertLstMnoOptional;
    }

    public Optional<Integer> getcSupplierLstMnoOptional() {
        return cSupplierLstMnoOptional;
    }

    public void setcSupplierLstMnoOptional(Optional<Integer> cSupplierLstMnoOptional) {
        this.cSupplierLstMnoOptional = cSupplierLstMnoOptional;
    }

    public Optional<String> getcCustomerVNat1Optional() {
        return cCustomerVNat1Optional;
    }

    public void setcCustomerVNat1Optional(Optional<String> cCustomerVNat1Optional) {
        this.cCustomerVNat1Optional = cCustomerVNat1Optional;
    }

    public Optional<String> getcCustomerVNat2Optional() {
        return cCustomerVNat2Optional;
    }

    public void setcCustomerVNat2Optional(Optional<String> cCustomerVNat2Optional) {
        this.cCustomerVNat2Optional = cCustomerVNat2Optional;
    }

    public Optional<Double> getcCustomerVatCmpOptional() {
        return cCustomerVatCmpOptional;
    }

    public void setcCustomerVatCmpOptional(Optional<Double> cCustomerVatCmpOptional) {
        this.cCustomerVatCmpOptional = cCustomerVatCmpOptional;
    }

    public Optional<String> getcSupplierVNat1Optional() {
        return cSupplierVNat1Optional;
    }

    public void setcSupplierVNat1Optional(Optional<String> cSupplierVNat1Optional) {
        this.cSupplierVNat1Optional = cSupplierVNat1Optional;
    }

    public Optional<String> getcSupplierVNat2Optional() {
        return cSupplierVNat2Optional;
    }

    public void setcSupplierVNat2Optional(Optional<String> cSupplierVNat2Optional) {
        this.cSupplierVNat2Optional = cSupplierVNat2Optional;
    }

    public Optional<Double> getcSupplierVatCmpOptional() {
        return cSupplierVatCmpOptional;
    }

    public void setcSupplierVatCmpOptional(Optional<Double> cSupplierVatCmpOptional) {
        this.cSupplierVatCmpOptional = cSupplierVatCmpOptional;
    }

    public Optional<String> getcCustomerCtrAccOptional() {
        return cCustomerCtrAccOptional;
    }

    public void setcCustomerCtrAccOptional(Optional<String> cCustomerCtrAccOptional) {
        this.cCustomerCtrAccOptional = cCustomerCtrAccOptional;
    }

    public Optional<String> getcSupplierCtrAccOptional() {
        return cSupplierCtrAccOptional;
    }

    public void setcSupplierCtrAccOptional(Optional<String> cSupplierCtrAccOptional) {
        this.cSupplierCtrAccOptional = cSupplierCtrAccOptional;
    }

    public Optional<String> getcCustomerImputAOptional() {
        return cCustomerImputAOptional;
    }

    public void setcCustomerImputAOptional(Optional<String> cCustomerImputAOptional) {
        this.cCustomerImputAOptional = cCustomerImputAOptional;
    }

    public Optional<String> getcSupplierImputAOptional() {
        return cSupplierImputAOptional;
    }

    public void setcSupplierImputAOptional(Optional<String> cSupplierImputAOptional) {
        this.cSupplierImputAOptional = cSupplierImputAOptional;
    }

    public Optional<String> getcSupplierCatCommOptional() {
        return cSupplierCatCommOptional;
    }

    public void setcSupplierCatCommOptional(Optional<String> cSupplierCatCommOptional) {
        this.cSupplierCatCommOptional = cSupplierCatCommOptional;
    }

    public Optional<String> getcCountryCodeOptional() {
        return cCountryCodeOptional;
    }

    public void setcCountryCodeOptional(Optional<String> cCountryCodeOptional) {
        this.cCountryCodeOptional = cCountryCodeOptional;
    }

    public Optional<String> getcBankCodeOptional() {
        return cBankCodeOptional;
    }

    public void setcBankCodeOptional(Optional<String> cBankCodeOptional) {
        this.cBankCodeOptional = cBankCodeOptional;
    }

    public Optional<String> getcBankNumberOptional() {
        return cBankNumberOptional;
    }

    public void setcBankNumberOptional(Optional<String> cBankNumberOptional) {
        this.cBankNumberOptional = cBankNumberOptional;
    }

    public Optional<String> getcBankIbanOptional() {
        return cBankIbanOptional;
    }

    public void setcBankIbanOptional(Optional<String> cBankIbanOptional) {
        this.cBankIbanOptional = cBankIbanOptional;
    }

    public Optional<String> getDbnkQualifOptional() {
        return dbnkQualifOptional;
    }

    public void setDbnkQualifOptional(Optional<String> dbnkQualifOptional) {
        this.dbnkQualifOptional = dbnkQualifOptional;
    }

    public Optional<String> getcCustomerPayDelayOptional() {
        return cCustomerPayDelayOptional;
    }

    public void setcCustomerPayDelayOptional(Optional<String> cCustomerPayDelayOptional) {
        this.cCustomerPayDelayOptional = cCustomerPayDelayOptional;
    }

    public Optional<Double> getcSupplierPayDiscOptional() {
        return cSupplierPayDiscOptional;
    }

    public void setcSupplierPayDiscOptional(Optional<Double> cSupplierPayDiscOptional) {
        this.cSupplierPayDiscOptional = cSupplierPayDiscOptional;
    }

    public Optional<Integer> getcSupplierDiscDelOptional() {
        return cSupplierDiscDelOptional;
    }

    public void setcSupplierDiscDelOptional(Optional<Integer> cSupplierDiscDelOptional) {
        this.cSupplierDiscDelOptional = cSupplierDiscDelOptional;
    }

    public Optional<String> getcSupplierDiscCalcTypeOptional() {
        return cSupplierDiscCalcTypeOptional;
    }

    public void setcSupplierDiscCalcTypeOptional(Optional<String> cSupplierDiscCalcTypeOptional) {
        this.cSupplierDiscCalcTypeOptional = cSupplierDiscCalcTypeOptional;
    }

    public Optional<Boolean> getcSupplierDiscAdvOptional() {
        return cSupplierDiscAdvOptional;
    }

    public void setcSupplierDiscAdvOptional(Optional<Boolean> cSupplierDiscAdvOptional) {
        this.cSupplierDiscAdvOptional = cSupplierDiscAdvOptional;
    }

    public Optional<String> getcSupplierDiscAdvTypeOptional() {
        return cSupplierDiscAdvTypeOptional;
    }

    public void setcSupplierDiscAdvTypeOptional(Optional<String> cSupplierDiscAdvTypeOptional) {
        this.cSupplierDiscAdvTypeOptional = cSupplierDiscAdvTypeOptional;
    }

    public Optional<Boolean> getcSupplierDiscAdvExtraOptional() {
        return cSupplierDiscAdvExtraOptional;
    }

    public void setcSupplierDiscAdvExtraOptional(Optional<Boolean> cSupplierDiscAdvExtraOptional) {
        this.cSupplierDiscAdvExtraOptional = cSupplierDiscAdvExtraOptional;
    }

    public Optional<Double> getcSupplierDiscAdvExtraDiscOptional() {
        return cSupplierDiscAdvExtraDiscOptional;
    }

    public void setcSupplierDiscAdvExtraDiscOptional(Optional<Double> cSupplierDiscAdvExtraDiscOptional) {
        this.cSupplierDiscAdvExtraDiscOptional = cSupplierDiscAdvExtraDiscOptional;
    }

    public Optional<Double> getcTempCustomerDebitOptional() {
        return cTempCustomerDebitOptional;
    }

    public void setcTempCustomerDebitOptional(Optional<Double> cTempCustomerDebitOptional) {
        this.cTempCustomerDebitOptional = cTempCustomerDebitOptional;
    }

    public Optional<Double> getcTempCustomerCreditOptional() {
        return cTempCustomerCreditOptional;
    }

    public void setcTempCustomerCreditOptional(Optional<Double> cTempCustomerCreditOptional) {
        this.cTempCustomerCreditOptional = cTempCustomerCreditOptional;
    }

    public Optional<Double> getcTempSupplierDebitOptional() {
        return cTempSupplierDebitOptional;
    }

    public void setcTempSupplierDebitOptional(Optional<Double> cTempSupplierDebitOptional) {
        this.cTempSupplierDebitOptional = cTempSupplierDebitOptional;
    }

    public Optional<Double> getcTempSupplierCreditOptional() {
        return cTempSupplierCreditOptional;
    }

    public void setcTempSupplierCreditOptional(Optional<Double> cTempSupplierCreditOptional) {
        this.cTempSupplierCreditOptional = cTempSupplierCreditOptional;
    }

    public Optional<LocalDateTime> getcRemLastDateTimeOptional() {
        return cRemLastDateTimeOptional;
    }

    public void setcRemLastDateTimeOptional(Optional<LocalDateTime> cRemLastDateTimeOptional) {
        this.cRemLastDateTimeOptional = cRemLastDateTimeOptional;
    }

    public Optional<Integer> getcRemLastLevelOptional() {
        return cRemLastLevelOptional;
    }

    public void setcRemLastLevelOptional(Optional<Integer> cRemLastLevelOptional) {
        this.cRemLastLevelOptional = cRemLastLevelOptional;
    }

    public Optional<String> getcRemCcreMcatStriOptional() {
        return cRemCcreMcatStriOptional;
    }

    public void setcRemCcreMcatStriOptional(Optional<String> cRemCcreMcatStriOptional) {
        this.cRemCcreMcatStriOptional = cRemCcreMcatStriOptional;
    }

    public Optional<String> getcRemStatUsStriOptional() {
        return cRemStatUsStriOptional;
    }

    public void setcRemStatUsStriOptional(Optional<String> cRemStatUsStriOptional) {
        this.cRemStatUsStriOptional = cRemStatUsStriOptional;
    }

    public Optional<String> getcRemSendByStriOptional() {
        return cRemSendByStriOptional;
    }

    public void setcRemSendByStriOptional(Optional<String> cRemSendByStriOptional) {
        this.cRemSendByStriOptional = cRemSendByStriOptional;
    }

    public Optional<Double> getcTotInteRestCurrOptional() {
        return cTotInteRestCurrOptional;
    }

    public void setcTotInteRestCurrOptional(Optional<Double> cTotInteRestCurrOptional) {
        this.cTotInteRestCurrOptional = cTotInteRestCurrOptional;
    }

    public Optional<String> getAuthorOptional() {
        return authorOptional;
    }

    public void setAuthorOptional(Optional<String> authorOptional) {
        this.authorOptional = authorOptional;
    }

    public Optional<String> getcNationalRegistryIdOptional() {
        return cNationalRegistryIdOptional;
    }

    public void setcNationalRegistryIdOptional(Optional<String> cNationalRegistryIdOptional) {
        this.cNationalRegistryIdOptional = cNationalRegistryIdOptional;
    }

    public Optional<String> getcSupplierPayDelayOptional() {
        return cSupplierPayDelayOptional;
    }

    public void setcSupplierPayDelayOptional(Optional<String> cSupplierPayDelayOptional) {
        this.cSupplierPayDelayOptional = cSupplierPayDelayOptional;
    }

    public Optional<Boolean> getcManualPayOptional() {
        return cManualPayOptional;
    }

    public void setcManualPayOptional(Optional<Boolean> cManualPayOptional) {
        this.cManualPayOptional = cManualPayOptional;
    }

    public Optional<Boolean> getcIndepPayeeOptional() {
        return cIndepPayeeOptional;
    }

    public void setcIndepPayeeOptional(Optional<Boolean> cIndepPayeeOptional) {
        this.cIndepPayeeOptional = cIndepPayeeOptional;
    }

    public Optional<Double> getcIndepPayAmnOptional() {
        return cIndepPayAmnOptional;
    }

    public void setcIndepPayAmnOptional(Optional<Double> cIndepPayAmnOptional) {
        this.cIndepPayAmnOptional = cIndepPayAmnOptional;
    }

    public Optional<String> getcIndepPImputOptional() {
        return cIndepPImputOptional;
    }

    public void setcIndepPImputOptional(Optional<String> cIndepPImputOptional) {
        this.cIndepPImputOptional = cIndepPImputOptional;
    }

    public Optional<String> getcBankCivilityOptional() {
        return cBankCivilityOptional;
    }

    public void setcBankCivilityOptional(Optional<String> cBankCivilityOptional) {
        this.cBankCivilityOptional = cBankCivilityOptional;
    }

    public Optional<String> getcBankLangCodeOptional() {
        return cBankLangCodeOptional;
    }

    public void setcBankLangCodeOptional(Optional<String> cBankLangCodeOptional) {
        this.cBankLangCodeOptional = cBankLangCodeOptional;
    }

    public Optional<String> getcBankBankDbkOptional() {
        return cBankBankDbkOptional;
    }

    public void setcBankBankDbkOptional(Optional<String> cBankBankDbkOptional) {
        this.cBankBankDbkOptional = cBankBankDbkOptional;
    }

    public Optional<Boolean> getcBankNeverRegroupOptional() {
        return cBankNeverRegroupOptional;
    }

    public void setcBankNeverRegroupOptional(Optional<Boolean> cBankNeverRegroupOptional) {
        this.cBankNeverRegroupOptional = cBankNeverRegroupOptional;
    }

    public Optional<Boolean> getcFactoringOptional() {
        return cFactoringOptional;
    }

    public void setcFactoringOptional(Optional<Boolean> cFactoringOptional) {
        this.cFactoringOptional = cFactoringOptional;
    }

    public Optional<String> getcFactorIdOptional() {
        return cFactorIdOptional;
    }

    public void setcFactorIdOptional(Optional<String> cFactorIdOptional) {
        this.cFactorIdOptional = cFactorIdOptional;
    }

    public Optional<Boolean> getcBankOrderPayOptional() {
        return cBankOrderPayOptional;
    }

    public void setcBankOrderPayOptional(Optional<Boolean> cBankOrderPayOptional) {
        this.cBankOrderPayOptional = cBankOrderPayOptional;
    }

    public Optional<String> getcBankOrderPayNumberOptional() {
        return cBankOrderPayNumberOptional;
    }

    public void setcBankOrderPayNumberOptional(Optional<String> cBankOrderPayNumberOptional) {
        this.cBankOrderPayNumberOptional = cBankOrderPayNumberOptional;
    }

    public Optional<String> getcBankOrderMandateOptional() {
        return cBankOrderMandateOptional;
    }

    public void setcBankOrderMandateOptional(Optional<String> cBankOrderMandateOptional) {
        this.cBankOrderMandateOptional = cBankOrderMandateOptional;
    }

    public Optional<Boolean> getcBankOrderB2BOptional() {
        return cBankOrderB2BOptional;
    }

    public void setcBankOrderB2BOptional(Optional<Boolean> cBankOrderB2BOptional) {
        this.cBankOrderB2BOptional = cBankOrderB2BOptional;
    }

    public Optional<Boolean> getcBankOrderMigrTodoOptional() {
        return cBankOrderMigrTodoOptional;
    }

    public void setcBankOrderMigrTodoOptional(Optional<Boolean> cBankOrderMigrTodoOptional) {
        this.cBankOrderMigrTodoOptional = cBankOrderMigrTodoOptional;
    }

    public Optional<Boolean> getcBankOrderBMigrDoneOptional() {
        return cBankOrderBMigrDoneOptional;
    }

    public void setcBankOrderBMigrDoneOptional(Optional<Boolean> cBankOrderBMigrDoneOptional) {
        this.cBankOrderBMigrDoneOptional = cBankOrderBMigrDoneOptional;
    }

    public Optional<LocalDate> getcBankOrderMigrDateOptional() {
        return cBankOrderMigrDateOptional;
    }

    public void setcBankOrderMigrDateOptional(Optional<LocalDate> cBankOrderMigrDateOptional) {
        this.cBankOrderMigrDateOptional = cBankOrderMigrDateOptional;
    }

    public Optional<Double> getcCustomerPayDiscOptional() {
        return cCustomerPayDiscOptional;
    }

    public void setcCustomerPayDiscOptional(Optional<Double> cCustomerPayDiscOptional) {
        this.cCustomerPayDiscOptional = cCustomerPayDiscOptional;
    }

    public Optional<Integer> getcCustomerDiscDelOptional() {
        return cCustomerDiscDelOptional;
    }

    public void setcCustomerDiscDelOptional(Optional<Integer> cCustomerDiscDelOptional) {
        this.cCustomerDiscDelOptional = cCustomerDiscDelOptional;
    }

    public Optional<Boolean> getcCustomerDiscAdvOptional() {
        return cCustomerDiscAdvOptional;
    }

    public void setcCustomerDiscAdvOptional(Optional<Boolean> cCustomerDiscAdvOptional) {
        this.cCustomerDiscAdvOptional = cCustomerDiscAdvOptional;
    }

    public Optional<String> getcCustomerDiscAdvTypeOptional() {
        return cCustomerDiscAdvTypeOptional;
    }

    public void setcCustomerDiscAdvTypeOptional(Optional<String> cCustomerDiscAdvTypeOptional) {
        this.cCustomerDiscAdvTypeOptional = cCustomerDiscAdvTypeOptional;
    }

    public Optional<String> getcCustomerTempLidOptional() {
        return cCustomerTempLidOptional;
    }

    public void setcCustomerTempLidOptional(Optional<String> cCustomerTempLidOptional) {
        this.cCustomerTempLidOptional = cCustomerTempLidOptional;
    }

    public Optional<String> getcSupplierTempLidOptional() {
        return cSupplierTempLidOptional;
    }

    public void setcSupplierTempLidOptional(Optional<String> cSupplierTempLidOptional) {
        this.cSupplierTempLidOptional = cSupplierTempLidOptional;
    }

    public Optional<String> getEmailAddressOptional() {
        return emailAddressOptional;
    }

    public void setEmailAddressOptional(Optional<String> emailAddressOptional) {
        this.emailAddressOptional = emailAddressOptional;
    }

    public Optional<Boolean> getCheckEmailIOnvOptional() {
        return checkEmailIOnvOptional;
    }

    public void setCheckEmailIOnvOptional(Optional<Boolean> checkEmailIOnvOptional) {
        this.checkEmailIOnvOptional = checkEmailIOnvOptional;
    }

    public Optional<String> getEmailAddressInvOptional() {
        return emailAddressInvOptional;
    }

    public void setEmailAddressInvOptional(Optional<String> emailAddressInvOptional) {
        this.emailAddressInvOptional = emailAddressInvOptional;
    }

    public Optional<String> getHttpAddressOptional() {
        return httpAddressOptional;
    }

    public void setHttpAddressOptional(Optional<String> httpAddressOptional) {
        this.httpAddressOptional = httpAddressOptional;
    }

    public Optional<String> getcSupplierJobOptional() {
        return cSupplierJobOptional;
    }

    public void setcSupplierJobOptional(Optional<String> cSupplierJobOptional) {
        this.cSupplierJobOptional = cSupplierJobOptional;
    }

    public Optional<String> getCreatedByOptional() {
        return createdByOptional;
    }

    public void setCreatedByOptional(Optional<String> createdByOptional) {
        this.createdByOptional = createdByOptional;
    }

    public Optional<LocalDateTime> getCreatedOnOptional() {
        return createdOnOptional;
    }

    public void setCreatedOnOptional(Optional<LocalDateTime> createdOnOptional) {
        this.createdOnOptional = createdOnOptional;
    }

    public Optional<String> getModifiedByOptional() {
        return modifiedByOptional;
    }

    public void setModifiedByOptional(Optional<String> modifiedByOptional) {
        this.modifiedByOptional = modifiedByOptional;
    }

    public Optional<LocalDateTime> getModifiedOnOptional() {
        return modifiedOnOptional;
    }

    public void setModifiedOnOptional(Optional<LocalDateTime> modifiedOnOptional) {
        this.modifiedOnOptional = modifiedOnOptional;
    }

    public Optional<String> getTrftStatusOptional() {
        return trftStatusOptional;
    }

    public void setTrftStatusOptional(Optional<String> trftStatusOptional) {
        this.trftStatusOptional = trftStatusOptional;
    }

    public Optional<String> getStationIdOptional() {
        return stationIdOptional;
    }

    public void setStationIdOptional(Optional<String> stationIdOptional) {
        this.stationIdOptional = stationIdOptional;
    }

    public Optional<String> getcBusinessNumberOptional() {
        return cBusinessNumberOptional;
    }

    public void setcBusinessNumberOptional(Optional<String> cBusinessNumberOptional) {
        this.cBusinessNumberOptional = cBusinessNumberOptional;
    }

    public Optional<Boolean> getcCustomerInWarningOptional() {
        return cCustomerInWarningOptional;
    }

    public void setcCustomerInWarningOptional(Optional<Boolean> cCustomerInWarningOptional) {
        this.cCustomerInWarningOptional = cCustomerInWarningOptional;
    }

    public Optional<Boolean> getcSupplierInWarningOptional() {
        return cSupplierInWarningOptional;
    }

    public void setcSupplierInWarningOptional(Optional<Boolean> cSupplierInWarningOptional) {
        this.cSupplierInWarningOptional = cSupplierInWarningOptional;
    }

    public Optional<Boolean> getcCustomerReadOnlyOptional() {
        return cCustomerReadOnlyOptional;
    }

    public void setcCustomerReadOnlyOptional(Optional<Boolean> cCustomerReadOnlyOptional) {
        this.cCustomerReadOnlyOptional = cCustomerReadOnlyOptional;
    }

    public Optional<Boolean> getcSupplierReadOnlyOptional() {
        return cSupplierReadOnlyOptional;
    }

    public void setcSupplierReadOnlyOptional(Optional<Boolean> cSupplierReadOnlyOptional) {
        this.cSupplierReadOnlyOptional = cSupplierReadOnlyOptional;
    }

    public Optional<Boolean> getcCustomerBlockedOptional() {
        return cCustomerBlockedOptional;
    }

    public void setcCustomerBlockedOptional(Optional<Boolean> cCustomerBlockedOptional) {
        this.cCustomerBlockedOptional = cCustomerBlockedOptional;
    }

    public Optional<Boolean> getcSupplierBlockedOptional() {
        return cSupplierBlockedOptional;
    }

    public void setcSupplierBlockedOptional(Optional<Boolean> cSupplierBlockedOptional) {
        this.cSupplierBlockedOptional = cSupplierBlockedOptional;
    }

    public Optional<Boolean> getcCustomerSecretOptional() {
        return cCustomerSecretOptional;
    }

    public void setcCustomerSecretOptional(Optional<Boolean> cCustomerSecretOptional) {
        this.cCustomerSecretOptional = cCustomerSecretOptional;
    }

    public Optional<Boolean> getcSupplierSecretOptional() {
        return cSupplierSecretOptional;
    }

    public void setcSupplierSecretOptional(Optional<Boolean> cSupplierSecretOptional) {
        this.cSupplierSecretOptional = cSupplierSecretOptional;
    }

    public Optional<Boolean> getcCustomerSleepingOptional() {
        return cCustomerSleepingOptional;
    }

    public void setcCustomerSleepingOptional(Optional<Boolean> cCustomerSleepingOptional) {
        this.cCustomerSleepingOptional = cCustomerSleepingOptional;
    }

    public Optional<Boolean> getcSupplierSleepingOptional() {
        return cSupplierSleepingOptional;
    }

    public void setcSupplierSleepingOptional(Optional<Boolean> cSupplierSleepingOptional) {
        this.cSupplierSleepingOptional = cSupplierSleepingOptional;
    }

    public Optional<String> getcBankTypePayOptional() {
        return cBankTypePayOptional;
    }

    public void setcBankTypePayOptional(Optional<String> cBankTypePayOptional) {
        this.cBankTypePayOptional = cBankTypePayOptional;
    }

    public Optional<LocalDateTime> getBoardDateTimeOptional() {
        return boardDateTimeOptional;
    }

    public void setBoardDateTimeOptional(Optional<LocalDateTime> boardDateTimeOptional) {
        this.boardDateTimeOptional = boardDateTimeOptional;
    }

    public Optional<Boolean> getBoardPublicOptional() {
        return boardPublicOptional;
    }

    public void setBoardPublicOptional(Optional<Boolean> boardPublicOptional) {
        this.boardPublicOptional = boardPublicOptional;
    }

    public Optional<String> getFiscalControlOptional() {
        return fiscalControlOptional;
    }

    public void setFiscalControlOptional(Optional<String> fiscalControlOptional) {
        this.fiscalControlOptional = fiscalControlOptional;
    }

    public Optional<String> getFiscalControlPhoneNumberOptional() {
        return fiscalControlPhoneNumberOptional;
    }

    public void setFiscalControlPhoneNumberOptional(Optional<String> fiscalControlPhoneNumberOptional) {
        this.fiscalControlPhoneNumberOptional = fiscalControlPhoneNumberOptional;
    }

    public Optional<String> getFolderIdOptional() {
        return folderIdOptional;
    }

    public void setFolderIdOptional(Optional<String> folderIdOptional) {
        this.folderIdOptional = folderIdOptional;
    }

    public Optional<LocalDateTime> getfYearEndDateTimeOptional() {
        return fYearEndDateTimeOptional;
    }

    public void setfYearEndDateTimeOptional(Optional<LocalDateTime> fYearEndDateTimeOptional) {
        this.fYearEndDateTimeOptional = fYearEndDateTimeOptional;
    }

    public Optional<Boolean> getManFolderOptional() {
        return manFolderOptional;
    }

    public void setManFolderOptional(Optional<Boolean> manFolderOptional) {
        this.manFolderOptional = manFolderOptional;
    }

    public Optional<Boolean> getManReportOptional() {
        return manReportOptional;
    }

    public void setManReportOptional(Optional<Boolean> manReportOptional) {
        this.manReportOptional = manReportOptional;
    }

    public Optional<String> getRevisorOptional() {
        return revisorOptional;
    }

    public void setRevisorOptional(Optional<String> revisorOptional) {
        this.revisorOptional = revisorOptional;
    }

    public Optional<String> getUserRespOptional() {
        return userRespOptional;
    }

    public void setUserRespOptional(Optional<String> userRespOptional) {
        this.userRespOptional = userRespOptional;
    }

    public Optional<String> getVatControlOptional() {
        return vatControlOptional;
    }

    public void setVatControlOptional(Optional<String> vatControlOptional) {
        this.vatControlOptional = vatControlOptional;
    }

    public Optional<String> getVatControlPhoneNumberOptional() {
        return vatControlPhoneNumberOptional;
    }

    public void setVatControlPhoneNumberOptional(Optional<String> vatControlPhoneNumberOptional) {
        this.vatControlPhoneNumberOptional = vatControlPhoneNumberOptional;
    }

    public Optional<String> getVatTypeOptional() {
        return vatTypeOptional;
    }

    public void setVatTypeOptional(Optional<String> vatTypeOptional) {
        this.vatTypeOptional = vatTypeOptional;
    }

    public Optional<String> getTremExtOptional() {
        return tremExtOptional;
    }

    public void setTremExtOptional(Optional<String> tremExtOptional) {
        this.tremExtOptional = tremExtOptional;
    }

    public Optional<String> getTrftSiteOptional() {
        return trftSiteOptional;
    }

    public void setTrftSiteOptional(Optional<String> trftSiteOptional) {
        this.trftSiteOptional = trftSiteOptional;
    }

    public Optional<Integer> getTrftVersOptional() {
        return trftVersOptional;
    }

    public void setTrftVersOptional(Optional<Integer> trftVersOptional) {
        this.trftVersOptional = trftVersOptional;
    }

    public Optional<Boolean> getDecSiteSelectedOptional() {
        return decSiteSelectedOptional;
    }

    public void setDecSiteSelectedOptional(Optional<Boolean> decSiteSelectedOptional) {
        this.decSiteSelectedOptional = decSiteSelectedOptional;
    }

    public Optional<Integer> getcExtRefOptional() {
        return cExtRefOptional;
    }

    public void setcExtRefOptional(Optional<Integer> cExtRefOptional) {
        this.cExtRefOptional = cExtRefOptional;
    }

    public Optional<String> getcKeyIdOptional() {
        return cKeyIdOptional;
    }

    public void setcKeyIdOptional(Optional<String> cKeyIdOptional) {
        this.cKeyIdOptional = cKeyIdOptional;
    }

    public Optional<String> getcEnsignLinkOptional() {
        return cEnsignLinkOptional;
    }

    public void setcEnsignLinkOptional(Optional<String> cEnsignLinkOptional) {
        this.cEnsignLinkOptional = cEnsignLinkOptional;
    }

    public Optional<String> getC28150SportOptional() {
        return c28150SportOptional;
    }

    public void setC28150SportOptional(Optional<String> c28150SportOptional) {
        this.c28150SportOptional = c28150SportOptional;
    }

    public Optional<String> getCrmCustomerOptional() {
        return crmCustomerOptional;
    }

    public void setCrmCustomerOptional(Optional<String> crmCustomerOptional) {
        this.crmCustomerOptional = crmCustomerOptional;
    }

    public Optional<String> getCrmSupplierOptional() {
        return crmSupplierOptional;
    }

    public void setCrmSupplierOptional(Optional<String> crmSupplierOptional) {
        this.crmSupplierOptional = crmSupplierOptional;
    }

    public Optional<String> getUniqueIdOptional() {
        return uniqueIdOptional;
    }

    public void setUniqueIdOptional(Optional<String> uniqueIdOptional) {
        this.uniqueIdOptional = uniqueIdOptional;
    }

    public Optional<Integer> getcIdDbFactOptional() {
        return cIdDbFactOptional;
    }

    public void setcIdDbFactOptional(Optional<Integer> cIdDbFactOptional) {
        this.cIdDbFactOptional = cIdDbFactOptional;
    }

    public Optional<String> getcCustomerEInvRecMethOptional() {
        return cCustomerEInvRecMethOptional;
    }

    public void setcCustomerEInvRecMethOptional(Optional<String> cCustomerEInvRecMethOptional) {
        this.cCustomerEInvRecMethOptional = cCustomerEInvRecMethOptional;
    }

    public Optional<String> getcCustomerEInvSystemOptional() {
        return cCustomerEInvSystemOptional;
    }

    public void setcCustomerEInvSystemOptional(Optional<String> cCustomerEInvSystemOptional) {
        this.cCustomerEInvSystemOptional = cCustomerEInvSystemOptional;
    }

    public Optional<LocalDateTime> getcCustomerEInvFromDateTimeOptional() {
        return cCustomerEInvFromDateTimeOptional;
    }

    public void setcCustomerEInvFromDateTimeOptional(Optional<LocalDateTime> cCustomerEInvFromDateTimeOptional) {
        this.cCustomerEInvFromDateTimeOptional = cCustomerEInvFromDateTimeOptional;
    }

    public Optional<String> getcSupplierEInvRecMethOptional() {
        return cSupplierEInvRecMethOptional;
    }

    public void setcSupplierEInvRecMethOptional(Optional<String> cSupplierEInvRecMethOptional) {
        this.cSupplierEInvRecMethOptional = cSupplierEInvRecMethOptional;
    }

    public Optional<String> getcSupplierEInvSystemOptional() {
        return cSupplierEInvSystemOptional;
    }

    public void setcSupplierEInvSystemOptional(Optional<String> cSupplierEInvSystemOptional) {
        this.cSupplierEInvSystemOptional = cSupplierEInvSystemOptional;
    }

    public Optional<LocalDateTime> getcSupplierEInvFromDateTimeOptional() {
        return cSupplierEInvFromDateTimeOptional;
    }

    public void setcSupplierEInvFromDateTimeOptional(Optional<LocalDateTime> cSupplierEInvFromDateTimeOptional) {
        this.cSupplierEInvFromDateTimeOptional = cSupplierEInvFromDateTimeOptional;
    }

    public Optional<String> getcCustomerInviteStatusOptional() {
        return cCustomerInviteStatusOptional;
    }

    public void setcCustomerInviteStatusOptional(Optional<String> cCustomerInviteStatusOptional) {
        this.cCustomerInviteStatusOptional = cCustomerInviteStatusOptional;
    }

    public Optional<String> geteInvIdOptional() {
        return eInvIdOptional;
    }

    public void seteInvIdOptional(Optional<String> eInvIdOptional) {
        this.eInvIdOptional = eInvIdOptional;
    }

    public Optional<String> getcSupplierEComIdOptional() {
        return cSupplierEComIdOptional;
    }

    public void setcSupplierEComIdOptional(Optional<String> cSupplierEComIdOptional) {
        this.cSupplierEComIdOptional = cSupplierEComIdOptional;
    }

    public Optional<String> getcCustomerEComIdOptional() {
        return cCustomerEComIdOptional;
    }

    public void setcCustomerEComIdOptional(Optional<String> cCustomerEComIdOptional) {
        this.cCustomerEComIdOptional = cCustomerEComIdOptional;
    }

    public Optional<LocalDateTime> getcCustomerEInvLastChangeDateTimeOptional() {
        return cCustomerEInvLastChangeDateTimeOptional;
    }

    public void setcCustomerEInvLastChangeDateTimeOptional(Optional<LocalDateTime> cCustomerEInvLastChangeDateTimeOptional) {
        this.cCustomerEInvLastChangeDateTimeOptional = cCustomerEInvLastChangeDateTimeOptional;
    }

    public Optional<LocalDateTime> getcSupplierEInvLastChangeDateTimeOptional() {
        return cSupplierEInvLastChangeDateTimeOptional;
    }

    public void setcSupplierEInvLastChangeDateTimeOptional(Optional<LocalDateTime> cSupplierEInvLastChangeDateTimeOptional) {
        this.cSupplierEInvLastChangeDateTimeOptional = cSupplierEInvLastChangeDateTimeOptional;
    }

    public Optional<Boolean> getcCustomerEInvExcludeOptional() {
        return cCustomerEInvExcludeOptional;
    }

    public void setcCustomerEInvExcludeOptional(Optional<Boolean> cCustomerEInvExcludeOptional) {
        this.cCustomerEInvExcludeOptional = cCustomerEInvExcludeOptional;
    }

    public Optional<Boolean> getcSupplierEInvExcludeOptional() {
        return cSupplierEInvExcludeOptional;
    }

    public void setcSupplierEInvExcludeOptional(Optional<Boolean> cSupplierEInvExcludeOptional) {
        this.cSupplierEInvExcludeOptional = cSupplierEInvExcludeOptional;
    }

    public Optional<String> getcSupplierInviteStatusOptional() {
        return cSupplierInviteStatusOptional;
    }

    public void setcSupplierInviteStatusOptional(Optional<String> cSupplierInviteStatusOptional) {
        this.cSupplierInviteStatusOptional = cSupplierInviteStatusOptional;
    }

    public Optional<Integer> getFreedelityIdOptional() {
        return freedelityIdOptional;
    }

    public void setFreedelityIdOptional(Optional<Integer> freedelityIdOptional) {
        this.freedelityIdOptional = freedelityIdOptional;
    }

    public Optional<String> getcManecomIdOptional() {
        return cManecomIdOptional;
    }

    public void setcManecomIdOptional(Optional<String> cManecomIdOptional) {
        this.cManecomIdOptional = cManecomIdOptional;
    }

    public Optional<Boolean> getDgdComplexOptional() {
        return dgdComplexOptional;
    }

    public void setDgdComplexOptional(Optional<Boolean> dgdComplexOptional) {
        this.dgdComplexOptional = dgdComplexOptional;
    }

    public Optional<String> getcCustomerDematKeyWordOptional() {
        return cCustomerDematKeyWordOptional;
    }

    public void setcCustomerDematKeyWordOptional(Optional<String> cCustomerDematKeyWordOptional) {
        this.cCustomerDematKeyWordOptional = cCustomerDematKeyWordOptional;
    }

    public Optional<String> getcSupplierDematKeyWordOptional() {
        return cSupplierDematKeyWordOptional;
    }

    public void setcSupplierDematKeyWordOptional(Optional<String> cSupplierDematKeyWordOptional) {
        this.cSupplierDematKeyWordOptional = cSupplierDematKeyWordOptional;
    }

    private Optional<String> cSupplierDematKeyWordOptional;


}
