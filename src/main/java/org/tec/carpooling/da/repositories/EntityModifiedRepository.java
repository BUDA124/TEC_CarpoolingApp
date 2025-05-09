package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.EntityModifiedEntity;

public class EntityModifiedRepository extends BaseRepository<EntityModifiedEntity, Long> {

    public EntityModifiedRepository() {
        super(EntityModifiedEntity.class);
    }
}