package com.views.components.tables;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.constants.ConfigUI;
import com.constants.FunctionCallbackButtonClicked;
import com.utils.UIFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
    private TableRowSorter<TableModel> rowSorter;

    public TableCRUD() {
        super();
        initComponents();
    }

    public TableCRUD(Table table) {
        super();
        this.table = table;
        initComponents();
        initSearchComponent();
    }

    private void initComponents() {
        // Create components
        searchTextField = UIFactory.createTextField();
        searchLabel = new JLabel("Search: ");
        deleteBtn = UIFactory.createBtn("Delete", Color.RED);
        editBtn = UIFactory.createBtn("Create", Color.GREEN);
        createBtn = UIFactory.createBtn("Edit", Color.YELLOW);
        refreshBtn = UIFactory.createBtn("Refresh", Color.WHITE);

        // Layout container
        BorderLayout containerLayout = new BorderLayout();
        setLayout(containerLayout);

        // Layout top container
        JPanel topPanel = new JPanel();
        layoutTop(topPanel);

        // Layout top left container
        JPanel pLeft = new JPanel();
        layoutTopLeft(pLeft);

        // Layout top right container
        JPanel pRight = new JPanel();
        layoutTopRight(pRight);

        // Add components to top container
        topPanel.add(pLeft);
        topPanel.add(pRight);

        // Add all to Panel CRUD
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setFocusable(false);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setBorder(new EmptyBorder(ConfigUI.Padding.NORMAL, ConfigUI.Padding.NORMAL, ConfigUI.Padding.NORMAL,
                ConfigUI.Padding.NORMAL));
    }

    // Ref:
    // https://stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
    private void initSearchComponent() {
        rowSorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(rowSorter);

        // Listener
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String content = searchTextField.getText().trim();

                if (content.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + content));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String content = searchTextField.getText().trim();

                if (content.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + content));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    public Table getTable() {
        return this.table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    private void layoutTop(JPanel topPanel) {
        // Top container
        GridLayout topLayout = new GridLayout(1, 2);
        topPanel.setLayout(topLayout);

        //// trick remove first, last vgap of layout
        topPanel.setBorder(new EmptyBorder(0, -topLayout.getVgap() - 2, 0, -topLayout.getVgap() - 2));
    }

    private void layoutTopLeft(JPanel panel) {
        FlowLayout l1 = new FlowLayout(FlowLayout.LEADING);
        panel.setLayout(l1);
        panel.add(createBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
    }

    private void layoutTopRight(JPanel panel) {

        GridBagLayout l1 = new GridBagLayout();
        panel.setLayout(l1);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, ConfigUI.Padding.TINY, 0, ConfigUI.Padding.TINY);

        // Just for empty cell
        gbc.gridx = 0;
        gbc.weightx = 1;
        panel.add(new JLabel(), gbc);

        // Text field
        JPanel containerTextField = new JPanel();
        BoxLayout l2 = new BoxLayout(containerTextField, BoxLayout.LINE_AXIS);
        containerTextField.setLayout(l2);
        containerTextField.add(searchLabel);
        containerTextField.add(searchTextField);

        gbc.weightx = 1;
        gbc.gridx = 1;
        panel.add(containerTextField, gbc);

        // Button Refresh
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.gridwidth = 0; // default button width
        panel.add(refreshBtn, gbc);
    }

    public void addHandleButtonDeleteClick(FunctionCallbackButtonClicked fn) {
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fn.run();
            }
        });
    }

    public void handleButtonCreateClick(FunctionCallbackButtonClicked fn) {
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fn.run();
            }
        });
    }

    public void handleButtonEditClick(FunctionCallbackButtonClicked fn) {
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fn.run();
            }
        });
    }

    public void handleButtonRefreshClick(FunctionCallbackButtonClicked fn) {
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fn.run();
            }
        });
    }
}
