package org.tec.carpooling.bl.services;

import org.tec.carpooling.da.entities.*;

import java.util.List;

public interface SimpleDataRetrievalService {
    List<GenderEntity> getAllGenders();
    List<AdministratorEntity> getAllAdministrators();
    List<PersonEntity> getAllPersons();
    List<InstitutionEntity> getAllInstitutions();
    List<CountryEntity> getAllCountries();
    String getPrimaryDomainForInstitution(Long institutionId);
    List<DistrictEntity> getAllDistrictsInProvince(String province);
    List<TypeOfCredentialEntity> getAllTypeOfCredentials();
    PersonalUserEntity getCurrentUser();
    InstitutionalEmailEntity getCurrentInstitutionalEmail(PersonalUserEntity person);
    EmailEntity getCurrentEmail(PersonEntity person);
}


