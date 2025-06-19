package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tec.carpooling.bl.dto.UI_BL.UpdateUserProfileDTO;
import org.tec.carpooling.bl.dto.UserProfileDTO;
import org.tec.carpooling.bl.services.UserProfileService;
import org.tec.carpooling.common.exceptions.NotFoundException;
import org.tec.carpooling.common.exceptions.OperationFailedException;
import org.tec.carpooling.common.exceptions.ValidationException;
import org.tec.carpooling.common.utils.HashingUtil;
import org.tec.carpooling.da.entities.*;
import org.tec.carpooling.da.repositories.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired private PersonalUserRepository personalUserRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private InstitutionalEmailRepository institutionalEmailRepository;
    @Autowired private EmailRepository emailRepository;
    @Autowired private GenderRepository genderRepository;

    @Override
    @Transactional(readOnly = true)
    public UserProfileDTO getUserProfile(String username) throws NotFoundException {
        PersonalUserEntity user = personalUserRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username '" + username + "' not found."));

        PersonEntity person = user.getPerson();
        if (person == null) throw new NotFoundException("Person data not found for user: " + username);

        InstitutionalEmailEntity institutionalEmail = institutionalEmailRepository.findByPersonalUser(user)
                .orElseThrow(() -> new NotFoundException("Institutional email not found for user: " + username));

        Optional<EmailEntity> personalEmail = emailRepository.findByPerson(person);
        if (personalEmail.isEmpty()) {
            throw new NotFoundException("Personal email not found for user: " + username);
        }

        // 2. Obtener datos adicionales
        List<GenderEntity> allGenders = genderRepository.findAll();

        // 3. Crear y devolver el DTO
        return new UserProfileDTO(
                person.getFirstName() + " " + person.getFirstSurname(),
                person.getId().toString(),
                personalEmail.get().getEmailAddress(),
                institutionalEmail.getEmailAddress(),
                person.getBirthdate(),
                person.getIdGender(),
                allGenders
        );
    }

    @Override
    @Transactional
    public void updateUserProfile(UpdateUserProfileDTO dto) throws ValidationException, NotFoundException, OperationFailedException {

        // 1. Obtener las entidades de la base de datos
        PersonalUserEntity currentUser = personalUserRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found during update process."));

        PersonEntity currentPerson = currentUser.getPerson();
        InstitutionalEmailEntity institutionalMail = institutionalEmailRepository.findByPersonalUser(currentUser)
                .orElseThrow(() -> new NotFoundException("Institutional email not found for user."));

        // 2. Lógica de validación (movida desde el controlador)
        if (dto.isPasswordChangeRequested()) {
            // Verificar si la contraseña actual es correcta
            if (!HashingUtil.verifyPassword(dto.getCurrentPassword(), currentUser.getPassword())) {
                throw new ValidationException(Collections.singleton("The current password you entered is incorrect."));
            }
        }

        Optional<EmailEntity> personalEmail = emailRepository.findByPerson(currentPerson);
        if (personalEmail.isEmpty()) {
            throw new NotFoundException("Personal email not found for user.");
        }
        EmailEntity personalEmailEntity = personalEmail.get();
        personalEmailEntity.setEmailAddress(dto.getPersonalEmail());

        institutionalMail.setEmailAddress(dto.getInstitutionalEmail());
        currentPerson.setIdGender(dto.getSelectedGender());
        currentPerson.setBirthdate(dto.getBirthdate());

        if (dto.isPasswordChangeRequested()) {
            currentUser.setPassword(HashingUtil.hashPassword(dto.getNewPassword()));
        }

        // 4. Guardar las entidades actualizadas
        try {
            emailRepository.save(personalEmailEntity);
            personRepository.save(currentPerson);
            institutionalEmailRepository.save(institutionalMail);
            personalUserRepository.save(currentUser);
        } catch (Exception e) {
            throw new OperationFailedException("Failed to save profile updates to the database.", e);
        }
    }
}