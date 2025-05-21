package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CoordinateLocationEntity;

import java.util.Optional;


@Repository
public interface CoordinateLocationRepository extends JpaRepository<CoordinateLocationEntity, Long> {
    Optional<CoordinateLocationEntity> findByYCoordinateAndXCoordinate(float v, float v1);
}