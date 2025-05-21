package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.EntityModifiedEntity;
import org.tec.carpooling.da.entities.LogBookEntity;
import org.tec.carpooling.da.entities.LogBookHasEntityModifiedEntity;

import java.util.Optional;


@Repository
public interface LogBookHasEntityModifiedRepository extends JpaRepository<LogBookHasEntityModifiedEntity, Long> {
    Optional<LogBookHasEntityModifiedEntity> findByLogBookAndEntityModified(LogBookEntity logBookEntity, EntityModifiedEntity entityModifiedEntity);
}