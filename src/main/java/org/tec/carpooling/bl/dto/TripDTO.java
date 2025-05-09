package org.tec.carpooling.bl.dto;

import java.time.LocalDateTime;

public class TripDTO {
    private Long id;
    private Integer maximumPassengers;
    private LocalDateTime departureDateTime;
    private Long durationEstimate; // Assuming this is a numeric value like minutes
    private LocalDateTime arrivalDateTime;
    private DriverDTO driver;
    private PriceStatusDTO priceStatus;
    private VehicleDTO vehicle;
    private AuditLogDTO auditLog;

    public TripDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaximumPassengers() {
        return maximumPassengers;
    }

    public void setMaximumPassengers(Integer maximumPassengers) {
        this.maximumPassengers = maximumPassengers;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Long getDurationEstimate() {
        return durationEstimate;
    }

    public void setDurationEstimate(Long durationEstimate) {
        this.durationEstimate = durationEstimate;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    public PriceStatusDTO getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(PriceStatusDTO priceStatus) {
        this.priceStatus = priceStatus;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}