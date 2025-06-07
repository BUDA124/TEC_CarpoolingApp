// En org.tec.carpooling.bl.services o una nueva clase de utilidad
// Asumimos que tienes AppConstants.SYSTEM_USER
package org.tec.carpooling.bl.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.tec.carpooling.bl.services.AuditLogService;
import org.tec.carpooling.da.entities.AuditLogEntity;
import org.tec.carpooling.da.repositories.AuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    /**
     * Creates a new AuditLogEntity.
     * This method will join an existing transaction if one is present, or create a new
     * one otherwise. This ensures the AuditLog and the data it tracks are saved atomically.
     * @param creator The user/system creating the log.
     * @return The new, uncommitted AuditLogEntity, ready to be associated.
     */
    @Override
    @Transactional
    public AuditLogEntity createInitialAuditLog(String creator) {
        AuditLogEntity auditLog = new AuditLogEntity();
        auditLog.setCreationDate(LocalDateTime.now());
        auditLog.setCreatedBy(creator);
        return auditLogRepository.save(auditLog);
    }

    /**
     * Updates an existing AuditLogEntity.
     * This method will also join the caller's transaction.
     * @param auditLog The entity to update.
     * @param updater The user/system performing the update.
     * @return The updated, uncommitted AuditLogEntity.
     */
    @Override
    @Transactional
    public AuditLogEntity updateAuditLog(AuditLogEntity auditLog, String updater) {
        auditLog.setLastUpdateDate(LocalDateTime.now());
        auditLog.setUpdatedBy(updater);
        return auditLogRepository.save(auditLog);
    }
}