package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.ParameterEntity;

public class ParameterRepository extends BaseRepository<ParameterEntity, Long> {

    public ParameterRepository() {
        super(ParameterEntity.class);
    }
}