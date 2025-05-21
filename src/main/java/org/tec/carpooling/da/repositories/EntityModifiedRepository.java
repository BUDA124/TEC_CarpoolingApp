package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.EntityModifiedEntity;

import java.util.Optional;


@Repository
public interface EntityModifiedRepository extends JpaRepository<EntityModifiedEntity, Long> {
    Optional<EntityModifiedEntity> findByEntityName(String trip);
}