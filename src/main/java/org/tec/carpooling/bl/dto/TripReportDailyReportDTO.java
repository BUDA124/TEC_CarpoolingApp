package org.tec.carpooling.bl.dto;

import java.time.LocalDate;

public class TripReportDailyReportDTO {
    private TripDTO trip;
    private DailyReportDTO dailyReport;
    private LocalDate reportDate;
    private String reportNumber;
    private AuditLogDTO auditLog;

    public TripReportDailyReportDTO() {
    }

    // Getters and Setters
    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public DailyReportDTO getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReportDTO dailyReport) {
        this.dailyReport = dailyReport;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}