package com.ems.ui;

import com.ems.model.AuditLog;
import com.ems.service.AuditService;
import net.miginfocom.swing.MigLayout;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AuditPanel extends JPanel {
    private final AuditService auditService;
    private final JTable auditTable;
    private final DefaultTableModel tableModel;
    private final JTextField entitySearchField;
    private final JButton searchButton;
    private final JButton refreshButton;

    public AuditPanel(AuditService auditService) {
        this.auditService = auditService;
        this.tableModel = new DefaultTableModel(
            new String[]{"Date", "Action", "Entity Type", "Entity ID", "Modified By", "Changes"}, 
            0
        );
        this.auditTable = new JTable(tableModel);
        this.entitySearchField = new JTextField(20);
        this.searchButton = new JButton("Search");
        this.refreshButton = new JButton("Refresh");
        
        initComponents();
        loadAuditLogs();
    }

    private void initComponents() {
        setLayout(new MigLayout("fill"));
        
        // Search panel
        JPanel searchPanel = new JPanel(new MigLayout());
        searchPanel.add(new JLabel("Entity ID:"));
        searchPanel.add(entitySearchField, "growx");
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        
        // Table scroll pane
        JScrollPane scrollPane = new JScrollPane(auditTable);
        auditTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // Add components
        add(searchPanel, "wrap, growx");
        add(scrollPane, "grow");
        
        // Add listeners
        searchButton.addActionListener(e -> performSearch());
        refreshButton.addActionListener(e -> loadAuditLogs());
    }

    private void loadAuditLogs() {
        tableModel.setRowCount(0);
        List<AuditLog> logs = auditService.getAuditLogsByDateRange(
            LocalDateTime.now().minusDays(7),
            LocalDateTime.now()
        );
        displayLogs(logs);
    }

    private void performSearch() {
        String entityId = entitySearchField.getText();
        if (!entityId.isEmpty()) {
            List<AuditLog> logs = auditService.getAuditLogsByEntity("Employee", entityId);
            displayLogs(logs);
        }
    }

    private void displayLogs(List<AuditLog> logs) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        tableModel.setRowCount(0);
        for (AuditLog log : logs) {
            tableModel.addRow(new Object[]{
                log.getModifiedAt().format(formatter),
                log.getAction(),
                log.getEntityType(),
                log.getEntityId(),
                log.getModifiedBy(),
                log.getChanges()
            });
        }
    }
} 