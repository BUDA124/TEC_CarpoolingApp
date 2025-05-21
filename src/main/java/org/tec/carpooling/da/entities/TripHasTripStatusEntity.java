package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.TripHasStopHasPaymentMethodId;
import org.tec.carpooling.da.entities.embeddable.TripHasTripStatusId;
import java.util.Objects;

@Entity
@Table(name = "TRIPHASTRIPSTATUS")
public class TripHasTripStatusEntity implements Identifiable<TripHasTripStatusId> {

    @EmbeddedId
    private TripHasTripStatusId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTrip")
    @JoinColumn(name = "IDTRIP", referencedColumnName = "ID", insertable = false, updatable = false)
    private TripEntity trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTripStatus")
    @JoinColumn(name = "IDTRIPSTATUS", referencedColumnName = "ID", insertable = false, updatable = false)
    private TripStatusEntity tripStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public TripHasTripStatusEntity() {}

    public TripHasTripStatusEntity(TripEntity tripEntity, TripStatusEntity tripStatusEntity, AuditLogEntity auditLogEntity) {
    }

    // Getters and Setters
    public TripHasTripStatusId getId() { return id; }
    public void setId(TripHasTripStatusId id) { this.id = id; }
    public TripEntity getTrip() { return trip; }
    public void setTrip(TripEntity trip) { this.trip = trip; }
    public TripStatusEntity getTripStatus() { return tripStatus; }
    public void setTripStatus(TripStatusEntity tripStatus) { this.tripStatus = tripStatus; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripHasTripStatusEntity that = (TripHasTripStatusEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "TripHasTripStatusEntity{" +
                "id=" + id +
                ", tripId=" + (trip != null ? trip.getId() : null) +
                ", tripStatusId=" + (tripStatus != null ? tripStatus.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}