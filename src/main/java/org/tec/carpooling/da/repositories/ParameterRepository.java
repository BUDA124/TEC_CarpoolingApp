package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.ParameterEntity;

import java.util.Optional;


@Repository
public interface ParameterRepository extends JpaRepository<ParameterEntity, Long> {
    Optional<ParameterEntity> findByName(String name);
}