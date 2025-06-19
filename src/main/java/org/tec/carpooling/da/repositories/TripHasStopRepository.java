package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.StopEntity;
import org.tec.carpooling.da.entities.TripEntity;
import org.tec.carpooling.da.entities.TripHasStopEntity;

import java.util.Optional;


@Repository
public interface TripHasStopRepository extends JpaRepository<TripHasStopEntity, Long> {
    @Query("SELECT AVG(ths.stopCost) FROM TripHasStopEntity ths WHERE ths.stopCost > 0")
    Double findAverageFare();
}