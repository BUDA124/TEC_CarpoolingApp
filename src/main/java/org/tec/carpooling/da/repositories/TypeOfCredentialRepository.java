package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.TypeOfCredentialEntity;

import java.util.Optional;


@Repository
public interface TypeOfCredentialRepository extends JpaRepository<TypeOfCredentialEntity, Long> {
    Optional<TypeOfCredentialEntity> findByType(String typeName);
}