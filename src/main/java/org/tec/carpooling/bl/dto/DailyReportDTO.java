package org.tec.carpooling.bl.dto;

public class DailyReportDTO {
    private Long id;
    private String reportType;
    private InstitutionDTO institution;
    private AuditLogDTO auditLog; // The daily report's own audit log for creation/update

    public DailyReportDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public InstitutionDTO getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionDTO institution) {
        this.institution = institution;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}