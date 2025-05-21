package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.TripHasStopId;
import java.time.LocalDate; // DDL says DATE, should be LocalDate
import java.util.Objects;

@Entity
@Table(name = "TRIPHASSTOP")
public class TripHasStopEntity implements Identifiable<TripHasStopId> {

    @EmbeddedId
    private TripHasStopId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTrip")
    @JoinColumn(name = "IDTRIP", referencedColumnName = "ID", insertable = false, updatable = false)
    private TripEntity trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idStop")
    @JoinColumn(name = "IDSTOP", referencedColumnName = "ID", insertable = false, updatable = false)
    private StopEntity stop;

    @Column(name = "ESTIMATEDARRIVAL", nullable = false)
    private LocalDate estimatedArrival;

    @Column(name = "STOPCOST", nullable = false)
    private float stopCost;

    @Column(name = "NUMBERSTOP", nullable = false)
    private Integer numberStop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public TripHasStopEntity() {}

    public TripHasStopEntity(TripEntity tripEntity, StopEntity mallSanPedro, LocalDate of, double v, int i, AuditLogEntity auditLogEntity) {
    }

    // Getters and Setters
    public TripHasStopId getId() { return id; }
    public void setId(TripHasStopId id) { this.id = id; }
    public TripEntity getTrip() { return trip; }
    public void setTrip(TripEntity trip) { this.trip = trip; }
    public StopEntity getStop() { return stop; }
    public void setStop(StopEntity stop) { this.stop = stop; }
    public LocalDate getEstimatedArrival() { return estimatedArrival; }
    public void setEstimatedArrival(LocalDate estimatedArrival) { this.estimatedArrival = estimatedArrival; }
    public float getStopCost() { return stopCost; }
    public void setStopCost(float stopCost) { this.stopCost = stopCost; }
    public Integer getNumberStop() { return numberStop; }
    public void setNumberStop(Integer numberStop) { this.numberStop = numberStop; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripHasStopEntity that = (TripHasStopEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TripHasStopEntity{" +
                "id=" + id +
                ", estimatedArrival=" + estimatedArrival +
                ", stopCost=" + stopCost +
                ", numberStop=" + numberStop +
                ", tripId=" + (trip != null ? trip.getId() : null) +
                ", stopId=" + (stop != null ? stop.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}