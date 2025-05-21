package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "ENTITYMODIFIED")
@SequenceGenerator(name = "seq_entitymodified_gen", sequenceName = "SEQ_ENTITYMODIFIED", allocationSize = 1)
public class EntityModifiedEntity implements Identifiable<Long> {

    public EntityModifiedEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_entitymodified_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ENTITYNAME", nullable = false, length = 100)
    private String entityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public EntityModifiedEntity(String entityName, AuditLogEntity auditLogEntity) {
        this.entityName = entityName;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
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
        EntityModifiedEntity that = (EntityModifiedEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "EntityModifiedEntity{" +
                "id=" + id +
                ", entityName='" + entityName + '\'' +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}