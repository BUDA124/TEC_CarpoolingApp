package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.LogBookHasEntityModifiedEntity;
import org.tec.carpooling.da.entities.embeddable.LogBookHasEntityModifiedId; // Assuming this PK class exists

public class LogBookHasEntityModifiedRepository extends BaseRepository<LogBookHasEntityModifiedEntity, LogBookHasEntityModifiedId> {

    public LogBookHasEntityModifiedRepository() {
        super(LogBookHasEntityModifiedEntity.class);
    }
}