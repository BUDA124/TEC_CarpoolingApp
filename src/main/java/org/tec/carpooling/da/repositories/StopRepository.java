package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.StopEntity;

public class StopRepository extends BaseRepository<StopEntity, Long> {

    public StopRepository() {
        super(StopEntity.class);
    }
}