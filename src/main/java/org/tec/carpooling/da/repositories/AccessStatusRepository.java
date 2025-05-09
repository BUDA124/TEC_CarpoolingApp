package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.AccessStatusEntity;

public class AccessStatusRepository extends BaseRepository<AccessStatusEntity, Long> {

    public AccessStatusRepository() {
        super(AccessStatusEntity.class);
    }
}