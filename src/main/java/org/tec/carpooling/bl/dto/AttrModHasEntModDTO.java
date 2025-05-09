package org.tec.carpooling.bl.dto;

public class AttrModHasEntModDTO {
    private LogBookDTO logBook;
    private AttributeModifiedDTO attributeModified;
    private AuditLogDTO auditLog;

    public AttrModHasEntModDTO() {
    }

    // Getters and Setters
    public LogBookDTO getLogBook() {
        return logBook;
    }

    public void setLogBook(LogBookDTO logBook) {
        this.logBook = logBook;
    }

    public AttributeModifiedDTO getAttributeModified() {
        return attributeModified;
    }

    public void setAttributeModified(AttributeModifiedDTO attributeModified) {
        this.attributeModified = attributeModified;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}