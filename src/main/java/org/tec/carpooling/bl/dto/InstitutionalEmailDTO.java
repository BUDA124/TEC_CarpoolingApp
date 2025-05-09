package org.tec.carpooling.bl.dto;

public class InstitutionalEmailDTO {
    private Long id;
    private String emailAddress;
    private PersonalUserDTO user;
    private AuditLogDTO auditLog;

    public InstitutionalEmailDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public PersonalUserDTO getUser() {
        return user;
    }

    public void setUser(PersonalUserDTO user) {
        this.user = user;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}