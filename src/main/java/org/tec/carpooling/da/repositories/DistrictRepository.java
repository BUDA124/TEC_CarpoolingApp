package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.DistrictEntity;

public class DistrictRepository extends BaseRepository<DistrictEntity, Long> {

    public DistrictRepository() {
        super(DistrictEntity.class);
    }
}