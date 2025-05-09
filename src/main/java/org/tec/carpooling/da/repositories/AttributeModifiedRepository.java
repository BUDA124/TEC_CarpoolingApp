package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.AttributeModifiedEntity;

public class AttributeModifiedRepository extends BaseRepository<AttributeModifiedEntity, Long> {

    public AttributeModifiedRepository() {
        super(AttributeModifiedEntity.class);
    }
}