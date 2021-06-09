package com.views.components.tables;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.constants.ConfigUI;
import com.utils.UIFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TableCRUD extends JPanel {
    private Table table;
    private JTextField searchTextField;
    private JLabel searchLabel;
    private JButton deleteBtn;
    private JButton editBtn;
    private JButton createBtn;
    private JButton refreshBtn;

    public TableCRUD() {
        super();
        initComponents();
    }

    public TableCRUD(Table table) {
        super();
        this.table = table;
        initComponents();
    }

    private void initComponents() {
        // Create components
        searchTextField = UIFactory.createTextField();
        searchLabel = new JLabel("Search");
        deleteBtn = UIFactory.createBtn("Delete", Color.RED);
        editBtn = UIFactory.createBtn("Create", Color.GREEN);
        createBtn = UIFactory.createBtn("Edit", Color.YELLOW);
        refreshBtn = UIFactory.createBtn("Refresh", Color.WHITE);

        // Container Layout
        BorderLayout containerLayout = new BorderLayout();
        setLayout(containerLayout);

        // Top container
        JPanel topPanel = new JPanel();
        GridLayout topLayout = new GridLayout(1, 2);
        topPanel.setLayout(topLayout);

        // Top left container
        JPanel pLeft = new JPanel();
        FlowLayout l1 = new FlowLayout(FlowLayout.LEADING);
        pLeft.setLayout(l1);
        pLeft.add(createBtn);
        pLeft.add(editBtn);
        pLeft.add(deleteBtn);

        // Top right container
        JPanel pRight = new JPanel();
        FlowLayout l2 = new FlowLayout(FlowLayout.TRAILING);
        pRight.setLayout(l2);
        pRight.add(searchTextField);
        pRight.add(refreshBtn);

        // Add components to top container
        topPanel.add(pLeft);
        topPanel.add(pRight);

        // Add all to Panel CRUD
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setBorder(new EmptyBorder(ConfigUI.Padding.NORMAL, ConfigUI.Padding.NORMAL, ConfigUI.Padding.NORMAL,
                ConfigUI.Padding.NORMAL));
    }

    public Table getTable() {
        return this.table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
