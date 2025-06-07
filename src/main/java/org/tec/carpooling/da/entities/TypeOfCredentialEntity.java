package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;

import org.tec.carpooling.common.utils.Auditable;
import org.tec.carpooling.common.utils.CatalogEntity;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "TYPEOFCREDENTIAL")
public class TypeOfCredentialEntity implements Identifiable<Long>, CatalogEntity {

    public TypeOfCredentialEntity() {
    }

    public TypeOfCredentialEntity(String type, AuditLogEntity auditLogEntity) {
        this.type = type;
        this.auditLog = auditLogEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE", nullable = false, length = 50)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        TypeOfCredentialEntity that = (TypeOfCredentialEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "TypeOfCredentialEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }

    @Override
    public String getName() {
        return this.type;
    }

    @Override
    public void setName(String name) {
        this.type = name;
    }
}