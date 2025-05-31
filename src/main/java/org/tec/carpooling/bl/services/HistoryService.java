package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.UI_BL.DriverTripHistoryItemDTO;
import org.tec.carpooling.bl.dto.UI_BL.PassengerTripHistoryItemDTO;
import org.tec.carpooling.common.exceptions.NotFoundException;

import java.util.List;

public interface HistoryService {

    /**
     * Retrieves the history of trips made by the currently authenticated user as a driver.
     *
     * @return A list of DriverTripHistoryItemDTOs.
     * @throws NotFoundException If the user is not found or has no driver history.
     */
    List<DriverTripHistoryItemDTO> getMyDriverHistory();

    /**
     * Retrieves the history of trips taken by the currently authenticated user as a passenger.
     *
     * @return A list of PassengerTripHistoryItemDTOs.
     * @throws NotFoundException If the user is not found or has no passenger history.
     */
    List<PassengerTripHistoryItemDTO> getMyPassengerHistory();
}