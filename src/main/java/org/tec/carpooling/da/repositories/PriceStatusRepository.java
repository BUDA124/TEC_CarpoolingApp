package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.PriceStatusEntity;

public class PriceStatusRepository extends BaseRepository<PriceStatusEntity, Long> {

    public PriceStatusRepository() {
        super(PriceStatusEntity.class);
    }
}