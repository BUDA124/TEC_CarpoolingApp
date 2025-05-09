package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.AdministratorEntity;

// Assuming AdministratorEntity's PK (idPerson) is mapped as Long and identifiable via getId()
public class AdministratorRepository extends BaseRepository<AdministratorEntity, Long> {

    public AdministratorRepository() {
        super(AdministratorEntity.class);
    }
}