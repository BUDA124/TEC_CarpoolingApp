package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.TripEntity;

public class TripRepository extends BaseRepository<TripEntity, Long> {

    public TripRepository() {
        super(TripEntity.class);
    }
}