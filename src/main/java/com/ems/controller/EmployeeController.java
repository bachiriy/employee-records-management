package com.ems.controller;

import com.ems.dto.ApiResponse;
import com.ems.dto.EmployeeDTO;
import com.ems.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Api(tags = "Employee Management", description = "Operations pertaining to employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping
    @ApiOperation(value = "Create a new employee", response = ApiResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created employee"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(
            @ApiParam(value = "Employee object", required = true) 
            @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO created = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(ApiResponse.success("Employee created successfully", created));
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN') or @userService.canAccessEmployee(authentication.name, #employeeId)")
    @PutMapping("/{employeeId}")
    @ApiOperation(value = "Update an existing employee", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(
            @ApiParam(value = "Employee ID", required = true) 
            @PathVariable String employeeId,
            @ApiParam(value = "Updated employee object", required = true) 
            @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updated = employeeService.updateEmployee(employeeId, employeeDTO);
        return ResponseEntity.ok(ApiResponse.success("Employee updated successfully", updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{employeeId}")
    @ApiOperation(value = "Delete an employee", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @ApiParam(value = "Employee ID", required = true) 
            @PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(ApiResponse.success("Employee deleted successfully", null));
    }

    @GetMapping("/{employeeId}")
    @ApiOperation(value = "Get an employee by ID", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployee(
            @ApiParam(value = "Employee ID", required = true) 
            @PathVariable String employeeId) {
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(ApiResponse.success(employee));
    }

    @GetMapping
    @ApiOperation(value = "Get all employees", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(ApiResponse.success(employees));
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search employees", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> searchEmployees(
            @ApiParam(value = "Search term") 
            @RequestParam String term) {
        List<EmployeeDTO> employees = employeeService.searchEmployees(term);
        return ResponseEntity.ok(ApiResponse.success(employees));
    }

    @GetMapping("/department/{department}")
    @ApiOperation(value = "Get employees by department", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesByDepartment(
            @ApiParam(value = "Department name", required = true) 
            @PathVariable String department) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(department);
        return ResponseEntity.ok(ApiResponse.success(employees));
    }

    @GetMapping("/hire-date-range")
    @ApiOperation(value = "Get employees by hire date range", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesByHireDateRange(
            @ApiParam(value = "Start date (yyyy-MM-dd)", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @ApiParam(value = "End date (yyyy-MM-dd)", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDateRange(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(employees));
    }
} 