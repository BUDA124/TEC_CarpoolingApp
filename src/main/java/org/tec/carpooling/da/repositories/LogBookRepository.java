package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.LogBookEntity;

public class LogBookRepository extends BaseRepository<LogBookEntity, Long> {

    public LogBookRepository() {
        super(LogBookEntity.class);
    }
}