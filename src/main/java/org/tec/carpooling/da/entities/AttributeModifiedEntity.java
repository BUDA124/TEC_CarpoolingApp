package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "ATTRIBUTEMODIFIED")
@SequenceGenerator(name = "seq_attributemodified_gen", sequenceName = "SEQ_ATTRIBUTEMODIFIED", allocationSize = 1)
public class AttributeModifiedEntity implements Identifiable<Long> {

    public AttributeModifiedEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_attributemodified_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NEWVALUE", length = 255)
    private String newValue;

    @Column(name = "OLDVALUE", length = 255)
    private String oldValue;

    @Column(name = "ATTRIBUTENAME", nullable = false, length = 100)
    private String attributeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENTITYMODIFIED", nullable = false)
    private EntityModifiedEntity entityModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public AttributeModifiedEntity(Object o, String programado, String status, EntityModifiedEntity em1, AuditLogEntity auditLogEntity) {
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

    public EntityModifiedEntity getEntityModified() {
        return entityModified;
    }

    public void setEntityModified(EntityModifiedEntity entityModified) {
        this.entityModified = entityModified;
    }

    public AuditLogEntity getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogEntity auditLog) {
        this.auditLog = auditLog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeModifiedEntity that = (AttributeModifiedEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "AttributeModifiedEntity{" +
                "id=" + id +
                ", newValue='" + newValue + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", attributeName='" + attributeName + '\'' +
                ", entityModifiedId=" + (entityModified != null ? entityModified.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}