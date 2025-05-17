package org.tec.carpooling.bl.services.implementations;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tec.carpooling.bl.dto.BL_DA.UserDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.bl.services.UserService;
import org.tec.carpooling.common.exceptions.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {}
    
    @Transactional
    @Override
    public UserDTO registerNewUser(UserRegistrationDTO dto) {
        return new UserDTO();
    }

    @Transactional
    @Override
    public UserDTO loginUser(String username, String rawPassword) throws AuthenticationException {
        return new UserDTO();
    }
}