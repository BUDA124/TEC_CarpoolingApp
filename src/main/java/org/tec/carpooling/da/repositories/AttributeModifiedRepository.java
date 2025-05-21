package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.AttributeModifiedEntity;
import org.tec.carpooling.da.entities.EntityModifiedEntity;

import java.util.Optional;

@Repository
public interface AttributeModifiedRepository extends JpaRepository<AttributeModifiedEntity, Long> {
    Optional<AttributeModifiedEntity> findByEntityModifiedAndAttributeNameAndOldValue(EntityModifiedEntity em1, String status, String programado);

    Optional<AttributeModifiedEntity> findByEntityModifiedAndAttributeNameAndOldValueAndNewValue(EntityModifiedEntity em2, String userStatus, String inactivo, String activo);
}