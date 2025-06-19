package org.tec.carpooling.bl.services;

import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopDriverDTO;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopStopDTO;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopUserDTO;


import java.time.LocalDate;
import java.util.List;

@Service
public interface GeneralQueriesService {

    List<TopDriverDTO> getTop5Drivers();

    List<TopStopDTO> getTop5ConcurrentStops(LocalDate startDate, LocalDate endDate);

    List<TopUserDTO> getTop5ActiveUsers();

    Double getAverageFare();

    Long getNewUsersInLast3Months();
}