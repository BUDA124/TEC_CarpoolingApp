package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.AuditLogEntity;

public class AuditLogRepository extends BaseRepository<AuditLogEntity, Long> {

    public AuditLogRepository() {
        super(AuditLogEntity.class);
    }
}