package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;

import org.tec.carpooling.common.utils.Auditable;
import org.tec.carpooling.common.utils.CatalogEntity;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "GENDER")
public class GenderEntity implements Identifiable<Long>, CatalogEntity {

    public GenderEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "GENDERNAME", nullable = false, length = 50)
    private String genderName;

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

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
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
        GenderEntity that = (GenderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "GenderEntity{" +
                "id=" + id +
                ", genderName='" + genderName + '\'' +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }

    @Override
    public String getName() {
        return this.genderName;
    }

    @Override
    public void setName(String name) {
        this.genderName = name;
    }
}