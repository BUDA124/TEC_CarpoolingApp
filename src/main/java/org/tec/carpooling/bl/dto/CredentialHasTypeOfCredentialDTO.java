package org.tec.carpooling.bl.dto;

public class CredentialHasTypeOfCredentialDTO {
    private CredentialDTO credential;
    private TypeOfCredentialDTO typeOfCredential;
    private AuditLogDTO auditLog;

    public CredentialHasTypeOfCredentialDTO() {
    }

    // Getters and Setters
    public CredentialDTO getCredential() {
        return credential;
    }

    public void setCredential(CredentialDTO credential) {
        this.credential = credential;
    }

    public TypeOfCredentialDTO getTypeOfCredential() {
        return typeOfCredential;
    }

    public void setTypeOfCredential(TypeOfCredentialDTO typeOfCredential) {
        this.typeOfCredential = typeOfCredential;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}