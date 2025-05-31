package org.tec.carpooling.bl.dto.UI_BL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for carrying summarized information about a found trip from the BL
 * to the UI, typically as part of a list of search results.
 */
public class TripSearchResultDTO {
    private String tripId;
    private DriverSummaryDTO driverInfo;
    private VehicleDTO vehicleInfo; // Reusing VehicleDTO
    private String startPoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private int availableCapacity;
    private BigDecimal costPerPassenger; // Can be null

    public TripSearchResultDTO() {}
    // Getters and Setters (omitted for brevity)
    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }
    public DriverSummaryDTO getDriverInfo() { return driverInfo; }
    public void setDriverInfo(DriverSummaryDTO driverInfo) { this.driverInfo = driverInfo; }
    public VehicleDTO getVehicleInfo() { return vehicleInfo; }
    public void setVehicleInfo(VehicleDTO vehicleInfo) { this.vehicleInfo = vehicleInfo; }
    public String getStartPoint() { return startPoint; }
    public void setStartPoint(String startPoint) { this.startPoint = startPoint; }
    public String getDestinationPoint() { return destinationPoint; }
    public void setDestinationPoint(String destinationPoint) { this.destinationPoint = destinationPoint; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public int getAvailableCapacity() { return availableCapacity; }
    public void setAvailableCapacity(int availableCapacity) { this.availableCapacity = availableCapacity; }
    public BigDecimal getCostPerPassenger() { return costPerPassenger; }
    public void setCostPerPassenger(BigDecimal costPerPassenger) { this.costPerPassenger = costPerPassenger; }
}
