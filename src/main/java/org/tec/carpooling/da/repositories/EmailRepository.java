package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.EmailEntity;
import org.tec.carpooling.da.entities.PersonEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    Optional<EmailEntity> findByEmailAddressAndPerson(String mail, PersonEntity carlos);
}