package com.views.components.tables;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.constants.FunctionCallbackButtonClicked;
import com.models.SemesterRegisterSession;
import com.services.SemesterRegisterSessionServices;

public class SemesterRegisterSessionTable extends TableCRUD {
    private SemesterRegisterSessionServices services;
    private DefaultTableModel model;
    private List<SemesterRegisterSession> sessions;
    private final String[] columnNames = {"#", "SemNo", "Year", "Start Date", "End Date"};

    public SemesterRegisterSessionTable() {
        services = new SemesterRegisterSessionServices();

        initComponents();
        loadData();
        handleEventComponent();
    }

    private void initComponents() {
        services = new SemesterRegisterSessionServices();

        // Init model
        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0)
                    return Boolean.class;

                if (column == 3 || column == 4)
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
        sessions = services.getSession(1, 10);

        sessions.forEach(ss -> {
            model.addRow(new Object[] {false, ss.getSemester().getSemNo(),
                    ss.getSemester().getYear(), ss.getStartTime(), ss.getEndTime()});
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
                SemesterRegisterSession selectedSrs = sessions.get(idxInModel);
                services.deleteSession(selectedSrs);

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
