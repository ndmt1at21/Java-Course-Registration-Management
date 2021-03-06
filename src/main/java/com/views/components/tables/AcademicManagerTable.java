package com.views.components.tables;

import java.util.Date;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.constants.FunctionCallbackButtonClicked;
import com.models.AcademicManager;
import com.services.AcademicManagerServices;

public class AcademicManagerTable extends TableCRUD {
    private AcademicManagerServices services;
    private DefaultTableModel model;
    private List<AcademicManager> academicManagers;
    private final String[] columnNames = { "#", "EmployeeID", "Username", "Last Name", "First Name", "Birth",
            "Gender" };

    public AcademicManagerTable() {
        services = new AcademicManagerServices();

        initComponents();
        loadData();
        handleEventComponent();
    }

    private void initComponents() {
        services = new AcademicManagerServices();

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
        table.setRowSelectionAllowed(true);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    private void loadData() {
        academicManagers = services.getAcademicManagers(1, 10);
        academicManagers.forEach(am -> {
            model.addRow(new Object[] { false, am.getAcademicManagerID(), am.getUsername(), am.getLastName(),
                    am.getFirstName(), am.getBirth(), am.getSex() });
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
                AcademicManager selectedAm = academicManagers
                        .get(getTable().convertRowIndexToModel(rowsSelected.get(i)));
                services.deleteAcademicManager(selectedAm);
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
