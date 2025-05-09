package org.tec.carpooling.bl.dto;

public class DistrictDTO {
    private Long id;
    private String name;
    private CantonDTO canton;
    private AuditLogDTO auditLog;

    public DistrictDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CantonDTO getCanton() {
        return canton;
    }

    public void setCanton(CantonDTO canton) {
        this.canton = canton;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}