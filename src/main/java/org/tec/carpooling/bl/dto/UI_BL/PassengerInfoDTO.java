package org.tec.carpooling.bl.dto.UI_BL;

/**
 * Data Transfer Object for carrying summary information about a passenger (username,
 * full name, pickup point) from the BL to the UI. Used within lists like
 * confirmed passengers on a trip or in trip history.
 */
public class PassengerInfoDTO {
    private String username;
    private String fullName;
    private String requestedPickupPoint;

    public PassengerInfoDTO() {}
    public PassengerInfoDTO(String username, String fullName, String requestedPickupPoint) {
        this.username = username;
        this.fullName = fullName;
        this.requestedPickupPoint = requestedPickupPoint;
    }
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getRequestedPickupPoint() { return requestedPickupPoint; }
    public void setRequestedPickupPoint(String requestedPickupPoint) { this.requestedPickupPoint = requestedPickupPoint; }
}