package org.tec.carpooling.bl.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.tec.carpooling.bl.dto.UI_BL.LogInDTO;
import org.tec.carpooling.da.entities.PersonalUserEntity;

@Mapper(componentModel = "spring")
public interface UserLogInMapper {

    @Mappings({
            @Mapping(source = "username", target = "username"),
    })
    LogInDTO toUserDTO(PersonalUserEntity personalUserEntity);
}