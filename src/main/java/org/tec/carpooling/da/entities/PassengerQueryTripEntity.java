package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.PassengerQueryTripId;
import java.util.Objects;

@Entity
@Table(name = "PASSENGERQUERYTRIP")
public class PassengerQueryTripEntity implements Identifiable<PassengerQueryTripId> {

    @EmbeddedId
    private PassengerQueryTripId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUser") // refers to PassengerQueryTripId.idUser
    @JoinColumn(name = "IDUSER", referencedColumnName = "ID", insertable = false, updatable = false)
    private PersonalUserEntity personalUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTrip")
    @JoinColumn(name = "IDTRIP", referencedColumnName = "ID", insertable = false, updatable = false)
    private TripEntity trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;
    
    public PassengerQueryTripEntity() {}

    public PassengerQueryTripEntity(PersonalUserEntity user, TripEntity trip, AuditLogEntity auditLogEntity) {
        this.id = new PassengerQueryTripId();
        this.personalUser = user;
        this.trip = trip;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public PassengerQueryTripId getId() { return id; }
    public void setId(PassengerQueryTripId id) { this.id = id; }
    public PersonalUserEntity getPersonalUser() { return personalUser; }
    public void setPersonalUser(PersonalUserEntity personalUser) { this.personalUser = personalUser; }
    public TripEntity getTrip() { return trip; }
    public void setTrip(TripEntity trip) { this.trip = trip; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerQueryTripEntity that = (PassengerQueryTripEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PassengerQueryTripEntity{" +
                "id=" + id +
                ", personalUserId=" + (personalUser != null ? personalUser.getId() : null) +
                ", tripId=" + (trip != null ? trip.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}