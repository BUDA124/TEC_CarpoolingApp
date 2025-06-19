package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopDriverDTO;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopStopDTO;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopUserDTO;
import org.tec.carpooling.bl.services.GeneralQueriesService;
import org.tec.carpooling.da.repositories.PersonalUserRepository;
import org.tec.carpooling.da.repositories.StopRepository;
import org.tec.carpooling.da.repositories.TripHasStopRepository;
import org.tec.carpooling.da.repositories.TripRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class GeneralQueriesServiceImpl implements GeneralQueriesService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private PersonalUserRepository personalUserRepository;

    @Autowired
    private TripHasStopRepository tripHasStopRepository;

    @Override
    public List<TopDriverDTO> getTop5Drivers() {
        return tripRepository.findTopDrivers(PageRequest.of(0, 5)).getContent();
    }

    @Override
    public List<TopStopDTO> getTop5ConcurrentStops(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return stopRepository.findTopConcurrentStops(startDateTime, endDateTime, PageRequest.of(0, 5)).getContent();
    }

    @Override
    public List<TopUserDTO> getTop5ActiveUsers() {
        return personalUserRepository.findTopActiveUsers(PageRequest.of(0, 5)).getContent();
    }

    @Override
    public Double getAverageFare() {
        Double average = tripHasStopRepository.findAverageFare();
        return average == null ? 0.0 : average;
    }

    @Override
    public Long getNewUsersInLast3Months() {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        return personalUserRepository.countNewUsersSince(threeMonthsAgo);
    }
}