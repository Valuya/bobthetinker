package be.valuya.bob.core;

import java.time.LocalDateTime;

public class BobPeriod {

    private String fYear;
    private int year;
    private int month;
    private String status;
    private boolean defaultPeriod;
    private String label;
    private boolean ctrlPrint;
    private String origin;
    private int curLevel;
    private boolean salesClosed;
    private boolean purchasesClosed;
    private boolean casClosed;
    private boolean priClosed;
    private String conversion;
    private boolean fxActive;
    private boolean fxClosed;
    private boolean fxDepCalc;
    private boolean fxPriGen;
    private boolean fxNew;
    private boolean fxMod;
    private LocalDateTime fxModificationDateTime;
    private LocalDateTime fxDepDateTime;
    private LocalDateTime fxPriDateTime;
    private String heading1;
    private String heading2;

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

    public boolean isCtrlPrint() {
        return ctrlPrint;
    }

    public void setCtrlPrint(boolean ctrlPrint) {
        this.ctrlPrint = ctrlPrint;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getCurLevel() {
        return curLevel;
    }

    public void setCurLevel(int curLevel) {
        this.curLevel = curLevel;
    }

    public boolean isSalesClosed() {
        return salesClosed;
    }

    public void setSalesClosed(boolean salesClosed) {
        this.salesClosed = salesClosed;
    }

    public boolean isPurchasesClosed() {
        return purchasesClosed;
    }

    public void setPurchasesClosed(boolean purchasesClosed) {
        this.purchasesClosed = purchasesClosed;
    }

    public boolean isCasClosed() {
        return casClosed;
    }

    public void setCasClosed(boolean casClosed) {
        this.casClosed = casClosed;
    }

    public boolean isPriClosed() {
        return priClosed;
    }

    public void setPriClosed(boolean priClosed) {
        this.priClosed = priClosed;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public boolean isFxActive() {
        return fxActive;
    }

    public void setFxActive(boolean fxActive) {
        this.fxActive = fxActive;
    }

    public boolean isFxClosed() {
        return fxClosed;
    }

    public void setFxClosed(boolean fxClosed) {
        this.fxClosed = fxClosed;
    }

    public boolean isFxDepCalc() {
        return fxDepCalc;
    }

    public void setFxDepCalc(boolean fxDepCalc) {
        this.fxDepCalc = fxDepCalc;
    }

    public boolean isFxPriGen() {
        return fxPriGen;
    }

    public void setFxPriGen(boolean fxPriGen) {
        this.fxPriGen = fxPriGen;
    }

    public boolean isFxNew() {
        return fxNew;
    }

    public void setFxNew(boolean fxNew) {
        this.fxNew = fxNew;
    }

    public boolean isFxMod() {
        return fxMod;
    }

    public void setFxMod(boolean fxMod) {
        this.fxMod = fxMod;
    }

    public LocalDateTime getFxModificationDateTime() {
        return fxModificationDateTime;
    }

    public void setFxModificationDateTime(LocalDateTime fxModificationDateTime) {
        this.fxModificationDateTime = fxModificationDateTime;
    }

    public LocalDateTime getFxDepDateTime() {
        return fxDepDateTime;
    }

    public void setFxDepDateTime(LocalDateTime fxDepDateTime) {
        this.fxDepDateTime = fxDepDateTime;
    }

    public LocalDateTime getFxPriDateTime() {
        return fxPriDateTime;
    }

    public void setFxPriDateTime(LocalDateTime fxPriDateTime) {
        this.fxPriDateTime = fxPriDateTime;
    }

    public String getHeading1() {
        return heading1;
    }

    public void setHeading1(String heading1) {
        this.heading1 = heading1;
    }

    public String getHeading2() {
        return heading2;
    }

    public void setHeading2(String heading2) {
        this.heading2 = heading2;
    }
}
