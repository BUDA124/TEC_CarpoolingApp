package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "CANTON")
@SequenceGenerator(name = "seq_canton_gen", sequenceName = "SEQ_CANTON", allocationSize = 1)
public class CantonEntity implements Identifiable<Long> {

    public CantonEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_canton_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPROVINCE", nullable = false)
    private ProvinceEntity province;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
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
        CantonEntity that = (CantonEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "CantonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provinceId=" + (province != null ? province.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}