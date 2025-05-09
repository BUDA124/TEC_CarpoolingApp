package org.tec.carpooling.bl.dto;

import java.time.LocalDate; // SQL DATE for estimatedArrival

public class TripHasStopDTO {
    private TripDTO trip;
    private StopDTO stop;
    private LocalDate estimatedArrival;
    private Double stopCost;
    private Integer numberStop;
    private AuditLogDTO auditLog;

    public TripHasStopDTO() {
    }

    // Getters and Setters
    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public StopDTO getStop() {
        return stop;
    }

    public void setStop(StopDTO stop) {
        this.stop = stop;
    }

    public LocalDate getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(LocalDate estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

    public Double getStopCost() {
        return stopCost;
    }

    public void setStopCost(Double stopCost) {
        this.stopCost = stopCost;
    }

    public Integer getNumberStop() {
        return numberStop;
    }

    public void setNumberStop(Integer numberStop) {
        this.numberStop = numberStop;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}