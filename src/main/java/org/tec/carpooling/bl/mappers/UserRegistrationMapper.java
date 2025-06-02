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

    default String map(CountryEntity value) {
        return value == null ? null : value.getName();
    };

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "secondName", source = "secondName")
    @Mapping(target = "firstSurname", source = "firstSurname")
    @Mapping(target = "secondSurname", source = "secondSurname")
    @Mapping(target = "birthdate", source = "birthdate")
    @Mapping(target = "nationality", source = "nationality")
    @Mapping(target = "profilePicture", ignore = true)
    @Mapping(target = "idInstitution", source = "idInstitution")
    @Mapping(target = "idGender", source = "idGender")
    @Mapping(target = "idAuditLog", ignore = true)
    PersonEntity toPersonEntity(UserRegistrationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password")
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "auditLog", ignore = true)
    PersonalUserEntity toPersonalUserEntity(UserRegistrationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emailAddress", source = "institutionalEmail")
    @Mapping(target = "personalUser", ignore = true)
    @Mapping(target = "auditLog", ignore = true)
    InstitutionalEmailEntity toInstitutionalEmailEntity(UserRegistrationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "numberOfCredential", source = "credentialNumber")
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "auditLog", ignore = true)
    CredentialEntity toCredentialEntity(UserRegistrationDTO dto);
}