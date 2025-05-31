package org.tec.carpooling.bl.mappers;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.tec.carpooling.da.entities.InstitutionEntity;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.da.entities.PersonalUserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T15:27:28-0600",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class UserLogInMapperImpl implements UserLogInMapper {

    @Override
    public UserDTO toUserDTO(PersonalUserEntity personalUserEntity) {
        if ( personalUserEntity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( personalUserEntity.getId() );
        userDTO.setUsername( personalUserEntity.getUsername() );
        userDTO.setFirstName( personalUserEntityPersonFirstName( personalUserEntity ) );
        userDTO.setLastName( personalUserEntityPersonFirstSurname( personalUserEntity ) );
        userDTO.setBirthdate( personalUserEntityPersonBirthdate( personalUserEntity ) );
        userDTO.setIdInstitution( personalUserEntityPersonIdInstitution( personalUserEntity ) );

        return userDTO;
    }

    private String personalUserEntityPersonFirstName(PersonalUserEntity personalUserEntity) {
        PersonEntity person = personalUserEntity.getPerson();
        if ( person == null ) {
            return null;
        }
        return person.getFirstName();
    }

    private String personalUserEntityPersonFirstSurname(PersonalUserEntity personalUserEntity) {
        PersonEntity person = personalUserEntity.getPerson();
        if ( person == null ) {
            return null;
        }
        return person.getFirstSurname();
    }

    private LocalDate personalUserEntityPersonBirthdate(PersonalUserEntity personalUserEntity) {
        PersonEntity person = personalUserEntity.getPerson();
        if ( person == null ) {
            return null;
        }
        return person.getBirthdate();
    }

    private InstitutionEntity personalUserEntityPersonIdInstitution(PersonalUserEntity personalUserEntity) {
        PersonEntity person = personalUserEntity.getPerson();
        if ( person == null ) {
            return null;
        }
        return person.getIdInstitution();
    }
}
