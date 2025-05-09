package org.tec.carpooling.bl.dto;

import java.time.LocalDate;

public class PassengerJoinTripDTO {
    private PersonalUserDTO user; // Represents idUser FK to PERSONALUSER.ID
    private TripDTO trip;
    private LocalDate joinDate;
    private AuditLogDTO auditLog;

    public PassengerJoinTripDTO() {
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

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}