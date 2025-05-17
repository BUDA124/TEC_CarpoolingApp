package org.tec.carpooling.bl.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.da.entities.*; // Assuming GenderEntity is also in this package or imported

import java.time.LocalDate; // Not strictly needed by the mapper if types match

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    @Named("longToInstitutionEntity")
    default InstitutionEntity longToInstitutionEntity(Long id) {
        if (id == null) {
            return null;
        }
        InstitutionEntity institution = new InstitutionEntity();
        institution.setId(id);
        // Nota: Otros campos de InstitutionEntity no se llenan aquí.
        // JPA usará este objeto con ID para la relación.
        return institution;
    }

    @Named("longToGenderEntity")
    default GenderEntity longToGenderEntity(Long id) {
        if (id == null) {
            return null;
        }
        GenderEntity gender = new GenderEntity(); // Asumiendo que GenderEntity existe y tiene setId
        gender.setId(id);
        return gender;
    }

    @Named("longToTypeOfCredentialEntity")
    default TypeOfCredentialEntity longToTypeOfCredentialEntity(Long id) {
        if (id == null) {
            return null;
        }
        TypeOfCredentialEntity typeOfCredential = new TypeOfCredentialEntity();
        typeOfCredential.setId(id);
        return typeOfCredential;
    }

    @Mapping(target = "id", ignore = true) // ID es autogenerado
    @Mapping(target = "profilePicture", ignore = true)
    @Mapping(target = "idInstitution", source = "idInstitution", qualifiedByName = "longToInstitutionEntity")
    @Mapping(target = "idGender", source = "idGender", qualifiedByName = "longToGenderEntity")
    @Mapping(target = "idAuditLog", ignore = true) // Se asignará en la capa de servicio
    PersonEntity toPersonEntity(UserRegistrationDTO dto);

    @Mapping(target = "id", ignore = true) // ID es autogenerado
    @Mapping(target = "password", source = "password") // La contraseña se pasará en texto plano, el servicio la hasheará
    @Mapping(target = "registrationDate", ignore = true) // Se asignará en la capa de servicio (e.g., LocalDate.now())
    @Mapping(target = "person", ignore = true) // Se asignará en la capa de servicio
    @Mapping(target = "auditLog", ignore = true) // Se asignará en la capa de servicio
    PersonalUserEntity toPersonalUserEntity(UserRegistrationDTO dto);

    @Mapping(target = "id", ignore = true) // ID es autogenerado
    @Mapping(target = "emailAddress", source = "email")
    @Mapping(target = "person", ignore = true) // Se asignará en la capa de servicio
    @Mapping(target = "auditLog", ignore = true) // Se asignará en la capa de servicio
    EmailEntity toEmailEntity(UserRegistrationDTO dto);

    // --- Mapeo a InstitutionalEmailEntity ---
    @Mapping(target = "id", ignore = true) // ID es autogenerado
    @Mapping(target = "emailAddress", source = "institutionalEmail")
    @Mapping(target = "personalUser", ignore = true) // Se asignará en la capa de servicio
    @Mapping(target = "auditLog", ignore = true) // Se asignará en la capa de servicio
    InstitutionalEmailEntity toInstitutionalEmailEntity(UserRegistrationDTO dto);

    // --- Mapeo a CredentialEntity ---
    @Mapping(target = "id", ignore = true) // ID es autogenerado
    @Mapping(target = "numberOfCredential", source = "credentialNumber")
    @Mapping(target = "isActive", ignore = true) // Se asignará en la capa de servicio (e.g., a 1 por defecto)
    @Mapping(target = "person", ignore = true) // Se asignará en la capa de servicio
    @Mapping(target = "auditLog", ignore = true) // Se asignará en la capa de servicio
    @Mapping(target = "typeOfCredential", source = "idTypeOfCredential", qualifiedByName = "longToTypeOfCredentialEntity") // Mapea el ID a la entidad TypeOfCredential
    CredentialEntity toCredentialEntity(UserRegistrationDTO dto);

}