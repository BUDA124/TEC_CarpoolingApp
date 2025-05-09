package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.CantonEntity;

public class CantonRepository extends BaseRepository<CantonEntity, Long> {

    public CantonRepository() {
        super(CantonEntity.class);
    }
}