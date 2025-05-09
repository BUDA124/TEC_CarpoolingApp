package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.InstitutionalEmailEntity;

public class InstitutionalEmailRepository extends BaseRepository<InstitutionalEmailEntity, Long> {

    public InstitutionalEmailRepository() {
        super(InstitutionalEmailEntity.class);
    }
}