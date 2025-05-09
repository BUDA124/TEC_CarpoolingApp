package org.tec.carpooling.bl.dto;

// Represents PASSENGER table. PK is idPerson.
public class PassengerDTO {
    private Long id; // This ID corresponds to Person.id (from PASSENGER.IDPERSON column)
    private AuditLogDTO auditLog;

    public PassengerDTO() {
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