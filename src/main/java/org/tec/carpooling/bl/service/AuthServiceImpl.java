package org.tec.carpooling.bl.service;

import org.tec.carpooling.bl.dto.LoginRequestDTO;
import org.tec.carpooling.bl.dto.LoginResponseDTO;
import org.tec.carpooling.bl.dto.UserRegistrationDTO;

import org.tec.carpooling.bl.validation.UserRegistrationValidator;

import org.tec.carpooling.common.utils.HashingUtil;
import org.tec.carpooling.common.exceptions.AuthenticationException;
import org.tec.carpooling.common.exceptions.RegistrationException;
import org.tec.carpooling.common.exceptions.ValidationException;

import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.da.repositories.AuditLogRepository;
import org.tec.carpooling.da.repositories.PersonRepository;
import org.tec.carpooling.da.repositories.PersonalUserRepository;
import org.tec.carpooling.da.repositories.InstitutionRepository;
import org.tec.carpooling.da.repositories.InstitutionalEmailRepository;
import org.tec.carpooling.da.repositories.GenderRepository;

import java.time.LocalDate;

public class AuthServiceImpl implements AuthService {

    private final PersonRepository personRepository;
    private final PersonalUserRepository personalUserRepository;
    private final InstitutionRepository institutionRepository;
    private final InstitutionalEmailRepository institutionalEmailRepository;
    private final GenderRepository genderRepository;
    private final AuditLogRepository auditLogRepository; // For creating audit log entries
    private final UserRegistrationValidator registrationValidator;

    public AuthServiceImpl() {
        this.personRepository = new PersonRepository();
        this.personalUserRepository = new PersonalUserRepository();
        this.institutionRepository = new InstitutionRepository();
        this.institutionalEmailRepository = new InstitutionalEmailRepository();
        this.genderRepository = new GenderRepository();
        this.auditLogRepository = new AuditLogRepository(); // Don't forget this
        this.registrationValidator = new UserRegistrationValidator();
    }

    public AuthServiceImpl(PersonRepository personRepository,
                           PersonalUserRepository personalUserRepository,
                           InstitutionRepository institutionRepository,
                           InstitutionalEmailRepository institutionalEmailRepository,
                           GenderRepository genderRepository,
                           AuditLogRepository auditLogRepository,
                           UserRegistrationValidator registrationValidator) {
        this.personRepository = personRepository;
        this.personalUserRepository = personalUserRepository;
        this.institutionRepository = institutionRepository;
        this.institutionalEmailRepository = institutionalEmailRepository;
        this.genderRepository = genderRepository;
        this.auditLogRepository = auditLogRepository;
        this.registrationValidator = registrationValidator;
    }


    @Override
    public void registerUser(UserRegistrationDTO dto) throws RegistrationException, ValidationException {}

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws AuthenticationException {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        return loginResponseDTO;
    }

    @Override
    public void logout(Long userId) {}

    @Override
    public boolean verifyInstitutionalEmail(String email, Long institutionId) {
        // This would involve calling the external API provided by the institution.
        // For now, simulate it:
        System.out.println("Simulating verification for email: " + email + " at institution ID: " + institutionId);
        // Call external API...
        // return true; // If successful from API
        if (email.endsWith(".edu") || email.endsWith(".ac.uk")) { // Dummy check
            return true;
        }
        return false;
    }
}