package com.views.components.tables;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.views.components.tables.Table;

public class AcademicManagerTable extends Table {

    public AcademicManagerTable() {
        super();
        initComponents();
    }

    private void initComponents() {
        // Init model
        Object[] columnNames = { true, "EmployeeID", "Username", "First Name", "Last Name", "Birth", "Gender" };
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0)
                    return Boolean.class;

                if (column == 5)
                    return Date.class;

                if (column == 6)
                    return JButton.class;
                return String.class;
            }
        };

        model.setColumnIdentifiers(columnNames);
        setModel(model);

        addRowSelectionListener((int[] a) -> {
            System.out.println("dfhdjhh");
        });

        model.addRow(new Object[] { false, "2", "3", "4", "5", new Date(), new JButton("dfjhdjh") });
        model.addRow(new Object[] { false, "2", "3", "4", "6", new Date(), new JButton("dfjhdjh") });
        model.addRow(new Object[] { false, "2", "3", "4", "6", new Date(), new JButton("dfjhdjh") });
        model.addRow(new Object[] { false, "2", "3", "4", "6", new Date(), new JButton("dfjhdjh") });
    }
}
