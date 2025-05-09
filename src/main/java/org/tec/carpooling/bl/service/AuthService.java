package org.tec.carpooling.bl.service;

import org.tec.carpooling.bl.dto.LoginRequestDTO;
import org.tec.carpooling.bl.dto.LoginResponseDTO;
import org.tec.carpooling.bl.dto.UserRegistrationDTO;
import org.tec.carpooling.common.utils.HashingUtil;
import org.tec.carpooling.common.exceptions.AuthenticationException;
import org.tec.carpooling.common.exceptions.RegistrationException;
import org.tec.carpooling.common.exceptions.ValidationException;

public interface AuthService {

    /**
     * Registers a new personal user.
     * @param registrationDTO Data for the new user.
     * @throws RegistrationException if registration fails (e.g., username exists).
     * @throws ValidationException if input data is invalid.
     */
    void registerUser(UserRegistrationDTO registrationDTO) throws RegistrationException, ValidationException;

    /**
     * Authenticates a user.
     * @param loginRequestDTO Credentials for login.
     * @return LoginResponseDTO containing user details or a session token.
     * @throws AuthenticationException if authentication fails.
     */
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws AuthenticationException;

    /**
     * Logs out a user (if session management is implemented).
     * @param userId The ID of the user to log out.
     */
    void logout(Long userId); // Or based on a session token

    /**
     * Verifies an institutional email.
     * (Details would depend on the external API integration)
     * @param email The institutional email to verify.
     * @param institutionId The ID of the institution.
     * @return true if verified, false otherwise.
     */
    boolean verifyInstitutionalEmail(String email, Long institutionId);
}