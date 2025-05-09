package org.tec.carpooling.bl.dto;

public class PassengerQueryTripDTO {
    private PersonalUserDTO user; // Represents idUser FK to PERSONALUSER.ID
    private TripDTO trip;
    private AuditLogDTO auditLog;

    public PassengerQueryTripDTO() {
    }

    // Getters and Setters
    public PersonalUserDTO getUser() {
        return user;
    }

    public void setUser(PersonalUserDTO user) {
        this.user = user;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}