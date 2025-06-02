package org.tec.carpooling.bl.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.tec.carpooling.bl.dto.UI_BL.LogInDTO;
import org.tec.carpooling.da.entities.PersonalUserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T17:58:07-0600",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class UserLogInMapperImpl implements UserLogInMapper {

    @Override
    public LogInDTO toUserDTO(PersonalUserEntity personalUserEntity) {
        if ( personalUserEntity == null ) {
            return null;
        }

        LogInDTO logInDTO = new LogInDTO();

        logInDTO.setUsername( personalUserEntity.getUsername() );
        logInDTO.setPassword( personalUserEntity.getPassword() );

        return logInDTO;
    }
}
