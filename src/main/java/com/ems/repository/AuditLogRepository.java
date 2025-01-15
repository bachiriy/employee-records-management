package com.ems.repository;

import com.ems.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityTypeAndEntityId(String entityType, String entityId);
    List<AuditLog> findByModifiedAtBetween(LocalDateTime start, LocalDateTime end);
    List<AuditLog> findByModifiedBy(String username);
} 