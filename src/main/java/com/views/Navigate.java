package com.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputFilter.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import javax.swing.tree.DefaultMutableTreeNode;

import com.constants.ConfigUI;
import com.models.Semester;
import com.models.Student;
import com.views.components.tables.AcademicManagerTable;
import com.views.components.tables.ClassTable;
import com.views.components.tables.CourseRegistrationTable;
import com.views.components.tables.CourseTable;
import com.views.components.tables.SemesterRegisterSessionTable;
import com.views.components.tables.SemesterTable;
import com.views.components.tables.StudentTable;
import com.views.components.tables.TableCRUD;

public class Navigate extends JPanel {
    private final List<String> items = Arrays.asList("Student", "Academic Manager", "Subject",
            "Course", "Semester Register Session", "Semester");

    public Navigate() {
        this.initComponent();
    }

    private void initComponent() {
        GridLayout layout = new GridLayout(1, 1);
        setLayout(layout);

        // items.forEach(item -> {
        // Button btn = new Button(item);
        // add(btn);
        // });

        // DefaultTableModel model = new DefaultTableModel() {
        // @Override
        // public Class<?> getColumnClass(int columnIndex) {
        // if (columnIndex == 0)
        // return Boolean.class;
        // return String.class;
        // }
        // };
        // model.addColumn("MaQG");
        // model.addColumn("TenQG");
        // model.addColumn("Button");
        // model.addRow(new Object[] { true, "VN", "VN" });

        // JTable table = new JTable(model) {
        // public boolean isCellEditable(int row, int column) {
        // return false;
        // };
        // };

        // add(new JScrollPane(table));

        // table.setSelectionBackground(Color.DARK_GRAY);
        // table.setGridColor(Color.red);
        // table.setShowGrid(true);
        // table.setRowHeight(50);
        // table.setFont(ConfigUI.DefaultFont.BODY);
        // table.setForeground(ConfigUI.SysColor.PRIMARY);
        // table.setSelectionBackground(selectionBackground);
        // JTableHeader tableHeader = table.getTableHeader();
        // tableHeader.setBackground(Color.GREEN);
        // tableHeader.setPreferredSize(new Dimension(1000, 100));
        // table.getSelectionModel().addListSelectionListener(new
        // ListSelectionListener() {
        // @Override
        // public void valueChanged(ListSelectionEvent e) {
        // System.out.println("dfdfhjhj");
        // System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
        // System.out.println(table.getSelectedColumn());
        // }
        // });

        // AcademicManagerTable table = new AcademicManagerTable();
        // add(table);

        // ClassTable table = new ClassTable();
        // add(table);

        // CourseTable table = new CourseTable();
        // add(table);

        // SemesterRegisterSessionTable table = new SemesterRegisterSessionTable();
        // add(table);

        CourseRegistrationTable table = new CourseRegistrationTable();
        add(table);
    }
}
