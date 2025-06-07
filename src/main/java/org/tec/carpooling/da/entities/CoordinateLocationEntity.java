package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "COORDINATELOCATION")
public class CoordinateLocationEntity implements Identifiable<Long> {

    public CoordinateLocationEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "YCOORDINATE", nullable = false)
    private float YCoordinate;

    @Column(name = "XCOORDINATE", nullable = false)
    private float XCoordinate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public CoordinateLocationEntity(float v, float v1, AuditLogEntity auditLogEntity) {
        this.YCoordinate = v;
        this.XCoordinate = v1;
        this.auditLog = auditLogEntity;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getYCoordinate() {
        return YCoordinate;
    }

    public void setYCoordinate(float yCoordinate) {
        this.YCoordinate = yCoordinate;
    }

    public float getXCoordinate() {
        return XCoordinate;
    }

    public void setXCoordinate(float xCoordinate) {
        this.XCoordinate = xCoordinate;
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
        CoordinateLocationEntity that = (CoordinateLocationEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return HashingUtil.hash(id);
    }

    @Override
    public String toString() {
        return "CoordinateLocationEntity{" +
                "id=" + id +
                ", yCoordinate=" + YCoordinate +
                ", xCoordinate=" + XCoordinate +
                ", auditLog=" + auditLog +
                '}';
    }
}