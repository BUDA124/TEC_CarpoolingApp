package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.PassengerJoinTripEntity;
import org.tec.carpooling.da.entities.embeddable.PassengerJoinTripId; // Assuming this PK class exists

public class PassengerJoinTripRepository extends BaseRepository<PassengerJoinTripEntity, PassengerJoinTripId> {

    public PassengerJoinTripRepository() {
        super(PassengerJoinTripEntity.class);
    }
}