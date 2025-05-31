package org.tec.carpooling.bl.dto.UI_BL;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object for carrying vehicle information.
 * Used for registering a new vehicle (UI to BL), updating vehicle details (UI to BL),
 * and displaying vehicle information (BL to UI).
 */
public class VehicleDTO {

    private String vehicleId; // System-generated, returned on GET, not on POST

    @NotBlank(message = "License plate is required.")
    @Size(min = 3, max = 15, message = "License plate must be between 3 and 15 characters.")
    private String licensePlate;

    @NotBlank(message = "Brand is required.")
    @Size(max = 50, message = "Brand must not exceed 50 characters.")
    private String brand;

    @NotBlank(message = "Model is required.")
    @Size(max = 50, message = "Model must not exceed 50 characters.")
    private String model;

    @NotBlank(message = "Color is required.")
    @Size(max = 30, message = "Color must not exceed 30 characters.")
    private String color;

    @NotNull(message = "Year of manufacture is required.")
    @Min(value = 1980, message = "Year must be 1980 or newer.")
    @Max(value = 2025, message = "Year cannot be in the distant future.") // Adjust current year as needed
    private Integer year;

    @NotNull(message = "Passenger capacity is required.")
    @Min(value = 1, message = "Capacity must be at least 1.")
    @Max(value = 10, message = "Capacity cannot exceed 10 for carpooling.") // Example limit
    private Integer passengerCapacity;

    public VehicleDTO() {}

    // Getters and Setters for all fields (omitted for brevity)
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getPassengerCapacity() { return passengerCapacity; }
    public void setPassengerCapacity(Integer passengerCapacity) { this.passengerCapacity = passengerCapacity; }
}
