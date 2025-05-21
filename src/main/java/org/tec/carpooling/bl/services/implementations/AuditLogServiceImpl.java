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
     * Creates a new AuditLogEntity for system-initiated actions or initial data.
     * This method should run in its own transaction or propagate an existing one
     * to ensure the AuditLogEntity is persisted before being associated with other entities.
     * @param creator The user/system creating the log.
     * @return The persisted AuditLogEntity.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Ensures it gets its own transaction if needed
    public AuditLogEntity createInitialAuditLog(String creator) {
        AuditLogEntity auditLog = new AuditLogEntity();
        auditLog.setCreationDate(LocalDateTime.now());
        auditLog.setCreatedBy(creator);
        return auditLogRepository.save(auditLog);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuditLogEntity updateAuditLog(AuditLogEntity auditLog, String updater) {
        auditLog.setLastUpdateDate(LocalDateTime.now());
        auditLog.setUpdatedBy(updater);
        return auditLogRepository.save(auditLog);
    }
}