package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.AttrModHasEntModId;
import java.util.Objects;

@Entity
@Table(name = "ATTRMODHASENTMOD")
public class AttrModHasEntModEntity implements Identifiable<AttrModHasEntModId> {

    @EmbeddedId
    private AttrModHasEntModId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLogBook")
    @JoinColumn(name = "IDLOGBOOK", referencedColumnName = "ID", insertable = false, updatable = false)
    private LogBookEntity logBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAttributeModified")
    @JoinColumn(name = "IDATTRIBUTEMODIFIED", referencedColumnName = "ID", insertable = false, updatable = false)
    private AttributeModifiedEntity attributeModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public AttrModHasEntModEntity() {}

    public AttrModHasEntModEntity(LogBookEntity logBookEntity, AttributeModifiedEntity attributeModifiedEntity, AuditLogEntity auditLogEntity) {
    }

    // Getters and Setters
    public AttrModHasEntModId getId() { return id; }
    public void setId(AttrModHasEntModId id) { this.id = id; }
    public LogBookEntity getLogBook() { return logBook; }
    public void setLogBook(LogBookEntity logBook) { this.logBook = logBook; }
    public AttributeModifiedEntity getAttributeModified() { return attributeModified; }
    public void setAttributeModified(AttributeModifiedEntity attributeModified) { this.attributeModified = attributeModified; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttrModHasEntModEntity that = (AttrModHasEntModEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "AttrModHasEntModEntity{" +
                "id=" + id +
                ", logBookId=" + (logBook != null ? logBook.getId() : null) +
                ", attributeModifiedId=" + (attributeModified != null ? attributeModified.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}