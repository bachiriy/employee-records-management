package com.ems.ui;

import net.miginfocom.swing.MigLayout;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainFrame extends JFrame {
    
    public MainFrame() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill"));
        
        // Set minimum size and center on screen
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        
        // Add a temporary label
        add(new JLabel("Employee Management System - Under Construction"), "center");
    }
} 