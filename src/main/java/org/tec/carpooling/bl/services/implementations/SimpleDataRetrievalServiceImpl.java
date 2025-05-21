package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.*;
import org.tec.carpooling.da.repositories.*;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleDataRetrievalServiceImpl implements SimpleDataRetrievalService {

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private CountryRepository countryRepository;

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
                .map(InstitutionEntity::getEmailDomain) // If present, applies getEmailDomain, returns Optional<String>
                .orElse(null); // If Optional<String> is empty (or original was empty), return null
    }
}
