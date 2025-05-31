package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.UI_BL.UserProfileDTO;
import org.tec.carpooling.bl.dto.UI_BL.UpdateUserProfileDTO;
import org.tec.carpooling.common.exceptions.NotFoundException;
import org.tec.carpooling.common.exceptions.ValidationException;
import org.tec.carpooling.common.exceptions.OperationFailedException;

public interface UserProfileService {

    /**
     * Retrieves the profile data for a specific user.
     *
     * @param username The username of the user whose profile is to be fetched.
     *                 If null, fetches the profile of the currently authenticated user.
     * @return UserProfileDTO containing the user's profile information.
     * @throws NotFoundException If the user (or current authenticated user) is not found.
     */
    UserProfileDTO getUserProfile(String username); // Username can be from path variable or security context

    /**
     * Updates the profile information for the currently authenticated user.
     *
     * @param dto The DTO containing the fields to update. Null fields are ignored.
     * @return true if the profile was successfully updated.
     * @throws ValidationException If the provided data fails validation rules.
     * @throws NotFoundException If the current authenticated user cannot be found.
     * @throws OperationFailedException If there's an issue updating the profile.
     */
    boolean updateUserProfile(UpdateUserProfileDTO dto);
}