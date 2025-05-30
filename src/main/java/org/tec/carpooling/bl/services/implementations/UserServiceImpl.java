package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tec.carpooling.bl.dto.BL_DA.UserInstitutionInfoDTO;
import org.tec.carpooling.bl.dto.UI_BL.LogInDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserAcceptTermsDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.bl.mappers.UserLogInMapper;
import org.tec.carpooling.bl.mappers.UserRegistrationMapper;
import org.tec.carpooling.bl.services.UserService;
import org.tec.carpooling.common.exceptions.AuthenticationException;
import org.tec.carpooling.common.utils.HashingUtil;
import org.tec.carpooling.da.entities.*;
import org.tec.carpooling.da.repositories.*;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonalUserRepository personalUserRepository;

    @Autowired
    private InstitutionalEmailRepository institutionalEmailRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private UserRegistrationMapper userRegistrationMapper;

    @Autowired
    private UserLogInMapper userLogInMapper;

    @Override
    @Transactional
    public boolean registerNewUser(UserRegistrationDTO dto) {

        PersonEntity person = userRegistrationMapper.toPersonEntity(dto);
        person = personRepository.save(person);

        PersonalUserEntity personalUser = userRegistrationMapper.toPersonalUserEntity(dto);
        personalUser.setPerson(person);
        personalUser.setRegistrationDate(LocalDate.now());

        personalUser = personalUserRepository.save(personalUser);

        InstitutionalEmailEntity institutionalEmail = userRegistrationMapper.toInstitutionalEmailEntity(dto);
        institutionalEmail.setPersonalUser(personalUser);
        institutionalEmailRepository.save(institutionalEmail);

        CredentialEntity credential = userRegistrationMapper.toCredentialEntity(dto);
        credential.setPerson(person);
        credentialRepository.save(credential);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean logInUser(LogInDTO logInDTO) {
        Optional<PersonalUserEntity> userEntityOptional = personalUserRepository.findByUsername(logInDTO.getUsername());

        if (userEntityOptional.isEmpty()) {
            throw new AuthenticationException("Invalid username or password"); // User not found
        }

        PersonalUserEntity userEntity = userEntityOptional.get();

        if (!HashingUtil.verifyPassword(logInDTO.getPassword(), userEntity.getPassword())) {
            throw new AuthenticationException("Invalid username or password"); // Incorrect password
        }

        return true;
    }

    @Override
    public boolean acceptTermsAndConditions(UserAcceptTermsDTO dto) {
        return false;
    }

    @Override
    public UserInstitutionInfoDTO getUserInstitutionInfo(String username) {
        return null;
    }


}
