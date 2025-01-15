package com.ems.service;

import com.ems.dto.EmployeeDTO;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;
import com.ems.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    
    @Mock
    private AuditService auditService;
    
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository, auditService);
    }

    @Test
    void createEmployee_Success() {
        // Arrange
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId("EMP001");
        dto.setFullName("John Doe");
        
        when(employeeRepository.save(any(Employee.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        EmployeeDTO result = employeeService.createEmployee(dto);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
        verify(auditService).logAction(eq("CREATE"), eq("Employee"), eq("EMP001"), anyString());
    }

    @Test
    void getEmployeeById_NotFound() {
        // Arrange
        String employeeId = "EMP001";
        when(employeeRepository.findByEmployeeId(employeeId))
            .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(javax.persistence.EntityNotFoundException.class,
            () -> employeeService.getEmployeeById(employeeId));
    }
} 