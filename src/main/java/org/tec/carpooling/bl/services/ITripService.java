package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.*; // Assuming DTOs like TripPublicationDTO, TripSearchCriteriaDTO, TripSummaryDTO, TripDetailsDTO, StopDTO
import org.tec.carpooling.bl.dto.BL_DA.StopDTO;
import org.tec.carpooling.bl.dto.BL_DA.TripHistoryDTO;
import org.tec.carpooling.bl.dto.UI_BL.*;
import org.tec.carpooling.common.exceptions.EntityNotFoundException;
import org.tec.carpooling.common.exceptions.ValidationException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing carpooling trips, including publishing,
 * searching, and joining trips, as well as accessing trip history.
 */
public interface ITripService {

    /**
     * Publishes a new trip offered by a driver.
     * Includes details about the route, vehicle, schedule, passenger capacity, and optional cost.
     *
     * @param publicationDTO The DTO containing all details for the new trip.
     * @return The DTO of the newly published trip.
     * @throws ValidationException If the provided trip data is invalid.
     * @throws EntityNotFoundException If the associated driver or vehicle does not exist.
     */
    TripDTO publishTrip(TripPublicationDTO publicationDTO) throws ValidationException, EntityNotFoundException;

    /**
     * Searches for available trips based on specified criteria.
     * Critically filters results to trips belonging to the same institution as the searching passenger.
     *
     * @param searchCriteriaDTO The DTO containing destination, schedule, and passenger's institution ID.
     * @return A list of TripSummaryDTOs matching the search criteria.
     */
    List<TripSummaryDTO> searchAvailableTrips(TripSearchCriteriaDTO searchCriteriaDTO);

    /**
     * Retrieves the complete details of a specific trip, including driver, vehicle,
     * route information, and any associated passengers.
     *
     * @param tripId The unique identifier of the trip.
     * @return An Optional containing the TripDetailsDTO if found, or empty otherwise.
     */
    Optional<TripDetailsDTO> getTripDetails(long tripId);

    /**
     * Checks the remaining available passenger capacity for a given trip.
     *
     * @param tripId The unique identifier of the trip.
     * @return The number of available seats.
     * @throws EntityNotFoundException If the trip with the given ID does not exist.
     */
    int getAvailableTripCapacity(long tripId) throws EntityNotFoundException;

    /**
     * Registers a passenger for a specific trip, decrementing the available capacity.
     *
     * @param tripId The unique identifier of the trip.
     * @param passengerUserId The unique identifier of the passenger (PersonalUser ID).
     * @param pickupStopDTO The DTO representing the desired pickup stop for the passenger.
     * @throws EntityNotFoundException If the trip or passenger does not exist.
     * @throws ValidationException If the pickup stop details are invalid.
     * @throws IllegalArgumentException If the passenger is already registered for this trip.
     */
    void joinTrip(long tripId, long passengerUserId, StopDTO pickupStopDTO)
            throws EntityNotFoundException, ValidationException;

    /**
     * Retrieves the history of trips a user has made as a driver.
     *
     * @param driverPersonId The unique identifier of the user acting as a driver (Person ID).
     * @return A list of TripHistoryDTOs representing trips driven.
     * @throws EntityNotFoundException If the driver is not found.
     */
    List<TripHistoryDTO> getDriverTripHistory(long driverPersonId) throws EntityNotFoundException;

    /**
     * Retrieves the history of trips a user has participated in as a passenger.
     *
     * @param passengerPersonId The unique identifier of the user acting as a passenger (Person ID).
     * @return A list of TripHistoryDTOs representing trips taken as a passenger.
     * @throws EntityNotFoundException If the passenger is not found.
     */
    List<TripHistoryDTO> getPassengerTripHistory(long passengerPersonId) throws EntityNotFoundException;
}