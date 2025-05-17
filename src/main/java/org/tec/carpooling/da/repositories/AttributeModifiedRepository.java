package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.AttributeModifiedEntity;

@Repository
public interface AttributeModifiedRepository extends JpaRepository<AttributeModifiedEntity, Long> {}