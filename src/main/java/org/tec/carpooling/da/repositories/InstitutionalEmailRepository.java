package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.InstitutionalEmailEntity;

import java.util.Optional;


@Repository
public interface InstitutionalEmailRepository extends JpaRepository<InstitutionalEmailEntity, Long> {
    Optional<InstitutionalEmailEntity> findByEmailAddress(String mail);
}