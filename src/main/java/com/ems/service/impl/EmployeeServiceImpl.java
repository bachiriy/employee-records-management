package com.ems.service.impl;

import com.ems.dto.EmployeeDTO;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
import com.ems.service.AuditService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final AuditService auditService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AuditService auditService) {
        this.employeeRepository = employeeRepository;
        this.auditService = auditService;
    }

    @Override
    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        
        Employee savedEmployee = employeeRepository.save(employee);
        auditService.logAction("CREATE", "Employee", savedEmployee.getEmployeeId(), "Created new employee");
        
        EmployeeDTO savedDTO = new EmployeeDTO();
        BeanUtils.copyProperties(savedEmployee, savedDTO);
        return savedDTO;
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        
        String oldData = employee.toString();
        BeanUtils.copyProperties(employeeDTO, employee, "id", "employeeId");
        
        Employee updatedEmployee = employeeRepository.save(employee);
        auditService.logAction("UPDATE", "Employee", employeeId, 
            String.format("Updated from [%s] to [%s]", oldData, updatedEmployee.toString()));
        
        EmployeeDTO updatedDTO = new EmployeeDTO();
        BeanUtils.copyProperties(updatedEmployee, updatedDTO);
        return updatedDTO;
    }

    @Override
    @Transactional
    public void deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        
        employeeRepository.delete(employee);
        auditService.logAction("DELETE", "Employee", employeeId, "Deleted employee");
    }

    @Override
    public EmployeeDTO getEmployeeById(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
            .map(employee -> {
                EmployeeDTO dto = new EmployeeDTO();
                BeanUtils.copyProperties(employee, dto);
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> searchEmployees(String searchTerm) {
        return employeeRepository.searchEmployees(searchTerm).stream()
            .map(employee -> {
                EmployeeDTO dto = new EmployeeDTO();
                BeanUtils.copyProperties(employee, dto);
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department).stream()
            .map(employee -> {
                EmployeeDTO dto = new EmployeeDTO();
                BeanUtils.copyProperties(employee, dto);
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDateRange(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findByHireDateBetween(startDate, endDate).stream()
            .map(employee -> {
                EmployeeDTO dto = new EmployeeDTO();
                BeanUtils.copyProperties(employee, dto);
                return dto;
            })
            .collect(Collectors.toList());
    }
} 