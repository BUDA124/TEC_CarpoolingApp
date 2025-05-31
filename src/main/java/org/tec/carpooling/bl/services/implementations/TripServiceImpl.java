package org.tec.carpooling.bl.services.implementations;

import org.tec.carpooling.bl.dto.UI_BL.*;
import org.tec.carpooling.bl.services.TripService;

import java.util.List;

public class TripServiceImpl implements TripService {
    @Override
    public TripDetailsDTO publishTrip(PublishTripDTO dto) {
        return null;
    }

    @Override
    public List<TripSearchResultDTO> searchAvailableTrips(TripSearchQueryDTO query) {
        return List.of();
    }

    @Override
    public TripDetailsDTO getTripDetails(String tripId) {
        return null;
    }

    @Override
    public boolean registerForTrip(String tripId, PassengerRegistrationDTO dto) {
        return false;
    }

    @Override
    public boolean cancelPassengerRegistration(String tripId, String passengerUsername) {
        return false;
    }

    @Override
    public boolean cancelTripByDriver(String tripId) {
        return false;
    }

    @Override
    public int getAvailableCapacity(String tripId) {
        return 0;
    }
}
