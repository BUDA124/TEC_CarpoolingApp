package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.VehicleEntity;

public class VehicleRepository extends BaseRepository<VehicleEntity, Long> {

    public VehicleRepository() {
        super(VehicleEntity.class);
    }
}