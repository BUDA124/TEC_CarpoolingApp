package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CredentialEntity;
import org.tec.carpooling.da.entities.PersonEntity;

import java.util.Optional;


@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {
    Optional<CredentialEntity> findByNumberOfCredential(String s);
}