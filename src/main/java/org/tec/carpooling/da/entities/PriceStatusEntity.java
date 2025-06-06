package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;

import org.tec.carpooling.common.utils.Auditable;
import org.tec.carpooling.common.utils.CatalogEntity;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "PRICESTATUS")
public class PriceStatusEntity implements Identifiable<Long>, CatalogEntity {

    public PriceStatusEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATUS", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public PriceStatusEntity(String status, AuditLogEntity auditLogEntity) {
        this.status = status;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        PriceStatusEntity that = (PriceStatusEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "PriceStatusEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }

    @Override
    public String getName() {
        return this.status;
    }

    @Override
    public void setName(String name) {
        this.status = name;
    }
}