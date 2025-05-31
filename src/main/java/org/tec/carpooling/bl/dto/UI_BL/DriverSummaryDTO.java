package org.tec.carpooling.bl.dto.UI_BL;

/**
 * Data Transfer Object for carrying a summary of a driver's information from the BL
 * to the UI, typically used within other DTOs like trip search results or trip details.
 */
public class DriverSummaryDTO {
    private String username;
    private String fullName;
    // Potentially average rating, number of trips as driver, etc.
    
    public DriverSummaryDTO() {}
    public DriverSummaryDTO(String username, String fullName) {
        this.username = username;
        this.fullName = fullName;
    }
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}