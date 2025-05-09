package org.tec.carpooling.bl.dto;

public class AdminReceiveDailyReportDTO {
    private AdministratorDTO administrator; // Represents idAdministrator FK to ADMINISTRATOR.IDPERSON
    private DailyReportDTO dailyReport;
    private AuditLogDTO auditLog;

    public AdminReceiveDailyReportDTO() {
    }

    // Getters and Setters
    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorDTO administrator) {
        this.administrator = administrator;
    }

    public DailyReportDTO getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(DailyReportDTO dailyReport) {
        this.dailyReport = dailyReport;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}