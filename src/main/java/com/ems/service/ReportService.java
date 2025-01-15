package com.ems.service;

import com.ems.dto.EmployeeDTO;
import net.sf.jasperreports.engine.JasperPrint;
import java.util.List;

public interface ReportService {
    JasperPrint generateEmployeeReport(List<EmployeeDTO> employees);
    JasperPrint generateDepartmentReport(String department);
    JasperPrint generateAuditReport(String entityType, String entityId);
} 