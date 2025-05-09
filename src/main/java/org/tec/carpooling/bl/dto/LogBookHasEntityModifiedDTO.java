package org.tec.carpooling.bl.dto;

public class LogBookHasEntityModifiedDTO {
    private LogBookDTO logBook;
    private EntityModifiedDTO entityModified;
    private AuditLogDTO auditLog;

    public LogBookHasEntityModifiedDTO() {
    }

    // Getters and Setters
    public LogBookDTO getLogBook() {
        return logBook;
    }

    public void setLogBook(LogBookDTO logBook) {
        this.logBook = logBook;
    }

    public EntityModifiedDTO getEntityModified() {
        return entityModified;
    }

    public void setEntityModified(EntityModifiedDTO entityModified) {
        this.entityModified = entityModified;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}