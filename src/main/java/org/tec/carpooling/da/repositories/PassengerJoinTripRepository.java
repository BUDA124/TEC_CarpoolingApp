package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PassengerJoinTripEntity;
import org.tec.carpooling.da.entities.PersonalUserEntity;
import org.tec.carpooling.da.entities.TripEntity;

import java.util.Optional;


@Repository
public interface PassengerJoinTripRepository extends JpaRepository<PassengerJoinTripEntity, Long> {
    Optional<PassengerJoinTripEntity> findByPersonalUserAndTrip(PersonalUserEntity personalUserEntity, TripEntity tripEntity);
}