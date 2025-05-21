package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.LogBookHasEntityModifiedId;
import java.util.Objects;

@Entity
@Table(name = "LOGBOOKHASENTITYMODIFIED")
public class LogBookHasEntityModifiedEntity implements Identifiable<LogBookHasEntityModifiedId> {

    @EmbeddedId
    private LogBookHasEntityModifiedId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLogBook")
    @JoinColumn(name = "IDLOGBOOK", referencedColumnName = "ID", insertable = false, updatable = false)
    private LogBookEntity logBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEntityModified")
    @JoinColumn(name = "IDENTITYMODIFIED", referencedColumnName = "ID", insertable = false, updatable = false)
    private EntityModifiedEntity entityModified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public LogBookHasEntityModifiedEntity() {}

    public LogBookHasEntityModifiedEntity(LogBookEntity logBook, EntityModifiedEntity entityModified, AuditLogEntity auditLogEntity) {
        this.id = new LogBookHasEntityModifiedId();
        this.logBook = logBook;
        this.entityModified = entityModified;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public LogBookHasEntityModifiedId getId() { return id; }
    public void setId(LogBookHasEntityModifiedId id) { this.id = id; }
    public LogBookEntity getLogBook() { return logBook; }
    public void setLogBook(LogBookEntity logBook) { this.logBook = logBook; }
    public EntityModifiedEntity getEntityModified() { return entityModified; }
    public void setEntityModified(EntityModifiedEntity entityModified) { this.entityModified = entityModified; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogBookHasEntityModifiedEntity that = (LogBookHasEntityModifiedEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "LogBookHasEntityModifiedEntity{" +
                "id=" + id +
                ", logBookId=" + (logBook != null ? logBook.getId() : null) +
                ", entityModifiedId=" + (entityModified != null ? entityModified.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}