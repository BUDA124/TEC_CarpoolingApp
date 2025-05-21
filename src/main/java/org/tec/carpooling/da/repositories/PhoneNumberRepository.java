package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.da.entities.PhoneNumberEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Long> {
    Optional<PhoneNumberEntity> findByPhoneNumberAndPerson(String s, PersonEntity carlos);
}