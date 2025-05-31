package org.tec.carpooling.bl.dto.UI_BL;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List; // For daysOfWeek in recurring routes, not used in single trip publish

/**
 * Data Transfer Object for carrying information from the UI to the BL
 * when a driver publishes a new trip offer. Includes details like vehicle, route,
 * schedule, capacity, and cost.
 */
public class PublishTripDTO { // Renamed from PublishRouteDTO for clarity, as it's a specific trip instance

    @NotBlank(message = "Vehicle ID is required.")
    private String vehicleId;

    @NotBlank(message = "Start point is required.")
    @Size(max = 255, message = "Start point must not exceed 255 characters.")
    private String startPoint; // Could be a complex type or GeoJSON string later

    @NotBlank(message = "Destination point is required.")
    @Size(max = 255, message = "Destination point must not exceed 255 characters.")
    private String destinationPoint; // Could be a complex type or GeoJSON string later

    @NotNull(message = "Departure time is required.")
    @Future(message = "Departure time must be in the future.")
    private LocalDateTime departureTime;

    @NotNull(message = "Passenger capacity for this trip is required.")
    @Min(value = 1, message = "Passenger capacity must be at least 1.")
    private Integer passengerCapacity;

    @DecimalMin(value = "0.0", inclusive = true, message = "Cost must be non-negative.")
    @Digits(integer = 5, fraction = 2, message = "Cost format is invalid.")
    private BigDecimal costPerPassenger; // Optional, can be null for free trips

    @Size(max = 500, message = "Route notes must not exceed 500 characters.")
    private String routeNotes; // Optional

    public PublishTripDTO() {}

    // Getters and Setters (omitted for brevity)
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    public String getStartPoint() { return startPoint; }
    public void setStartPoint(String startPoint) { this.startPoint = startPoint; }
    public String getDestinationPoint() { return destinationPoint; }
    public void setDestinationPoint(String destinationPoint) { this.destinationPoint = destinationPoint; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public Integer getPassengerCapacity() { return passengerCapacity; }
    public void setPassengerCapacity(Integer passengerCapacity) { this.passengerCapacity = passengerCapacity; }
    public BigDecimal getCostPerPassenger() { return costPerPassenger; }
    public void setCostPerPassenger(BigDecimal costPerPassenger) { this.costPerPassenger = costPerPassenger; }
    public String getRouteNotes() { return routeNotes; }
    public void setRouteNotes(String routeNotes) { this.routeNotes = routeNotes; }
}
