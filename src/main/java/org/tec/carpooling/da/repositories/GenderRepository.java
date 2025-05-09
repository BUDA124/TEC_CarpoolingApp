package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.GenderEntity;

public class GenderRepository extends BaseRepository<GenderEntity, Long> {

    public GenderRepository() {
        super(GenderEntity.class);
    }
}