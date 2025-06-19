package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.*;
import org.tec.carpooling.da.repositories.*;
import org.tec.carpooling.ui.UserSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleDataRetrievalServiceImpl implements SimpleDataRetrievalService {

    @Autowired private GenderRepository genderRepository;
    @Autowired private AdministratorRepository administratorRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private InstitutionRepository institutionRepository;
    @Autowired private CountryRepository countryRepository;
    @Autowired private DistrictRepository districtRepository;
    @Autowired private TypeOfCredentialRepository typeOfCredentialRepository;
    @Autowired private ProvinceRepository provinceRepository;
    @Autowired private PersonalUserRepository personalUserRepository;
    @Autowired private InstitutionalEmailRepository institutionalEmailRepository;
    @Autowired private EmailRepository emailRepository;

    @Override
    public List<GenderEntity> getAllGenders() {
        return genderRepository.findAll();
    }

    @Override
    public List<AdministratorEntity> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    @Override
    public List<InstitutionEntity> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    @Override
    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<PersonEntity> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public String getPrimaryDomainForInstitution(Long institutionId) {
        Optional<InstitutionEntity> institutionOptional = institutionRepository.findById(institutionId);
        return institutionOptional
                .map(InstitutionEntity::getEmailDomain)
                .orElse(null);
    }

    @Override
    public List<DistrictEntity> getAllDistrictsInProvince(String provinceName) {
        if (provinceRepository.findByName(provinceName).isEmpty()) {
            return Collections.emptyList();
        }

        return districtRepository.findByCanton_Province_Name(provinceName);
    }

    @Override
    public List<TypeOfCredentialEntity> getAllTypeOfCredentials() {
        return typeOfCredentialRepository.findAll();
    }

    @Override
    public PersonalUserEntity getCurrentUser() {
        String username = UserSession.getInstance().getCurrentUser();
        Optional<PersonalUserEntity> optionalPersonalUser = personalUserRepository.findByUsername(username);
        return optionalPersonalUser.orElse(null);
    }

    @Override
    public InstitutionalEmailEntity getCurrentInstitutionalEmail(PersonalUserEntity person) {
        Optional<InstitutionalEmailEntity> optionalEmail = institutionalEmailRepository.findByPersonalUser(person);
        return optionalEmail.orElse(null);
    }

    @Override
    public EmailEntity getCurrentEmail(PersonEntity person) {
        Optional<EmailEntity> optionalEmail = emailRepository.findByPerson(person);
        return optionalEmail.orElse(null);
    }

}
