package com.views.components.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.constants.FunctionCallbackButtonClicked;
import com.models.Course;
import com.models.CourseRegistration;
import com.models.Student;
import com.services.CourseRegistrationServices;

public class CourseRegistrationTable extends TableCRUD {
    private CourseRegistrationServices services;
    private DefaultTableModel model;
    private List<CourseRegistration> courseRegistrations;
    private final String[] columnNames = {"#", "Student Code", "Name", "Subject Code",
            "Teacher Name", "Timetable", "Week Start", "Time Register"};

    public CourseRegistrationTable() {
        services = new CourseRegistrationServices();

        initComponents();
        loadData();
        handleEventComponent();
    }

    private void initComponents() {
        services = new CourseRegistrationServices();

        // Init model
        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0)
                    return Boolean.class;

                if (column == 6 || column == 7)
                    return Date.class;

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
        courseRegistrations = services.getCourseRegistrations(1, 10);

        courseRegistrations.forEach(crs -> {
            Student student = crs.getStudent();
            Course course = crs.getCourse();
            String timetable = "(" + course.getShiftTime().toString() + ")."
                    + course.getDayOfWeek().toString();

            model.addRow(new Object[] {false, student.getStudentID(),
                    student.getLastName() + " " + student.getFirstName(),
                    course.getSubject().getSubjectCode(), course.getTeacherName(), timetable,
                    course.getSemester().getStartDate(), crs.getRegisteredAt()});
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
                CourseRegistration selectedAm = courseRegistrations.get(idxInModel);
                services.deleteCourseRegistration(selectedAm);

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
