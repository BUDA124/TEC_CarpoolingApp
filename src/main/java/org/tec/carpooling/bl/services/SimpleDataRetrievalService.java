package org.tec.carpooling.bl.services;

import org.tec.carpooling.da.entities.AdministratorEntity;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;
import org.tec.carpooling.da.entities.PersonEntity;

import java.util.List;

public interface SimpleDataRetrievalService {
    List<GenderEntity> getAllGenders();
    List<AdministratorEntity> getAllAdministrators();
    List<PersonEntity> getAllPersons();
    List<InstitutionEntity> getAllInstitutions();
    String getPrimaryDomainForInstitution(Long institutionId);
}


