package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.TripStatusEntity;

public class TripStatusRepository extends BaseRepository<TripStatusEntity, Long> {

    public TripStatusRepository() {
        super(TripStatusEntity.class);
    }
}