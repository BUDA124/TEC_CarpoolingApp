package org.tec.carpooling.bl.dto.UI_BL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for carrying comprehensive details about a specific trip from the BL
 * to the UI. This includes full driver and vehicle information, route details, capacity,
 * cost, status, and a list of confirmed passengers.
 */
public class TripDetailsDTO {
    private String tripId;
    private UserProfileDTO driverProfile; // Full driver profile
    private VehicleDTO vehicleDetails; // Full vehicle details
    private String startPoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private LocalDateTime estimatedArrivalTime; // Optional
    private int totalCapacity;
    private int availableCapacity;
    private BigDecimal costPerPassenger;
    private String routeNotes;
    private String status; // e.g., "Scheduled", "In Progress", "Completed", "Cancelled"
    private List<PassengerInfoDTO> confirmedPassengers; // List of passengers confirmed for this trip

    public TripDetailsDTO() {}
    // Getters and Setters (omitted for brevity)
    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }
    public UserProfileDTO getDriverProfile() { return driverProfile; }
    public void setDriverProfile(UserProfileDTO driverProfile) { this.driverProfile = driverProfile; }
    public VehicleDTO getVehicleDetails() { return vehicleDetails; }
    public void setVehicleDetails(VehicleDTO vehicleDetails) { this.vehicleDetails = vehicleDetails; }
    public String getStartPoint() { return startPoint; }
    public void setStartPoint(String startPoint) { this.startPoint = startPoint; }
    public String getDestinationPoint() { return destinationPoint; }
    public void setDestinationPoint(String destinationPoint) { this.destinationPoint = destinationPoint; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public LocalDateTime getEstimatedArrivalTime() { return estimatedArrivalTime; }
    public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) { this.estimatedArrivalTime = estimatedArrivalTime; }
    public int getTotalCapacity() { return totalCapacity; }
    public void setTotalCapacity(int totalCapacity) { this.totalCapacity = totalCapacity; }
    public int getAvailableCapacity() { return availableCapacity; }
    public void setAvailableCapacity(int availableCapacity) { this.availableCapacity = availableCapacity; }
    public BigDecimal getCostPerPassenger() { return costPerPassenger; }
    public void setCostPerPassenger(BigDecimal costPerPassenger) { this.costPerPassenger = costPerPassenger; }
    public String getRouteNotes() { return routeNotes; }
    public void setRouteNotes(String routeNotes) { this.routeNotes = routeNotes; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<PassengerInfoDTO> getConfirmedPassengers() { return confirmedPassengers; }
    public void setConfirmedPassengers(List<PassengerInfoDTO> confirmedPassengers) { this.confirmedPassengers = confirmedPassengers; }
}