package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.TripEntity;
import org.tec.carpooling.da.entities.TripHasTripStatusEntity;
import org.tec.carpooling.da.entities.TripStatusEntity;


@Repository
public interface TripHasTripStatusRepository extends JpaRepository<TripHasTripStatusEntity, Long> {
    boolean existsByTripAndTripStatus(TripEntity tripEntity, TripStatusEntity tripStatusEntity);
}