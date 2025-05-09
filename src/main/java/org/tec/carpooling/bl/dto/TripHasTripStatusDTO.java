package org.tec.carpooling.bl.dto;

public class TripHasTripStatusDTO {
    private TripDTO trip;
    private TripStatusDTO tripStatus;
    private AuditLogDTO auditLog;

    public TripHasTripStatusDTO() {
    }

    // Getters and Setters
    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public TripStatusDTO getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatusDTO tripStatus) {
        this.tripStatus = tripStatus;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}