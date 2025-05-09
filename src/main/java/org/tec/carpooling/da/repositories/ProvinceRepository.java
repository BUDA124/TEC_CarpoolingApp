package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.ProvinceEntity;

public class ProvinceRepository extends BaseRepository<ProvinceEntity, Long> {

    public ProvinceRepository() {
        super(ProvinceEntity.class);
    }
}