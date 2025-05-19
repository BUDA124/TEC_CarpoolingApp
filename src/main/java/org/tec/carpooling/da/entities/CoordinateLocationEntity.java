package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import java.util.Objects;
import org.tec.carpooling.common.utils.HashingUtil;

@Entity
@Table(name = "COORDINATELOCATION")
@SequenceGenerator(name = "seq_coordinatelocation_gen", sequenceName = "SEQ_COORDINATELOCATION", allocationSize = 1)
public class CoordinateLocationEntity implements Identifiable<Long> {

    public CoordinateLocationEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coordinatelocation_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "YCOORDINATE", nullable = false)
    private float yCoordinate;

    @Column(name = "XCOORDINATE", nullable = false)
    private float xCoordinate;

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

    public float getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public float getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(float xCoordinate) {
        this.xCoordinate = xCoordinate;
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
                ", yCoordinate=" + yCoordinate +
                ", xCoordinate=" + xCoordinate +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}