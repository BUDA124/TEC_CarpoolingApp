package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.TripHasTripStatusEntity;
import org.tec.carpooling.da.entities.embeddable.TripHasTripStatusId; // Assuming this PK class exists

public class TripHasTripStatusRepository extends BaseRepository<TripHasTripStatusEntity, TripHasTripStatusId> {

    public TripHasTripStatusRepository() {
        super(TripHasTripStatusEntity.class);
    }
}