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
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private JButton detailBtn;
    private TableRowSorter<TableModel> rowSorter;

    public TableCRUD() {
        super();
        initComponents();
        initSearchComponent();
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
        editBtn = UIFactory.createBtn("Edit", Color.YELLOW);
        createBtn = UIFactory.createBtn("Create", Color.GREEN);
        detailBtn = UIFactory.createBtn("Detail", Color.GREEN);
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
        addTable();
        add(topPanel, BorderLayout.NORTH);

        setBorder(new EmptyBorder(ConfigUI.Padding.NORMAL, ConfigUI.Padding.NORMAL,
                ConfigUI.Padding.NORMAL, ConfigUI.Padding.NORMAL));
    }

    private void addTable() {
        if (table == null)
            return;

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setFocusable(false);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Ref:
    // https://stackoverflow.com/questions/22066387/how-to-search-an-element-in-a-jtable-java
    private void initSearchComponent() {
        if (table == null)
            return;

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
        addTable();
        initSearchComponent();
    }

    private void layoutTop(JPanel topPanel) {
        // Top container
        GridLayout topLayout = new GridLayout(1, 2);
        topPanel.setLayout(topLayout);

        //// trick remove first, last vgap of layout
        topPanel.setBorder(
                new EmptyBorder(0, -topLayout.getVgap() - 2, 0, -topLayout.getVgap() - 2));
    }

    private void layoutTopLeft(JPanel panel) {
        FlowLayout l1 = new FlowLayout(FlowLayout.LEADING);
        panel.setLayout(l1);
        panel.add(createBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(detailBtn);
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

    public void addHandlerButtonDeleteClick(FunctionCallbackButtonClicked fn) {
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Delete show in table
                List<Integer> rowsSelected = getRowsSelected();

                if (rowsSelected.size() == 0)
                    return;

                String message = "You will delete " + rowsSelected.size()
                        + " items. Are you absolutely sure?";
                int option = JOptionPane.showConfirmDialog(getParent(), message, "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    fn.run(getRowsSelected());
                    getTable().getModel().fireTableDataChanged();
                }

            }
        });
    }

    public void addHandlerButtonCreateClick(FunctionCallbackButtonClicked fn) {
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fn.run(getRowsSelected());
                getTable().getModel().fireTableDataChanged();
            }
        });
    }

    public void addHandlerButtonEditClick(FunctionCallbackButtonClicked fn) {
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> rowsSelected = getRowsSelected();

                if (rowsSelected.size() == 0)
                    return;

                if (rowsSelected.size() > 0) {
                    String message = "You only can edit one item";
                    JOptionPane.showMessageDialog(getParent(), message, "Error Action",
                            JOptionPane.CLOSED_OPTION);
                    return;
                }

                fn.run(getRowsSelected());
                getTable().getModel().fireTableDataChanged();
            }
        });
    }

    public void addHandlerButtonRefreshClick(FunctionCallbackButtonClicked fn) {
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fn.run(getRowsSelected());
                table.getModel().fireTableDataChanged();
            }
        });
    }

    public void addHandlerButtonDetailClick(FunctionCallbackButtonClicked fn) {
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fn.run(getRowsSelected());
            }
        });
    }

    private List<Integer> getRowsSelected() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            if ((Boolean) table.getValueAt(i, 0) == true) {
                list.add(i);
            }
        }

        return list;
    }
}
