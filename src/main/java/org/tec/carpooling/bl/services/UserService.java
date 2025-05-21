package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.BL_DA.UserDTO;
import org.tec.carpooling.bl.dto.UI_BL.LogInDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserUpdateDTO;
import org.tec.carpooling.bl.dto.UI_BL.VehicleDTO;
import org.tec.carpooling.common.exceptions.AuthenticationException;
import org.tec.carpooling.common.exceptions.EntityNotFoundException;
import org.tec.carpooling.common.exceptions.ValidationException;

public interface UserService {
    /**
     * Registers a new user with their full profile data.
     * This includes basic personal information, institutional details,
     * contact information, identification, and role identification (driver/passenger).
     *
     * @param dto The DTO containing all necessary user registration data.
     * @return The DTO of the newly registered user.
     * @throws ValidationException If the provided data fails validation rules.
     * @throws IllegalArgumentException If there's a logical issue during registration (e.g., username/email already exists).
     */
    boolean registerNewUser(UserRegistrationDTO dto);

    /**
     * Authenticates a user based on their username and password.
     *
     * @param logInDTO The user's information.
     * @return An Optional containing the UserDTO if authentication is successful,
     *         or an empty Optional if credentials are invalid.
     * @throws AuthenticationException If authentication fails due to invalid credentials.
     */
    boolean logInUser(LogInDTO logInDTO);

    /**
     * Retrieves the complete profile data for a specific user.
     *
     * @param userId The unique identifier of the user (Person ID).
     * @return An Optional containing the UserDTO if found, or empty otherwise.
     */
    UserDTO getUserProfile(long userId);

    /**
     * Updates specific profile information for an existing user.
     *
     * @param userId The ID of the user whose profile is to be updated.
     * @param updateDTO The DTO containing the updated profile information.
     * @return The updated UserDTO.
     * @throws EntityNotFoundException If the user with the given ID does not exist.
     * @throws ValidationException If the updated data fails validation rules.
     */
    UserDTO updateUserProfile(long userId, UserUpdateDTO updateDTO) throws EntityNotFoundException, ValidationException;

    /**
     * Marks that a user has accepted the system's terms and conditions.
     *
     * @param userId The unique identifier of the user.
     * @throws EntityNotFoundException If the user with the given ID does not exist.
     */
    void acceptTermsAndConditions(long userId) throws EntityNotFoundException;

    /**
     * Retrieves the list of vehicles registered by a specific driver user.
     *
     * @param driverPersonId The unique identifier of the driver (Person ID).
     * @return A list of VehicleDTOs owned by the driver.
     * @throws EntityNotFoundException If the driver with the given ID does not exist.
     */
    VehicleDTO getVehiclesByDriver(long driverPersonId) throws EntityNotFoundException;

    /**
     * Retrieves the institution ID that a user belongs to.
     * This is typically used internally for security and filtering.
     *
     * @param userId The unique identifier of the user (PersonalUser ID).
     * @return The ID of the institution the user belongs to.
     * @throws EntityNotFoundException If the user or their associated institution is not found.
     */
    long getUserInstitutionId(long userId) throws EntityNotFoundException;
}
