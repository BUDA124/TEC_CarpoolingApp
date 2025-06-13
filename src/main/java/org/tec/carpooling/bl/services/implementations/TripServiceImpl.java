package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.dto.BL_DA.TripSearchQueryDTO;
import org.tec.carpooling.bl.dto.BL_DA.TripSearchResultDTO;
import org.tec.carpooling.bl.dto.UI_BL.*;
import org.tec.carpooling.bl.dto.UI_BL.driver.PublishTripDTO;
import org.tec.carpooling.bl.dto.UI_BL.TripDetailsDTO;
import org.tec.carpooling.bl.dto.UI_BL.driver.StopDTO;
import org.tec.carpooling.bl.mappers.TripMapper;
import org.tec.carpooling.bl.services.TripService;
import org.tec.carpooling.da.entities.StopEntity;
import org.tec.carpooling.da.repositories.StopRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired private TripMapper tripMapper;
    @Autowired private StopRepository stopRepository;

    @Override
    public boolean publishTrip(PublishTripDTO dto) {

        List<StopDTO> listStopsDTOs = dto.getStops();
        List<StopEntity> listStopEntities = new ArrayList<StopEntity>();

        for (StopDTO stopDTO : listStopsDTOs) {
            StopEntity stopEntity = tripMapper.toStopEntity(stopDTO);
            listStopEntities.add(stopEntity);
        }

        stopRepository.saveAll(listStopEntities);

        return false;
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
