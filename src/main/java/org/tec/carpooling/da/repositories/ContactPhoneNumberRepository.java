package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.ContactPhoneNumberEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;

import java.util.Optional;


@Repository
public interface ContactPhoneNumberRepository extends JpaRepository<ContactPhoneNumberEntity, Long> {
    Optional<ContactPhoneNumberEntity> findByPhoneNumberAndInstitution(String s, InstitutionEntity ucr);
}