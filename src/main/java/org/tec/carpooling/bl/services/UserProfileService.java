package org.tec.carpooling.bl.services;

import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.dto.UI_BL.UpdateUserProfileDTO;
import org.tec.carpooling.bl.dto.UserProfileDTO;
import org.tec.carpooling.common.exceptions.NotFoundException;
import org.tec.carpooling.common.exceptions.ValidationException;
import org.tec.carpooling.common.exceptions.OperationFailedException;

@Service
public interface UserProfileService {

    /**
     * Retrieves all necessary data to display the user profile.
     *
     * @param username The username of the user whose profile is to be retrieved.
     * @return A DTO containing all profile information.
     * @throws NotFoundException if the user or related data cannot be found.
     */
    UserProfileDTO getUserProfile(String username) throws NotFoundException;

    /**
     * Updates the profile information for a given user.
     *
     * @param dto The DTO containing the fields to update.
     * @throws ValidationException      If the provided data fails validation rules (e.g., incorrect password).
     * @throws NotFoundException        If the user to update cannot be found.
     * @throws OperationFailedException If there's an issue saving the changes.
     */
    void updateUserProfile(UpdateUserProfileDTO dto) throws ValidationException, NotFoundException, OperationFailedException;
}