package com.views.components.tables;

import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentTable extends JTable {
    public StudentTable() {
        super();
        initComponent();
    }

    private void initComponent() {
        // Init model
        String[] columnNames = { "#", "StudentID", "Username", "First Name", "Last Name", "Birth", "Gender", "Class",
                "Start Year" };

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0)
                    return Boolean.class;

                if (column == 5)
                    return Date.class;

                return String.class;
            }
        };

        model.setColumnIdentifiers(columnNames);
        setModel(model);

        model.addRow(new Object[] { false, "2", "3", "4", "5", new Date(), "7" });
        model.addRow(new Object[] { false, "2", "3", "4", "6", new Date(), "7" });
        model.addRow(new Object[] { false, "2", "3", "4", "6", new Date(), "7" });
        model.addRow(new Object[] { false, "2", "3", "4", "6", new Date(), "7" });
    }
}
