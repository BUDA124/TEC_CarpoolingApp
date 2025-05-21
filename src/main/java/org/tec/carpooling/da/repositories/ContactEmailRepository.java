package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.ContactEmailEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;

import java.util.Optional;


@Repository
public interface ContactEmailRepository extends JpaRepository<ContactEmailEntity, Long> {
    Optional<ContactEmailEntity> findByAddress(String mail);
}