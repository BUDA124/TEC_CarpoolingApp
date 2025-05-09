package org.tec.carpooling.bl.dto;

// Represents DRIVER table. PK is idPerson.
public class DriverDTO {
    private Long id; // This ID corresponds to Person.id (from DRIVER.IDPERSON column)
    private AuditLogDTO auditLog;

    public DriverDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}