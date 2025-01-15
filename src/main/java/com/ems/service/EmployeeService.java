package com.ems.service;

import com.ems.dto.EmployeeDTO;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO);
    void deleteEmployee(String employeeId);
    EmployeeDTO getEmployeeById(String employeeId);
    List<EmployeeDTO> getAllEmployees();
    List<EmployeeDTO> searchEmployees(String searchTerm);
    List<EmployeeDTO> getEmployeesByDepartment(String department);
    List<EmployeeDTO> getEmployeesByDateRange(LocalDate startDate, LocalDate endDate);
} 