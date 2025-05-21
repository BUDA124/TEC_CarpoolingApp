package org.tec.carpooling.bl.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.da.entities.CredentialEntity;
import org.tec.carpooling.da.entities.EmailEntity;
import org.tec.carpooling.da.entities.InstitutionalEmailEntity;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.da.entities.PersonalUserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T16:36:36-0600",
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

        personEntity.setIdInstitution( longToInstitutionEntity( dto.getIdInstitution() ) );
        personEntity.setIdGender( longToGenderEntity( dto.getIdGender() ) );
        personEntity.setNationality( dto.getNationality() );
        personEntity.setBirthdate( dto.getBirthdate() );
        personEntity.setSecondSurname( dto.getSecondSurname() );
        personEntity.setFirstSurname( dto.getFirstSurname() );
        personEntity.setSecondName( dto.getSecondName() );
        personEntity.setFirstName( dto.getFirstName() );

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
    public EmailEntity toEmailEntity(UserRegistrationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EmailEntity emailEntity = new EmailEntity();

        emailEntity.setEmailAddress( dto.getEmail() );

        return emailEntity;
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
