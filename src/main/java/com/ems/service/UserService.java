package com.ems.service;

import com.ems.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(User user);
    void deleteUser(String username);
    User updateUser(String username, User user);
    boolean canAccessEmployee(String username, String employeeId);
} 