package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;

import org.tec.carpooling.common.utils.Auditable;
import org.tec.carpooling.common.utils.CatalogEntity;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "PAYMENTMETHOD")
public class PaymentMethodEntity implements Identifiable<Long>, CatalogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "METHOD", nullable = false, length = 100)
    private String method;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public PaymentMethodEntity() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
        PaymentMethodEntity that = (PaymentMethodEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "PaymentMethodEntity{" +
                "id=" + id +
                ", method='" + method + '\'' +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }

    @Override
    public String getName() {
        return this.method;
    }

    @Override
    public void setName(String name) {
        this.method = name;
    }
}