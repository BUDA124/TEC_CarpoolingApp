package org.tec.carpooling.bl.services;

import org.tec.carpooling.bl.dto.BL_DA.UserDTO;
import org.tec.carpooling.bl.dto.UI_BL.LogInDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;

public interface UserService {
    boolean registerNewUser(UserRegistrationDTO dto);
    boolean logInUser(LogInDTO logInDTO);
}
