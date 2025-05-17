package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tec.carpooling.bl.dto.BL_DA.UserDTO;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.bl.services.UserService;
import org.tec.carpooling.common.exceptions.AuthenticationException;
import org.tec.carpooling.common.exceptions.EntityNotFoundException;
import org.tec.carpooling.da.entities.AuditLogEntity;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.da.repositories.AuditLogRepository;
import org.tec.carpooling.da.repositories.GenderRepository;
import org.tec.carpooling.da.repositories.InstitutionRepository;
import org.tec.carpooling.da.repositories.PersonRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private PersonRepository personRepository;

    public UserServiceImpl() {}
    
    @Transactional
    @Override
    public void registerNewUser(UserRegistrationDTO dto) {
        // 1. Crear y guardar AuditLog primero si es necesario para otras entidades
        // Esto depende de tu lógica, a veces AuditLog se crea y se pasa
        AuditLogEntity auditLog = new AuditLogEntity();
        // ... setear campos del auditLog ...
        auditLog = auditLogRepository.save(auditLog); // Guardar para obtener su ID

        // 2. Obtener las entidades relacionadas
        GenderEntity gender = genderRepository.findById(dto.getIdGender())
                .orElseThrow(() -> new EntityNotFoundException("Gender no encontrado con ID: " + dto.getIdGender()));

        InstitutionEntity institution = institutionRepository.findById(dto.getIdInstitution())
                .orElseThrow(() -> new EntityNotFoundException("Institution no encontrada con ID: " + dto.getIdInstitution()));

        // 3. Mapear los campos básicos del DTO a la entidad
        PersonEntity personEntity = new PersonEntity(); // O usa un mapper si lo tienes configurado
        personEntity.setFirstName(dto.getFirstName());
        personEntity.setBirthdate(dto.getBirthdate());
        // ... mapear otros campos directos ...

        // 4. Asignar las entidades relacionadas (las instancias completas) a la PersonEntity
        personEntity.setIdGender(gender);
        personEntity.setIdInstitution(institution);
        personEntity.setIdAuditLog(auditLog); // Asignar el AuditLog creado/obtenido

        // 5. Guardar la PersonEntity
        personRepository.save(personEntity);
    }

    @Transactional
    @Override
    public UserDTO loginUser(String username, String rawPassword) throws AuthenticationException {
        return new UserDTO();
    }
}