package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.AccessStatusEntity;
import org.tec.carpooling.da.entities.AdministratorEntity;

import java.util.Optional;

@Repository
public interface AccessStatusRepository extends JpaRepository<AccessStatusEntity, Long> {
    Optional<AccessStatusEntity> findByAdministrator(AdministratorEntity anaAdmin);
}