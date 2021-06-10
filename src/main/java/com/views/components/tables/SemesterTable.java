package com.views.components.tables;

import java.util.Date;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.constants.FunctionCallbackButtonClicked;
import com.models.Semester;
import com.services.SemesterServices;

public class SemesterTable extends TableCRUD {
    private SemesterServices services;
    private DefaultTableModel model;
    private List<Semester> semesters;
    private final String[] columnNames = {"#", "SemesterNo", "Year", "Start Date", "End Date"};

    public SemesterTable() {
        services = new SemesterServices();

        initComponents();
        loadData();
        handleEventComponent();
    }

    private void initComponents() {
        services = new SemesterServices();

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
        semesters = services.getSemesters(1, 10);
        semesters.forEach(smt -> {
            model.addRow(new Object[] {false, smt.getSemNo(), smt.getYear(), smt.getStartDate(),
                    smt.getEndDate()});
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
                Semester selectedAm = semesters.get(idxInModel);
                services.deleteSemester(selectedAm);

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
