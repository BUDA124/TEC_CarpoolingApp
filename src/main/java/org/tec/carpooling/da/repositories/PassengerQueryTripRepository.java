package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PassengerQueryTripEntity;
import org.tec.carpooling.da.entities.PersonalUserEntity;
import org.tec.carpooling.da.entities.TripEntity;
import org.tec.carpooling.da.entities.PersonalUserEntity;


@Repository
public interface PassengerQueryTripRepository extends JpaRepository<PassengerQueryTripEntity, Long> {
    boolean existsByPersonalUserAndTrip(PersonalUserEntity personalUserEntity, TripEntity tripEntity);
}