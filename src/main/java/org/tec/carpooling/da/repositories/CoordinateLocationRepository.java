package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.CoordinateLocationEntity;

public class CoordinateLocationRepository extends BaseRepository<CoordinateLocationEntity, Long> {

    public CoordinateLocationRepository() {
        super(CoordinateLocationEntity.class);
    }
}