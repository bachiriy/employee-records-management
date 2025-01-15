package com.ems.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.ems.repository")
@EnableTransactionManagement
public class DatabaseConfig {
    // Database configuration will be handled through application.properties
} 