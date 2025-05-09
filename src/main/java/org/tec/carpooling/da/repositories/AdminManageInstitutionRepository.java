package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.AdminManageInstitutionEntity;
import org.tec.carpooling.da.entities.embeddable.AdminManageInstitutionId; // Assuming this PK class exists

public class AdminManageInstitutionRepository extends BaseRepository<AdminManageInstitutionEntity, AdminManageInstitutionId> {

    public AdminManageInstitutionRepository() {
        super(AdminManageInstitutionEntity.class);
    }
}