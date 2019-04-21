package be.valuya.bob.core.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Optional;

public class BobPeriod {

    @NotNull
    @Size(min = 1)
    private String fYear;
    @NotNull
    @Min(1)
    private int year;
    @Min(0)
    private int month;
    @NotNull
    @Size(min = 1)
    private String status;
    private boolean defaultPeriod;
    @NotNull
    @Size(min = 1)
    private String label;

    private Optional<Boolean> ctrlPrintOptional;
    private Optional<String> originOptional;
    private Optional<Integer> curLevelOptional;
    private Optional<Boolean> salesClosedOptional;
    private Optional<Boolean> purchasesClosedOptional;
    private Optional<Boolean> casClosedOptional;
    private Optional<Boolean> priClosedOptional;
    private Optional<String> conversionOptional;
    private Optional<Boolean> fxActiveOptional;
    private Optional<Boolean> fxClosedOptional;
    private Optional<Boolean> fxDepcalcOptional;
    private Optional<Boolean> fxPrigenOptional;
    private Optional<Boolean> fxNewOptional;
    private Optional<Boolean> fxModOptional;
    private Optional<LocalDateTime> fxModificationDateTimeOptional;
    private Optional<LocalDateTime> fxDepDateTimeOptional;
    private Optional<LocalDateTime> fxpriDateTimeOptional;
    private Optional<String> heading1Optional;
    private Optional<String> heading2Optional;

    public String getfYear() {
        return fYear;
    }

    public void setfYear(String fYear) {
        this.fYear = fYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDefaultPeriod() {
        return defaultPeriod;
    }

    public void setDefaultPeriod(boolean defaultPeriod) {
        this.defaultPeriod = defaultPeriod;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Optional<Boolean> getCtrlPrintOptional() {
        return ctrlPrintOptional;
    }

    public void setCtrlPrintOptional(Optional<Boolean> ctrlPrintOptional) {
        this.ctrlPrintOptional = ctrlPrintOptional;
    }

    public Optional<String> getOriginOptional() {
        return originOptional;
    }

    public void setOriginOptional(Optional<String> originOptional) {
        this.originOptional = originOptional;
    }

    public Optional<Integer> getCurLevelOptional() {
        return curLevelOptional;
    }

    public void setCurLevelOptional(Optional<Integer> curLevelOptional) {
        this.curLevelOptional = curLevelOptional;
    }

    public Optional<Boolean> getSalesClosedOptional() {
        return salesClosedOptional;
    }

    public void setSalesClosedOptional(Optional<Boolean> salesClosedOptional) {
        this.salesClosedOptional = salesClosedOptional;
    }

    public Optional<Boolean> getPurchasesClosedOptional() {
        return purchasesClosedOptional;
    }

    public void setPurchasesClosedOptional(Optional<Boolean> purchasesClosedOptional) {
        this.purchasesClosedOptional = purchasesClosedOptional;
    }

    public Optional<Boolean> getCasClosedOptional() {
        return casClosedOptional;
    }

    public void setCasClosedOptional(Optional<Boolean> casClosedOptional) {
        this.casClosedOptional = casClosedOptional;
    }

    public Optional<Boolean> getPriClosedOptional() {
        return priClosedOptional;
    }

    public void setPriClosedOptional(Optional<Boolean> priClosedOptional) {
        this.priClosedOptional = priClosedOptional;
    }

    public Optional<String> getConversionOptional() {
        return conversionOptional;
    }

    public void setConversionOptional(Optional<String> conversionOptional) {
        this.conversionOptional = conversionOptional;
    }

    public Optional<Boolean> getFxActiveOptional() {
        return fxActiveOptional;
    }

    public void setFxActiveOptional(Optional<Boolean> fxActiveOptional) {
        this.fxActiveOptional = fxActiveOptional;
    }

    public Optional<Boolean> getFxClosedOptional() {
        return fxClosedOptional;
    }

    public void setFxClosedOptional(Optional<Boolean> fxClosedOptional) {
        this.fxClosedOptional = fxClosedOptional;
    }

    public Optional<Boolean> getFxDepcalcOptional() {
        return fxDepcalcOptional;
    }

    public void setFxDepcalcOptional(Optional<Boolean> fxDepcalcOptional) {
        this.fxDepcalcOptional = fxDepcalcOptional;
    }

    public Optional<Boolean> getFxPrigenOptional() {
        return fxPrigenOptional;
    }

    public void setFxPrigenOptional(Optional<Boolean> fxPrigenOptional) {
        this.fxPrigenOptional = fxPrigenOptional;
    }

    public Optional<Boolean> getFxNewOptional() {
        return fxNewOptional;
    }

    public void setFxNewOptional(Optional<Boolean> fxNewOptional) {
        this.fxNewOptional = fxNewOptional;
    }

    public Optional<Boolean> getFxModOptional() {
        return fxModOptional;
    }

    public void setFxModOptional(Optional<Boolean> fxModOptional) {
        this.fxModOptional = fxModOptional;
    }

    public Optional<LocalDateTime> getFxModificationDateTimeOptional() {
        return fxModificationDateTimeOptional;
    }

    public void setFxModificationDateTimeOptional(Optional<LocalDateTime> fxModificationDateTimeOptional) {
        this.fxModificationDateTimeOptional = fxModificationDateTimeOptional;
    }

    public Optional<LocalDateTime> getFxDepDateTimeOptional() {
        return fxDepDateTimeOptional;
    }

    public void setFxDepDateTimeOptional(Optional<LocalDateTime> fxDepDateTimeOptional) {
        this.fxDepDateTimeOptional = fxDepDateTimeOptional;
    }

    public Optional<LocalDateTime> getFxpriDateTimeOptional() {
        return fxpriDateTimeOptional;
    }

    public void setFxpriDateTimeOptional(Optional<LocalDateTime> fxpriDateTimeOptional) {
        this.fxpriDateTimeOptional = fxpriDateTimeOptional;
    }

    public Optional<String> getHeading1Optional() {
        return heading1Optional;
    }

    public void setHeading1Optional(Optional<String> heading1Optional) {
        this.heading1Optional = heading1Optional;
    }

    public Optional<String> getHeading2Optional() {
        return heading2Optional;
    }

    public void setHeading2Optional(Optional<String> heading2Optional) {
        this.heading2Optional = heading2Optional;
    }
}
