package com.ems.ui;

import net.miginfocom.swing.MigLayout;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;

@Component
public class MainFrame extends JFrame {
    private final JTabbedPane tabbedPane;
    private final EmployeePanel employeePanel;
    private final AuditPanel auditPanel;

    public MainFrame(EmployeePanel employeePanel, AuditPanel auditPanel) {
        this.employeePanel = employeePanel;
        this.auditPanel = auditPanel;
        this.tabbedPane = new JTabbedPane();
        initComponents();
    }

    private void initComponents() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill"));
        
        tabbedPane.addTab("Employees", employeePanel);
        tabbedPane.addTab("Audit Logs", auditPanel);
        
        add(tabbedPane, "grow");
        
        setMinimumSize(new Dimension(1024, 768));
        setLocationRelativeTo(null);
    }
} 