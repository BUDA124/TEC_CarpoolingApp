package org.tec.carpooling.bl.dto.UI_BL.driver;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for carrying information from the UI to the BL
 * when a driver publishes a new trip offer. Includes details like vehicle, route,
 * schedule, capacity, and cost.
 */
public class PublishTripDTO {

    private LocalDate tripDate;
    private int passengerCapacity;
    private List<StopDTO> stops;

    public PublishTripDTO(LocalDate tripDate, int passengerCapacity, List<StopDTO> stops) {
        this.tripDate = tripDate;
        this.passengerCapacity = passengerCapacity;
        this.stops = stops;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public List<StopDTO> getStops() {
        return stops;
    }

    public void setStops(List<StopDTO> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "RideDTO{" +
                "tripDate=" + tripDate +
                ", passengerCapacity=" + passengerCapacity +
                ", stops=" + stops +
                '}';
    }
}
