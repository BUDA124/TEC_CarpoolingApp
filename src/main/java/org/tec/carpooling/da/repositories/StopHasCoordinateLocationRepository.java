package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CoordinateLocationEntity;
import org.tec.carpooling.da.entities.StopEntity;
import org.tec.carpooling.da.entities.StopHasCoordinateLocationEntity;

import java.util.Optional;


@Repository
public interface StopHasCoordinateLocationRepository extends JpaRepository<StopHasCoordinateLocationEntity, Long> {
    Optional<StopHasCoordinateLocationEntity> findByStopAndCoordinateLocation(StopEntity ucrCampus, CoordinateLocationEntity ucr);
}