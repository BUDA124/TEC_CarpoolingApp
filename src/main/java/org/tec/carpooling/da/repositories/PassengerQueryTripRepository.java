package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.PassengerQueryTripEntity;
import org.tec.carpooling.da.entities.embeddable.PassengerQueryTripId; // Assuming this PK class exists

public class PassengerQueryTripRepository extends BaseRepository<PassengerQueryTripEntity, PassengerQueryTripId> {

    public PassengerQueryTripRepository() {
        super(PassengerQueryTripEntity.class);
    }
}