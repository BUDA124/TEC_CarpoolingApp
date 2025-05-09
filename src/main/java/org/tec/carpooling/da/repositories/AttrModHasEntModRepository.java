package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.AttrModHasEntModEntity;
import org.tec.carpooling.da.entities.embeddable.AttrModHasEntModId; // Assuming this PK class exists

public class AttrModHasEntModRepository extends BaseRepository<AttrModHasEntModEntity, AttrModHasEntModId> {

    public AttrModHasEntModRepository() {
        super(AttrModHasEntModEntity.class);
    }
}