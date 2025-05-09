package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.DriverEntity;

public class DriverRepository extends BaseRepository<DriverEntity, Long> {

    public DriverRepository() {
        super(DriverEntity.class);
    }
}