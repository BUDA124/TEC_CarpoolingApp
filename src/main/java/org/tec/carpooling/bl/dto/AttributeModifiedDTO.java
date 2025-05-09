package org.tec.carpooling.bl.dto;

public class AttributeModifiedDTO {
    private Long id;
    private String newValue;
    private String oldValue;
    private String attributeName;
    private EntityModifiedDTO entityModified; // For idEntityModified
    private AuditLogDTO auditLog;

    public AttributeModifiedDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
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