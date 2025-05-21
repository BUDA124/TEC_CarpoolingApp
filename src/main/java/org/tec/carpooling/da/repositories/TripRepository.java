package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.DriverEntity;
import org.tec.carpooling.da.entities.TripEntity;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long> {
    Optional<TripEntity> findByDriverAndDepartureDateTime(DriverEntity driverEntity, LocalDateTime of);
}