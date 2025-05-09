package org.tec.carpooling.bl.dto;

// Represents ADMINISTRATOR table. PK is idPerson.
public class AdministratorDTO {
    private Long id; // This ID corresponds to Person.id (from ADMINISTRATOR.IDPERSON column)
    private AuditLogDTO auditLog;
    // To get person details, fetch PersonDTO using this id.

    public AdministratorDTO() {
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