package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.*;
import org.tec.carpooling.da.repositories.*;

import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CantonRepository cantonRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

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

    public List<DistrictEntity> getAllDistrictsInProvince(String provinceName) {
        if (provinceRepository.findByName(provinceName).isEmpty()) {
            return Collections.emptyList();
        }

        return districtRepository.findByCanton_Province_Name(provinceName);
    }
}
