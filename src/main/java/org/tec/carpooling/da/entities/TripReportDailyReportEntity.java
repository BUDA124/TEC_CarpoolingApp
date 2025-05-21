package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.TripHasTripStatusId;
import org.tec.carpooling.da.entities.embeddable.TripReportDailyReportId;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TRIPREPORTDAILYREPORT")
public class TripReportDailyReportEntity implements Identifiable<TripReportDailyReportId> {

    @EmbeddedId
    private TripReportDailyReportId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTrip")
    @JoinColumn(name = "IDTRIP", referencedColumnName = "ID", insertable = false, updatable = false)
    private TripEntity trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDailyReport")
    @JoinColumn(name = "IDDAILYREPORT", referencedColumnName = "ID", insertable = false, updatable = false)
    private DailyReportEntity dailyReport;

    @Column(name = "REPORTDATE")
    private LocalDate reportDate;

    @Column(name = "REPORTNUMBER", length = 100)
    private String reportNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public TripReportDailyReportEntity() {}

    public TripReportDailyReportEntity(TripEntity trip, DailyReportEntity dailyReport, LocalDate reportDate, String reportNumber, AuditLogEntity auditLogEntity) {
        this.id = new TripReportDailyReportId();
        this.trip = trip;
        this.dailyReport = dailyReport;
        this.reportDate = reportDate;
        this.reportNumber = reportNumber;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public TripReportDailyReportId getId() { return id; }
    public void setId(TripReportDailyReportId id) { this.id = id; }
    public TripEntity getTrip() { return trip; }
    public void setTrip(TripEntity trip) { this.trip = trip; }
    public DailyReportEntity getDailyReport() { return dailyReport; }
    public void setDailyReport(DailyReportEntity dailyReport) { this.dailyReport = dailyReport; }
    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }
    public String getReportNumber() { return reportNumber; }
    public void setReportNumber(String reportNumber) { this.reportNumber = reportNumber; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripReportDailyReportEntity that = (TripReportDailyReportEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TripReportDailyReportEntity{" +
                "id=" + id +
                ", reportDate=" + reportDate +
                ", reportNumber='" + reportNumber + '\'' +
                ", tripId=" + (trip != null ? trip.getId() : null) +
                ", dailyReportId=" + (dailyReport != null ? dailyReport.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}