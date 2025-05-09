package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.PassengerJoinTripId;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "PASSENGERJOINTRIP")
public class PassengerJoinTripEntity implements Identifiable<PassengerJoinTripId> {

    @EmbeddedId
    private PassengerJoinTripId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUser") // refers to PassengerJoinTripId.idUser
    @JoinColumn(name = "IDUSER", referencedColumnName = "ID", insertable = false, updatable = false)
    private PersonalUserEntity personalUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTrip")
    @JoinColumn(name = "IDTRIP", referencedColumnName = "ID", insertable = false, updatable = false)
    private TripEntity trip;

    @Column(name = "JOINDATE", nullable = false)
    private LocalDate joinDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public PassengerJoinTripEntity() {}

    // Getters and Setters
    public PassengerJoinTripId getId() { return id; }
    public void setId(PassengerJoinTripId id) { this.id = id; }
    public PersonalUserEntity getPersonalUser() { return personalUser; }
    public void setPersonalUser(PersonalUserEntity personalUser) { this.personalUser = personalUser; }
    public TripEntity getTrip() { return trip; }
    public void setTrip(TripEntity trip) { this.trip = trip; }
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerJoinTripEntity that = (PassengerJoinTripEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "PassengerJoinTripEntity{" +
                "id=" + id +
                ", joinDate=" + joinDate +
                ", personalUserId=" + (personalUser != null ? personalUser.getId() : null) +
                ", tripId=" + (trip != null ? trip.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}