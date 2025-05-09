package org.tec.carpooling.bl.dto;

public class CredentialDTO {
    private Long id;
    private Boolean isActive;
    private String numberOfCredential;
    private PersonDTO person;
    private AuditLogDTO auditLog;

    public CredentialDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getNumberOfCredential() {
        return numberOfCredential;
    }

    public void setNumberOfCredential(String numberOfCredential) {
        this.numberOfCredential = numberOfCredential;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}