package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.UI_BL.VehicleDTO;
import org.tec.carpooling.common.exceptions.NotFoundException;
import org.tec.carpooling.common.exceptions.ValidationException;
import org.tec.carpooling.common.exceptions.OperationFailedException;
import org.tec.carpooling.common.exceptions.PermissionDeniedException;


import java.util.List;

public interface VehicleService {

    /**
     * Registers a new vehicle for the currently authenticated driver.
     *
     * @param vehicleDTO The DTO containing vehicle details. vehicleId should be null.
     * @return The DTO of the newly registered vehicle, including its generated ID.
     * @throws ValidationException If vehicle data is invalid.
     * @throws OperationFailedException If registration fails.
     * @throws PermissionDeniedException If the user is not a driver or not authorized.
     */
    VehicleDTO registerVehicle(VehicleDTO vehicleDTO);

    /**
     * Retrieves all vehicles registered by the currently authenticated driver.
     *
     * @return A list of VehicleDTOs.
     * @throws NotFoundException If the driver has no vehicles or driver not found.
     * @throws PermissionDeniedException If the user is not authorized.
     */
    List<VehicleDTO> getMyVehicles();

    /**
     * Retrieves a specific vehicle by its ID, ensuring it belongs to the authenticated driver.
     *
     * @param vehicleId The ID of the vehicle to retrieve.
     * @return The VehicleDTO.
     * @throws NotFoundException If the vehicle is not found or doesn't belong to the user.
     * @throws PermissionDeniedException If the user is not authorized.
     */
    VehicleDTO getVehicleById(String vehicleId);

    /**
     * Updates an existing vehicle for the currently authenticated driver.
     *
     * @param vehicleId The ID of the vehicle to update.
     * @param vehicleDTO The DTO containing updated vehicle details.
     * @return The updated VehicleDTO.
     * @throws ValidationException If vehicle data is invalid.
     * @throws NotFoundException If the vehicle is not found.
     * @throws OperationFailedException If update fails.
     * @throws PermissionDeniedException If the user is not authorized to update this vehicle.
     */
    VehicleDTO updateVehicle(String vehicleId, VehicleDTO vehicleDTO);

    /**
     * Deletes a vehicle for the currently authenticated driver.
     *
     * @param vehicleId The ID of the vehicle to delete.
     * @return true if deletion was successful.
     * @throws NotFoundException If the vehicle is not found.
     * @throws OperationFailedException If deletion fails (e.g., vehicle is part of active trips).
     * @throws PermissionDeniedException If the user is not authorized to delete this vehicle.
     */
    boolean deleteVehicle(String vehicleId);
}