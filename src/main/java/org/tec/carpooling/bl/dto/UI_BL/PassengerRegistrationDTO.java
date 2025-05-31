package org.tec.carpooling.bl.dto.UI_BL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for carrying a passenger's requested pickup point from the UI
 * to the BL when they register for a trip.
 */
public class PassengerRegistrationDTO {

    @NotBlank(message = "Requested pickup point is required.")
    @Size(max = 255, message = "Pickup point must not exceed 255 characters.")
    private String requestedPickupPoint;

    public PassengerRegistrationDTO() {}

    // Getter and Setter
    public String getRequestedPickupPoint() { return requestedPickupPoint; }
    public void setRequestedPickupPoint(String requestedPickupPoint) { this.requestedPickupPoint = requestedPickupPoint; }
}