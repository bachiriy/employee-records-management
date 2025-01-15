package com.ems.ui;

import com.ems.dto.EmployeeDTO;
import com.ems.service.EmployeeService;
import com.ems.ui.dialog.EmployeeDialog;
import net.miginfocom.swing.MigLayout;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@Component
public class EmployeePanel extends JPanel {
    private final EmployeeService employeeService;
    private final JTable employeeTable;
    private final DefaultTableModel tableModel;
    private final JButton addButton;
    private final JButton editButton;
    private final JButton deleteButton;
    private final JTextField searchField;

    public EmployeePanel(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Department", "Job Title", "Status"}, 0);
        this.employeeTable = new JTable(tableModel);
        this.addButton = new JButton("Add Employee");
        this.editButton = new JButton("Edit Employee");
        this.deleteButton = new JButton("Delete Employee");
        this.searchField = new JTextField(20);
        
        initComponents();
        loadEmployees();
    }

    private void initComponents() {
        setLayout(new MigLayout("fill"));
        
        // Search panel
        JPanel searchPanel = new JPanel(new MigLayout());
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField, "growx");
        searchField.addActionListener(e -> performSearch());
        
        // Button panel
        JPanel buttonPanel = new JPanel(new MigLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        // Table scroll pane
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        
        // Add components
        add(searchPanel, "wrap, growx");
        add(scrollPane, "wrap, grow");
        add(buttonPanel, "wrap");
        
        // Add listeners
        addButton.addActionListener(e -> showAddDialog());
        editButton.addActionListener(e -> showEditDialog());
        deleteButton.addActionListener(e -> deleteSelectedEmployee());
    }

    private void loadEmployees() {
        tableModel.setRowCount(0);
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        for (EmployeeDTO employee : employees) {
            tableModel.addRow(new Object[]{
                employee.getEmployeeId(),
                employee.getFullName(),
                employee.getDepartment(),
                employee.getJobTitle(),
                employee.getEmploymentStatus()
            });
        }
    }

    private void performSearch() {
        String searchTerm = searchField.getText();
        tableModel.setRowCount(0);
        List<EmployeeDTO> employees = employeeService.searchEmployees(searchTerm);
        for (EmployeeDTO employee : employees) {
            tableModel.addRow(new Object[]{
                employee.getEmployeeId(),
                employee.getFullName(),
                employee.getDepartment(),
                employee.getJobTitle(),
                employee.getEmploymentStatus()
            });
        }
    }

    // Dialog methods to be implemented
    private void showAddDialog() {
        EmployeeDTO newEmployee = new EmployeeDTO();
        EmployeeDialog dialog = new EmployeeDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this),
            newEmployee,
            false
        );
        dialog.setVisible(true);
        
        if (dialog.isApproved()) {
            employeeService.createEmployee(dialog.getEmployeeDTO());
            loadEmployees();
        }
    }

    private void showEditDialog() {
        int row = employeeTable.getSelectedRow();
        if (row != -1) {
            String employeeId = (String) tableModel.getValueAt(row, 0);
            EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
            
            EmployeeDialog dialog = new EmployeeDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                employee,
                true
            );
            dialog.setVisible(true);
            
            if (dialog.isApproved()) {
                employeeService.updateEmployee(employeeId, dialog.getEmployeeDTO());
                loadEmployees();
            }
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Please select an employee to edit",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void deleteSelectedEmployee() {
        int row = employeeTable.getSelectedRow();
        if (row != -1) {
            String employeeId = (String) tableModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this employee?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                employeeService.deleteEmployee(employeeId);
                loadEmployees();
            }
        }
    }
} 