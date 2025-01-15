package com.ems.service.impl;

import com.ems.model.AuditLog;
import com.ems.repository.AuditLogRepository;
import com.ems.service.AuditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    @Transactional
    public void logAction(String action, String entityType, String entityId, String changes) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setEntityType(entityType);
        auditLog.setEntityId(entityId);
        auditLog.setChanges(changes);
        auditLog.setModifiedAt(LocalDateTime.now());
        auditLog.setModifiedBy("SYSTEM"); // This will be replaced with actual user when security is implemented
        
        auditLogRepository.save(auditLog);
    }

    @Override
    public List<AuditLog> getAuditLogsByEntity(String entityType, String entityId) {
        return auditLogRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }

    @Override
    public List<AuditLog> getAuditLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return auditLogRepository.findByModifiedAtBetween(startDate, endDate);
    }

    @Override
    public List<AuditLog> getAuditLogsByUser(String username) {
        return auditLogRepository.findByModifiedBy(username);
    }
} 