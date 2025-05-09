package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.StopHasCoordinateLocationId;
import java.util.Objects;

@Entity
@Table(name = "STOPHASCOORDINATELOCATION")
public class StopHasCoordinateLocationEntity implements Identifiable<StopHasCoordinateLocationId> {

    @EmbeddedId
    private StopHasCoordinateLocationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idStop")
    @JoinColumn(name = "IDSTOP", referencedColumnName = "ID", insertable = false, updatable = false)
    private StopEntity stop;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCoordinateLocation")
    @JoinColumn(name = "IDCOORDINATELOCATION", referencedColumnName = "ID", insertable = false, updatable = false)
    private CoordinateLocationEntity coordinateLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;
    
    public StopHasCoordinateLocationEntity() {}

    // Getters and Setters
    public StopHasCoordinateLocationId getId() { return id; }
    public void setId(StopHasCoordinateLocationId id) { this.id = id; }
    public StopEntity getStop() { return stop; }
    public void setStop(StopEntity stop) { this.stop = stop; }
    public CoordinateLocationEntity getCoordinateLocation() { return coordinateLocation; }
    public void setCoordinateLocation(CoordinateLocationEntity coordinateLocation) { this.coordinateLocation = coordinateLocation; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopHasCoordinateLocationEntity that = (StopHasCoordinateLocationEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StopHasCoordinateLocationEntity{" +
                "id=" + id +
                ", stopId=" + (stop != null ? stop.getId() : null) +
                ", coordinateLocationId=" + (coordinateLocation != null ? coordinateLocation.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}