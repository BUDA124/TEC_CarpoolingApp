package org.tec.carpooling.bl.dto;

public class EmailDTO { // For Person
    private Long id;
    private String emailAddress;
    private PersonDTO person;
    private AuditLogDTO auditLog;

    public EmailDTO() {
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