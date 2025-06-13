package org.tec.carpooling.bl.dto.BL_DA;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for carrying search criteria from the UI to the BL
 * when a passenger searches for available trips. Includes destination and desired time window.
 */
public class TripSearchQueryDTO {

    @NotBlank(message = "Destination point is required for search.")
    @Size(max = 255)
    private String destinationPoint;

    @NotNull(message = "Desired departure start time is required.")
    @FutureOrPresent(message = "Search start time must be now or in the future.")
    private LocalDateTime desiredDepartureTimeStart;

    @NotNull(message = "Desired departure end time is required.")
    @FutureOrPresent(message = "Search end time must be now or in the future.")
    private LocalDateTime desiredDepartureTimeEnd;
    
    // Implicit: passenger's institution will be used for filtering

    public TripSearchQueryDTO() {}

    // Getters and Setters (omitted for brevity)
    public String getDestinationPoint() { return destinationPoint; }
    public void setDestinationPoint(String destinationPoint) { this.destinationPoint = destinationPoint; }
    public LocalDateTime getDesiredDepartureTimeStart() { return desiredDepartureTimeStart; }
    public void setDesiredDepartureTimeStart(LocalDateTime desiredDepartureTimeStart) { this.desiredDepartureTimeStart = desiredDepartureTimeStart; }
    public LocalDateTime getDesiredDepartureTimeEnd() { return desiredDepartureTimeEnd; }
    public void setDesiredDepartureTimeEnd(LocalDateTime desiredDepartureTimeEnd) { this.desiredDepartureTimeEnd = desiredDepartureTimeEnd; }
}