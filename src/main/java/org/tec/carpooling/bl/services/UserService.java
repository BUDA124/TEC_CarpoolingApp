package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.UI_BL.LogInDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.common.exceptions.AuthenticationException;
import org.tec.carpooling.common.exceptions.ValidationException;
import org.tec.carpooling.bl.dto.UI_BL.UserAcceptTermsDTO;
import org.tec.carpooling.bl.dto.BL_DA.UserInstitutionInfoDTO; // For internal use, if exposed
import org.tec.carpooling.common.exceptions.OperationFailedException;
import org.tec.carpooling.common.exceptions.NotFoundException;


public interface UserService {
    /**
     * Registers a new user with their full profile data.
     *
     * @param dto The DTO containing all necessary user registration data.
     * @return true if registration is successful.
     * @throws ValidationException If the provided data fails validation rules.
     * @throws OperationFailedException If there's a logical issue or persistence error during registration (e.g., username/email already exists).
     */
    boolean registerNewUser(UserRegistrationDTO dto);

    /**
     * Authenticates a user based on their username/email and password.
     *
     * @param logInDTO The user's login credentials.
     * @return true if authentication is successful.
     * @throws AuthenticationException If authentication fails due to invalid credentials or user not found.
     */
    boolean logInUser(LogInDTO logInDTO);

    /**
     * Marks that the currently authenticated user has accepted the terms and conditions.
     *
     * @param dto The DTO indicating acceptance. The username is implicit from the security context.
     * @return true if the acceptance was successfully recorded.
     * @throws NotFoundException If the current authenticated user cannot be found.
     * @throws OperationFailedException If there's an issue updating the user's record.
     */
    boolean acceptTermsAndConditions(UserAcceptTermsDTO dto);

    /**
     * Verifies the institution a user belongs to.
     * This is primarily for internal system checks to ensure security and data integrity.
     *
     * @param username The username of the user to verify.
     * @return UserInstitutionInfoDTO containing the user's institution details.
     * @throws NotFoundException If the user is not found.
     */
    UserInstitutionInfoDTO getUserInstitutionInfo(String username);

    boolean IsUserDriver(String username);
}
