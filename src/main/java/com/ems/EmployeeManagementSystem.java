package com.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.ems.ui.MainFrame;

@SpringBootApplication
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(EmployeeManagementSystem.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
        
        // Launch Swing UI
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame mainFrame = context.getBean(MainFrame.class);
            mainFrame.setVisible(true);
        });
    }
} 