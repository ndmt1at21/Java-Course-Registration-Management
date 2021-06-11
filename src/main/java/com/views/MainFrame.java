package com.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.constants.ConfigUI;

public class MainFrame extends JFrame {
    private final String[] navItems = new String[] {"Dashboard", "Academic Manager", "Student",
            "Course", "Subject", "Semester"};

    public MainFrame() {
        super();
        initComponents();
    }

    private void initComponents() {
        // Init frame
        setSize(1152, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // GridBagConstraint
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Container
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        add(container);

        Navigate nav = new Navigate();

        JPanel pSign = new JPanel();
        pSign.setBackground(ConfigUI.SysColor.GRAY_LIGHT);

        JPanel pTitle = new JPanel();
        JPanel pMain = new JPanel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.15;
        gbc.weighty = 1;
        gbc.gridheight = 3;
        container.add(nav, gbc);

        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.weightx = 0.85;
        gbc.weighty = 0;

        gbc.gridy = 0;
        gbc.weighty = 0.1;
        container.add(pSign, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.05;
        container.add(pTitle, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.8;
        container.add(pMain, gbc);
    }
}
