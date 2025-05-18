package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.bl.mappers.UserRegistrationMapper;
import org.tec.carpooling.bl.services.UserService;
import org.tec.carpooling.da.entities.*;
import org.tec.carpooling.da.repositories.*;

import java.time.LocalDate;

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
    private InstitutionRepository institutionRepository;

    @Autowired
    private UserRegistrationMapper userRegistrationMapper;

    @Override
    @Transactional
    public String registerNewUser(UserRegistrationDTO dto) {
        // Mapear DTO a entidades
        PersonEntity person = userRegistrationMapper.toPersonEntity(dto);
        person = personRepository.save(person);

        PersonalUserEntity personalUser = userRegistrationMapper.toPersonalUserEntity(dto);
        personalUser.setPerson(person);
        personalUser.setRegistrationDate(LocalDate.now());
        personalUser.setStatus("Pending Verification"); // Default

        personalUser = personalUserRepository.save(personalUser);

        InstitutionalEmailEntity institutionalEmail = userRegistrationMapper.toInstitutionalEmailEntity(dto);
        institutionalEmail.setPersonalUser(personalUser);
        institutionalEmailRepository.save(institutionalEmail);

        CredentialEntity credential = userRegistrationMapper.toCredentialEntity(dto);
        credential.setPerson(person);
        credentialRepository.save(credential);

        return "User registered successfully.";
    }
}
