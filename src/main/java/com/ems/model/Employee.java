package com.ems.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id", unique = true, nullable = false)
    private String employeeId;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(name = "job_title", nullable = false)
    private String jobTitle;
    
    @Column(nullable = false)
    private String department;
    
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;
    
    @Column(name = "employment_status", nullable = false)
    private String employmentStatus;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "address")
    private String address;

    // Getters and Setters
    // ... (I'll provide these if you want, but skipping for brevity)
} 