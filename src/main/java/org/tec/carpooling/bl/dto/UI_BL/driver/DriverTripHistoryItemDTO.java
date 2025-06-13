package org.tec.carpooling.bl.dto.UI_BL.driver;

import org.tec.carpooling.bl.dto.UI_BL.PassengerInfoDTO;
import org.tec.carpooling.bl.dto.UI_BL.VehicleDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for carrying details of a single trip from a driver's history
 * from the BL to the UI. Includes route info, vehicle, passengers, earnings, and status.
 */
public class DriverTripHistoryItemDTO {
    private String tripId;
    private String startPoint;
    private String destinationPoint;
    private LocalDateTime departureTime;
    private LocalDateTime actualArrivalTime; // If recorded
    private VehicleDTO vehicleUsed;
    private List<PassengerInfoDTO> passengersOnTrip;
    private BigDecimal totalEarningsFromTrip; // If applicable
    private String tripStatus; // e.g., "Completed", "Cancelled by Driver", "Cancelled by System"

    public DriverTripHistoryItemDTO() {}
    // Getters and Setters (omitted for brevity)
    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }
    public String getStartPoint() { return startPoint; }
    public void setStartPoint(String startPoint) { this.startPoint = startPoint; }
    public String getDestinationPoint() { return destinationPoint; }
    public void setDestinationPoint(String destinationPoint) { this.destinationPoint = destinationPoint; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public LocalDateTime getActualArrivalTime() { return actualArrivalTime; }
    public void setActualArrivalTime(LocalDateTime actualArrivalTime) { this.actualArrivalTime = actualArrivalTime; }
    public VehicleDTO getVehicleUsed() { return vehicleUsed; }
    public void setVehicleUsed(VehicleDTO vehicleUsed) { this.vehicleUsed = vehicleUsed; }
    public List<PassengerInfoDTO> getPassengersOnTrip() { return passengersOnTrip; }
    public void setPassengersOnTrip(List<PassengerInfoDTO> passengersOnTrip) { this.passengersOnTrip = passengersOnTrip; }
    public BigDecimal getTotalEarningsFromTrip() { return totalEarningsFromTrip; }
    public void setTotalEarningsFromTrip(BigDecimal totalEarningsFromTrip) { this.totalEarningsFromTrip = totalEarningsFromTrip; }
    public String getTripStatus() { return tripStatus; }
    public void setTripStatus(String tripStatus) { this.tripStatus = tripStatus; }
}