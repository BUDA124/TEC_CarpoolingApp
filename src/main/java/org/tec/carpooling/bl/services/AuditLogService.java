package org.tec.carpooling.bl.services;

import org.tec.carpooling.da.entities.AuditLogEntity;

public interface AuditLogService {
    AuditLogEntity createInitialAuditLog(String creator);
    AuditLogEntity updateAuditLog(AuditLogEntity auditLog, String updater);
}
