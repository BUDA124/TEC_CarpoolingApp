package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.UI_BL.*; // Includes PublishTripDTO, TripSearchQueryDTO, TripSearchResultDTO, TripDetailsDTO, PassengerRegistrationDTO
import org.tec.carpooling.common.exceptions.*;

import java.util.List;

public interface TripService {

    /**
     * Publishes a new trip offer by the currently authenticated driver.
     *
     * @param dto The DTO containing trip details.
     * @return TripDetailsDTO of the newly published trip.
     * @throws ValidationException If trip data is invalid.
     * @throws NotFoundException If the specified vehicle is not found or doesn't belong to the driver.
     * @throws PermissionDeniedException If the user is not a driver or not authorized.
     * @throws OperationFailedException If trip publication fails.
     */
    TripDetailsDTO publishTrip(PublishTripDTO dto);

    /**
     * Searches for available trips based on destination, schedule, and passenger's institution.
     *
     * @param query DTO containing search criteria. Passenger's institution is derived from authenticated user.
     * @return A list of TripSearchResultDTOs matching the criteria.
     * @throws ValidationException If search query is invalid.
     */
    List<TripSearchResultDTO> searchAvailableTrips(TripSearchQueryDTO query);

    /**
     * Retrieves full details of a specific trip.
     *
     * @param tripId The ID of the trip.
     * @return TripDetailsDTO with comprehensive information.
     * @throws NotFoundException If the trip is not found.
     */
    TripDetailsDTO getTripDetails(String tripId);

    /**
     * Registers the currently authenticated passenger for a specific trip.
     *
     * @param tripId The ID of the trip to join.
     * @param dto DTO containing passenger-specific information like pickup point.
     * @return true if registration is successful.
     * @throws NotFoundException If the trip is not found or passenger not found.
     * @throws OperationFailedException If registration fails (e.g., trip full, passenger already registered, institution mismatch).
     * @throws PermissionDeniedException If the passenger is not authorized to join (e.g. trying to join their own trip).
     * @throws ValidationException If the DTO is invalid.
     */
    boolean registerForTrip(String tripId, PassengerRegistrationDTO dto);

    /**
     * Cancels a passenger's registration for a specific trip.
     * Only the passenger who registered or the driver of the trip can cancel.
     *
     * @param tripId The ID of the trip.
     * @param passengerUsername The username of the passenger to be removed (if driver is cancelling).
     *                          If null, current authenticated passenger cancels their own spot.
     * @return true if cancellation is successful.
     * @throws NotFoundException If the trip or registration is not found.
     * @throws PermissionDeniedException If the user is not authorized to cancel this registration.
     * @throws OperationFailedException If cancellation fails.
     */
    boolean cancelPassengerRegistration(String tripId, String passengerUsername);

    /**
     * Allows a driver to cancel a trip they published.
     *
     * @param tripId The ID of the trip to cancel.
     * @return true if cancellation is successful.
     * @throws NotFoundException If the trip is not found.
     * @throws PermissionDeniedException If the authenticated user is not the driver of the trip.
     * @throws OperationFailedException If cancellation fails (e.g., trip already started or has critical status).
     */
    boolean cancelTripByDriver(String tripId);
    
    /**
     * Checks the available capacity for a specific trip.
     *
     * @param tripId The ID of the trip.
     * @return The number of available seats.
     * @throws NotFoundException If the trip is not found.
     */
    int getAvailableCapacity(String tripId);
}