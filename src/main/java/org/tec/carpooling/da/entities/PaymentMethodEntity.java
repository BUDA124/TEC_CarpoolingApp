package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "PAYMENTMETHOD")
@SequenceGenerator(name = "seq_paymentmethod_gen", sequenceName = "SEQ_PAYMENTMETHOD", allocationSize = 1)
public class PaymentMethodEntity implements Identifiable<Long> {

    public PaymentMethodEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paymentmethod_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "METHOD", nullable = false, length = 100)
    private String method;

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
}