package com.ems.controller;

import com.ems.dto.ApiResponse;
import com.ems.model.AuditLog;
import com.ems.service.AuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
@Api(tags = "Audit Management", description = "Operations pertaining to audit logs")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/entity/{entityType}/{entityId}")
    @ApiOperation(value = "Get audit logs for a specific entity")
    public ResponseEntity<ApiResponse<List<AuditLog>>> getAuditLogsByEntity(
            @ApiParam(value = "Entity type", required = true) @PathVariable String entityType,
            @ApiParam(value = "Entity ID", required = true) @PathVariable String entityId) {
        List<AuditLog> logs = auditService.getAuditLogsByEntity(entityType, entityId);
        return ResponseEntity.ok(ApiResponse.success(logs));
    }

    @GetMapping("/date-range")
    @ApiOperation(value = "Get audit logs within a date range")
    public ResponseEntity<ApiResponse<List<AuditLog>>> getAuditLogsByDateRange(
            @ApiParam(value = "Start date (yyyy-MM-dd'T'HH:mm:ss)", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @ApiParam(value = "End date (yyyy-MM-dd'T'HH:mm:ss)", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<AuditLog> logs = auditService.getAuditLogsByDateRange(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(logs));
    }

    @GetMapping("/user/{username}")
    @ApiOperation(value = "Get audit logs by user")
    public ResponseEntity<ApiResponse<List<AuditLog>>> getAuditLogsByUser(
            @ApiParam(value = "Username", required = true) @PathVariable String username) {
        List<AuditLog> logs = auditService.getAuditLogsByUser(username);
        return ResponseEntity.ok(ApiResponse.success(logs));
    }
} 