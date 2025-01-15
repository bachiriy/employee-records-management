package com.ems.ui.dialog;

import com.ems.dto.EmployeeDTO;
import com.ems.model.EmploymentStatus;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EmployeeDialog extends JDialog {
    private final JTextField employeeIdField;
    private final JTextField fullNameField;
    private final JTextField jobTitleField;
    private final JTextField departmentField;
    private final JTextField hireDateField;
    private final JComboBox<EmploymentStatus> statusComboBox;
    private final JTextField emailField;
    private final JTextField phoneField;
    private final JTextArea addressArea;
    
    private boolean approved = false;
    private final EmployeeDTO employeeDTO;
    private final boolean isEdit;

    public EmployeeDialog(JFrame parent, EmployeeDTO employeeDTO, boolean isEdit) {
        super(parent, isEdit ? "Edit Employee" : "Add Employee", true);
        this.employeeDTO = employeeDTO;
        this.isEdit = isEdit;

        // Initialize components
        employeeIdField = new JTextField(20);
        fullNameField = new JTextField(20);
        jobTitleField = new JTextField(20);
        departmentField = new JTextField(20);
        hireDateField = new JTextField(20);
        statusComboBox = new JComboBox<>(EmploymentStatus.values());
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        addressArea = new JTextArea(3, 20);

        if (isEdit) {
            populateFields();
        }

        initComponents();
    }

    private void initComponents() {
        setLayout(new MigLayout("fillx"));

        // Create form panel
        JPanel formPanel = new JPanel(new MigLayout("fillx"));
        formPanel.add(new JLabel("Employee ID:"));
        formPanel.add(employeeIdField, "growx, wrap");
        employeeIdField.setEnabled(!isEdit);

        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(fullNameField, "growx, wrap");

        formPanel.add(new JLabel("Job Title:"));
        formPanel.add(jobTitleField, "growx, wrap");

        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentField, "growx, wrap");

        formPanel.add(new JLabel("Hire Date (yyyy-MM-dd):"));
        formPanel.add(hireDateField, "growx, wrap");

        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusComboBox, "growx, wrap");

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField, "growx, wrap");

        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField, "growx, wrap");

        formPanel.add(new JLabel("Address:"));
        formPanel.add(new JScrollPane(addressArea), "growx, wrap");

        // Create button panel
        JPanel buttonPanel = new JPanel(new MigLayout("fillx"));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            if (validateInput()) {
                updateEmployeeDTO();
                approved = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(okButton, "split 2, right");
        buttonPanel.add(cancelButton);

        // Add panels to dialog
        add(formPanel, "wrap");
        add(buttonPanel, "growx");

        pack();
        setLocationRelativeTo(getOwner());
    }

    private void populateFields() {
        employeeIdField.setText(employeeDTO.getEmployeeId());
        fullNameField.setText(employeeDTO.getFullName());
        jobTitleField.setText(employeeDTO.getJobTitle());
        departmentField.setText(employeeDTO.getDepartment());
        hireDateField.setText(employeeDTO.getHireDate().toString());
        statusComboBox.setSelectedItem(employeeDTO.getEmploymentStatus());
        emailField.setText(employeeDTO.getEmail());
        phoneField.setText(employeeDTO.getPhone());
        addressArea.setText(employeeDTO.getAddress());
    }

    private boolean validateInput() {
        if (fullNameField.getText().trim().isEmpty()) {
            showError("Full name is required");
            return false;
        }

        if (emailField.getText().trim().isEmpty() || !emailField.getText().contains("@")) {
            showError("Valid email is required");
            return false;
        }

        try {
            LocalDate.parse(hireDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            showError("Invalid date format. Use yyyy-MM-dd");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    private void updateEmployeeDTO() {
        if (!isEdit) {
            employeeDTO.setEmployeeId(employeeIdField.getText());
        }
        employeeDTO.setFullName(fullNameField.getText());
        employeeDTO.setJobTitle(jobTitleField.getText());
        employeeDTO.setDepartment(departmentField.getText());
        employeeDTO.setHireDate(LocalDate.parse(hireDateField.getText()));
        employeeDTO.setEmploymentStatus((EmploymentStatus) statusComboBox.getSelectedItem());
        employeeDTO.setEmail(emailField.getText());
        employeeDTO.setPhone(phoneField.getText());
        employeeDTO.setAddress(addressArea.getText());
    }

    public boolean isApproved() {
        return approved;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }
} 