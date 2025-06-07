package org.tec.carpooling.common.utils;

import org.tec.carpooling.da.entities.AuditLogEntity;

/**
 * Interfaz para entidades que tienen un registro de auditoría asociado.
 */
public interface Auditable {
    void setAuditLog(AuditLogEntity auditLog);
}