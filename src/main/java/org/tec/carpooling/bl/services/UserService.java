package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;

public interface UserService {
    String registerNewUser(UserRegistrationDTO dto);
}
