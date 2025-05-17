package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.EmailEntity;

import java.util.List;


@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    boolean existsByEmailAddress(String email);
    List<EmailEntity> findByIdPerson(Long idPersonId);
}