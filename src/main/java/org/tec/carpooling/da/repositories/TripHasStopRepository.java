package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.TripHasStopEntity;
import org.tec.carpooling.da.entities.embeddable.TripHasStopId; // Assuming this PK class exists

public class TripHasStopRepository extends BaseRepository<TripHasStopEntity, TripHasStopId> {

    public TripHasStopRepository() {
        super(TripHasStopEntity.class);
    }
}