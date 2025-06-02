package org.tec.carpooling.bl.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.da.entities.CredentialEntity;
import org.tec.carpooling.da.entities.InstitutionalEmailEntity;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.da.entities.PersonalUserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T17:58:07-0600",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class UserRegistrationMapperImpl implements UserRegistrationMapper {

    @Override
    public PersonEntity toPersonEntity(UserRegistrationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setFirstName( dto.getFirstName() );
        personEntity.setSecondName( dto.getSecondName() );
        personEntity.setFirstSurname( dto.getFirstSurname() );
        personEntity.setSecondSurname( dto.getSecondSurname() );
        personEntity.setBirthdate( dto.getBirthdate() );
        personEntity.setNationality( map( dto.getNationality() ) );
        personEntity.setIdInstitution( dto.getIdInstitution() );
        personEntity.setIdGender( dto.getIdGender() );

        return personEntity;
    }

    @Override
    public PersonalUserEntity toPersonalUserEntity(UserRegistrationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PersonalUserEntity personalUserEntity = new PersonalUserEntity();

        personalUserEntity.setPassword( dto.getPassword() );
        personalUserEntity.setUsername( dto.getUsername() );

        return personalUserEntity;
    }

    @Override
    public InstitutionalEmailEntity toInstitutionalEmailEntity(UserRegistrationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        InstitutionalEmailEntity institutionalEmailEntity = new InstitutionalEmailEntity();

        institutionalEmailEntity.setEmailAddress( dto.getInstitutionalEmail() );

        return institutionalEmailEntity;
    }

    @Override
    public CredentialEntity toCredentialEntity(UserRegistrationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CredentialEntity credentialEntity = new CredentialEntity();

        credentialEntity.setNumberOfCredential( dto.getCredentialNumber() );

        return credentialEntity;
    }
}
