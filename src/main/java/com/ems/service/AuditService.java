package com.ems.service;

import java.time.LocalDateTime;
import java.util.List;
import com.ems.model.AuditLog;

public interface AuditService {
    void logAction(String action, String entityType, String entityId, String changes);
    List<AuditLog> getAuditLogsByEntity(String entityType, String entityId);
    List<AuditLog> getAuditLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<AuditLog> getAuditLogsByUser(String username);
} 