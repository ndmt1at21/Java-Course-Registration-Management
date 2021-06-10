package com.views.components.tables;

import java.util.Date;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.constants.FunctionCallbackButtonClicked;
import com.constants.Sex;
import com.models.Class;
import com.services.ClassServices;

public class ClassTable extends TableCRUD {
    private ClassServices services;
    private DefaultTableModel model;
    private List<Class> classes;
    private final String[] columnNames =
            {"#", "ClassName", "Number of Student", "Number of Male", "Number of Female"};

    public ClassTable() {
        services = new ClassServices();

        initComponents();
        loadData();
        handleEventComponent();
    }

    private void initComponents() {
        services = new ClassServices();

        // Init model
        model = new DefaultTableModel() {
            @Override
            public java.lang.Class<?> getColumnClass(int column) {
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
        classes = services.getAllClassWithStudents();
        classes.forEach(classObj -> {
            model.addRow(
                    new Object[] {false, classObj.getClassName(), classObj.getNumberOfStudent(),
                            classObj.getNumberOfMale(), classObj.getNumberOfFemale()});
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
                Class selectedAm = classes.get(idxInModel);
                services.deleteClass(selectedAm);

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
