package com.ems.service.impl;

import com.ems.dto.EmployeeDTO;
import com.ems.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public JasperPrint generateEmployeeReport(List<EmployeeDTO> employees) {
        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "EMS System");
            
            return JasperFillManager.fillReport(
                getClass().getResourceAsStream("/reports/employees.jasper"),
                parameters,
                dataSource
            );
        } catch (JRException e) {
            throw new RuntimeException("Error generating employee report", e);
        }
    }

    @Override
    public JasperPrint generateDepartmentReport(String department) {
        // Implementation similar to employee report but filtered by department
        return null;
    }

    @Override
    public JasperPrint generateAuditReport(String entityType, String entityId) {
        // Implementation for audit report
        return null;
    }
} 