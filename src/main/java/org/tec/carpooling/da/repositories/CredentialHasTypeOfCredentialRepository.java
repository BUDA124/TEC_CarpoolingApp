package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CredentialEntity;
import org.tec.carpooling.da.entities.CredentialHasTypeOfCredentialEntity;
import org.tec.carpooling.da.entities.TypeOfCredentialEntity;

import java.util.Optional;


@Repository
public interface CredentialHasTypeOfCredentialRepository extends JpaRepository<CredentialHasTypeOfCredentialEntity, Long> {
    Optional<CredentialHasTypeOfCredentialEntity> findByCredentialAndTypeOfCredential(CredentialEntity credentialEntity, TypeOfCredentialEntity licencia);
}