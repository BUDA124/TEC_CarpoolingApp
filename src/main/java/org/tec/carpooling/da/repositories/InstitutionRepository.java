package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.InstitutionEntity;

public class InstitutionRepository extends BaseRepository<InstitutionEntity, Long> {

    public InstitutionRepository() {
        super(InstitutionEntity.class);
    }
}