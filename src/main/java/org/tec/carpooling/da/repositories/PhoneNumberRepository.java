package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PhoneNumberEntity;

import java.util.List;


@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Long> {
    List<PhoneNumberEntity> findByIdPerson(Long idPerson);
}