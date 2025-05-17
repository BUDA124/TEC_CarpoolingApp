package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.BL_DA.UserDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.common.exceptions.AuthenticationException;

public interface UserService {
    /**
     * Registers a new user in the system.
     * @param dto The user registration data.
     * @throws org.tec.carpooling.common.exceptions.ValidationException if DTO validation fails.
     * @throws org.tec.carpooling.common.exceptions.UserRegistrationException for other registration-specific errors.
     * @throws org.tec.carpooling.common.exceptions.EntityNotFoundException if related entities like Institution or Gender are not found.
     */
    void registerNewUser(UserRegistrationDTO dto);

    /**
     * Authenticates a user based on username and password.
     *
     * @param username The username.
     * @param rawPassword The raw (unhashed) password.
     * @return UserDTO if authentication is successful.
     * @throws AuthenticationException if authentication fails.
     */
    UserDTO loginUser(String username, String rawPassword) throws AuthenticationException;
}