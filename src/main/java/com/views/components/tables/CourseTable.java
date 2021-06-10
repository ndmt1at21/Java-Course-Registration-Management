package com.views.components.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.constants.FunctionCallbackButtonClicked;
import com.models.Course;
import com.services.CourseServices;

public class CourseTable extends TableCRUD {
    private CourseServices services;
    private DefaultTableModel model;
    private List<Course> courses;
    private final String[] columnNames = {"#", "Subject Code", "Subject Name", "Teacher Name",
            "Department", "Day Of Week", "Shift Time", "Number of Slots"};

    public CourseTable() {
        services = new CourseServices();

        initComponents();
        loadData();
        handleEventComponent();
    }

    private void initComponents() {
        services = new CourseServices();

        // Init model
        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0)
                    return Boolean.class;

                if (column == 5)
                    return DayOfWeek.class;

                if (column == 7)
                    return int.class;

                return String.class;
            }
        };
        model.setColumnIdentifiers(columnNames);

        // Init table
        Table table = new Table();
        table.setModel(model);

        setTable(table);
        table.setRowSelectionAllowed(true);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    private void loadData() {
        courses = services.getCourses(1, 10);
        courses.forEach(course -> {
            model.addRow(new Object[] {false, course.getSubject().getSubjectCode(),
                    course.getSubject().getSubjectName(), course.getTeacherName(),
                    course.getDepartmentName(), course.getDayOfWeek(),
                    course.getShiftTime().toString(), course.getNumberOfSlot()});
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
                int idxInModel = getTable().convertRowIndexToModel(rowsSelected.get(i));

                // Delete in db
                Course selectedAm = courses.get(idxInModel);
                services.deleteCourse(selectedAm);

                // Delete in table
                getTable().getModel().removeRow(idxInModel);
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
