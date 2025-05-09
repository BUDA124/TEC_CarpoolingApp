package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.StopHasCoordinateLocationEntity;
import org.tec.carpooling.da.entities.embeddable.StopHasCoordinateLocationId; // Assuming this PK class exists

public class StopHasCoordinateLocationRepository extends BaseRepository<StopHasCoordinateLocationEntity, StopHasCoordinateLocationId> {

    public StopHasCoordinateLocationRepository() {
        super(StopHasCoordinateLocationEntity.class);
    }
}