package be.valuya.bob.core.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * table dm_invdoc
 * DOCID: (STRING, 36): A28E16DF-25A2-409F-A317-9CCDCB469F3E
 * FYEAR: (STRING, 5): 2017
 * YEAR: (INTEGER, 4): 2017
 * MONTH: (INTEGER, 4): 12
 * DBK: (STRING, 4): LIV
 * DOCNO: (INTEGER, 4): 201705
 * DBTYPE: (STRING, 3): CAS
 * CREATEDBY: (STRING, 10): CHRISTOF
 * CREATEDON: (TIMESTAMP, 8): 2018-03-22T08:53:15.905
 * MODIFIEDBY: (STRING, 10): CHRISTOF
 * MODIFIEDON: (TIMESTAMP, 8): 2018-03-22T08:53:15.905
 * APPCIRCUITID: (STRING, 10):
 */
public class BobDocument {
    @NotNull
    @Size(min = 1)
    private String id;
    @NotNull
    @Size(min = 1)
    private String fyear;
    @Min(1)
    private int year;
    @Min(0)
    private int month;
    @NotNull
    @Size(min = 1)
    private String dbk;
    @Min(0)
    private int docNo;

    private Optional<String> dbType;
    private Optional<String> createdBy;
    private Optional<LocalDateTime> createdOn;
    private Optional<String> modifiedBy;
    private Optional<LocalDateTime> modifiedOn;
    private Optional<String> appCirtcuitId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFyear() {
        return fyear;
    }

    public void setFyear(String fyear) {
        this.fyear = fyear;
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

    public String getDbk() {
        return dbk;
    }

    public void setDbk(String dbk) {
        this.dbk = dbk;
    }

    public int getDocNo() {
        return docNo;
    }

    public void setDocNo(int docNo) {
        this.docNo = docNo;
    }

    public Optional<String> getDbType() {
        return dbType;
    }

    public void setDbType(Optional<String> dbType) {
        this.dbType = dbType;
    }

    public Optional<String> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Optional<String> createdBy) {
        this.createdBy = createdBy;
    }

    public Optional<String> getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Optional<String> modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Optional<LocalDateTime> getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Optional<LocalDateTime> createdOn) {
        this.createdOn = createdOn;
    }

    public Optional<LocalDateTime> getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Optional<LocalDateTime> modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Optional<String> getAppCirtcuitId() {
        return appCirtcuitId;
    }

    public void setAppCirtcuitId(Optional<String> appCirtcuitId) {
        this.appCirtcuitId = appCirtcuitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BobDocument that = (BobDocument) o;
        return year == that.year &&
                month == that.month &&
                docNo == that.docNo &&
                Objects.equals(id, that.id) &&
                Objects.equals(fyear, that.fyear) &&
                Objects.equals(dbk, that.dbk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fyear, year, month, dbk, docNo);
    }

    @Override
    public String toString() {
        return "BobDocument{" +
                "id='" + id + '\'' +
                ", fyear='" + fyear + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", dbk='" + dbk + '\'' +
                ", docNo=" + docNo +
                '}';
    }
}
