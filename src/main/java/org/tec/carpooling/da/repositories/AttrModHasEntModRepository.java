package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.AttrModHasEntModEntity;
import org.tec.carpooling.da.entities.AttributeModifiedEntity;
import org.tec.carpooling.da.entities.LogBookEntity;

import java.util.Optional;

@Repository
public interface AttrModHasEntModRepository extends JpaRepository<AttrModHasEntModEntity, Long> {
    Optional<AttrModHasEntModEntity> findByLogBookAndAttributeModified(LogBookEntity logBookEntity, AttributeModifiedEntity attributeModifiedEntity);
}