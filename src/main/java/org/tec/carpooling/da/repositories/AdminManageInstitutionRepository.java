package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.AdminManageInstitutionEntity;
import org.tec.carpooling.da.entities.AdministratorEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;

import java.util.Optional;

@Repository
public interface AdminManageInstitutionRepository extends JpaRepository<AdminManageInstitutionEntity, Long> {
    Optional<AdminManageInstitutionEntity> findByAdministratorAndInstitution(AdministratorEntity administratorEntity, InstitutionEntity tec);
}