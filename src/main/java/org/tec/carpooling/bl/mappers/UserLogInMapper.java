package org.tec.carpooling.bl.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.tec.carpooling.da.entities.PersonalUserEntity;

@Mapper(componentModel = "spring")
public interface UserLogInMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "person.firstName", target = "firstName"),
            @Mapping(source = "person.firstSurname", target = "lastName"),
            @Mapping(source = "person.birthdate", target = "birthdate"),
            @Mapping(source = "person.idInstitution", target = "idInstitution")
    })
    UserDTO toUserDTO(PersonalUserEntity personalUserEntity);
}