package org.tec.carpooling.bl.dto.UI_BL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for carrying details of a single trip from a passenger's history
 * from the BL to the UI. Includes route info, driver, vehicle, pickup point, cost, and status.
 */
public class PassengerTripHistoryItemDTO {
    private String tripId;
    private String startPoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private DriverSummaryDTO driverInfo;
    private VehicleDTO vehicleInfo;
    private String yourPickupPoint;
    private BigDecimal costPaid; // If applicable
    private String tripStatus; // e.g., "Completed", "Cancelled by Driver", "Cancelled by You"

    public PassengerTripHistoryItemDTO() {}
    // Getters and Setters (omitted for brevity)
    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }
    public String getStartPoint() { return startPoint; }
    public void setStartPoint(String startPoint) { this.startPoint = startPoint; }
    public String getDestinationPoint() { return destinationPoint; }
    public void setDestinationPoint(String destinationPoint) { this.destinationPoint = destinationPoint; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public DriverSummaryDTO getDriverInfo() { return driverInfo; }
    public void setDriverInfo(DriverSummaryDTO driverInfo) { this.driverInfo = driverInfo; }
    public VehicleDTO getVehicleInfo() { return vehicleInfo; }
    public void setVehicleInfo(VehicleDTO vehicleInfo) { this.vehicleInfo = vehicleInfo; }
    public String getYourPickupPoint() { return yourPickupPoint; }
    public void setYourPickupPoint(String yourPickupPoint) { this.yourPickupPoint = yourPickupPoint; }
    public BigDecimal getCostPaid() { return costPaid; }
    public void setCostPaid(BigDecimal costPaid) { this.costPaid = costPaid; }
    public String getTripStatus() { return tripStatus; }
    public void setTripStatus(String tripStatus) { this.tripStatus = tripStatus; }
}