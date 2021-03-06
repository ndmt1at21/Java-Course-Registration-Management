package com.views.components.tables;

import java.util.Date;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.constants.FunctionCallbackButtonClicked;
import com.models.Student;
import com.services.StudentServices;

public class StudentTable extends TableCRUD {
    private StudentServices services;
    private DefaultTableModel model;
    private List<Student> students;
    private final String[] columnNames = { "#", "StudentID", "Username", "First Name", "Last Name", "Birth", "Gender",
            "Class", "Start Year" };

    public StudentTable() {
        services = new StudentServices();

        initComponents();
        loadData();
        handleEventComponent();
    }

    private void initComponents() {
        services = new StudentServices();

        // Init model
        model = new DefaultTableModel() {
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

        // Init table
        Table table = new Table();
        table.setModel(model);

        setTable(table);
    }

    private void loadData() {
        List<Student> students = services.getStudents(1, 10);
        students.forEach(stu -> {
            model.addRow(
                    new Object[] { false, stu.getStudentID(), stu.getUsername(), stu.getLastName(), stu.getFirstName(),
                            stu.getBirth(), stu.getSex(), stu.getStudentClass().getClassName(), stu.getStartYear() });
        });
    }

    private void handleEventComponent() {
        addHandlerButtonDeleteClick(handlerDeleteButtonClick());
        addHandlerButtonEditClick(handlerEditButtonClick());
        addHandlerButtonCreateClick(handlerCreateButtonClick());
    }

    private FunctionCallbackButtonClicked handlerDeleteButtonClick() {
        return (List<Integer> rowsSelected) -> {
            for (int i = 0; i < rowsSelected.size(); i++) {
                Student selectedStu = students.get(getTable().convertRowIndexToModel(rowsSelected.get(i)));
                services.deleteStudent(selectedStu);
            }
        };
    }

    private FunctionCallbackButtonClicked handlerEditButtonClick() {
        return (List<Integer> rowsSelected) -> {

        };
    }

    private FunctionCallbackButtonClicked handlerCreateButtonClick() {
        return (List<Integer> rowsSelected) -> {

        };
    }
}
