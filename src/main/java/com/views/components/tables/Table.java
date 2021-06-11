package com.views.components.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.constants.ConfigUI;

public class Table extends JTable {
    private static final Insets columnGap =
            new Insets(0, ConfigUI.Padding.NORMAL, 0, ConfigUI.Padding.NORMAL);

    public Table() {
        super();
        initComponent();
    }

    private void initComponent() {
        // Init Table Header
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setDefaultRenderer(new TableHeaderRenderer());
        tableHeader.setReorderingAllowed(false);

        // Init table
        setDefaultRenderer(Date.class, new DateCellRenderer());
        setSelectionForeground(ConfigUI.SysColor.DARK);
        setGridColor(ConfigUI.SysColor.GRAY);

        setShowGrid(false);
        setAutoCreateRowSorter(true);

        setRowSelectionAllowed(true);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0)
            return true;
        return false;
    }

    @Override
    public DefaultTableModel getModel() {
        return (DefaultTableModel) super.getModel();
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        return changeComponentBeforeRenderer(c, row, column);
    }

    @Override
    public Component prepareEditor(TableCellEditor editor, int row, int column) {
        Component c = super.prepareEditor(editor, row, column);
        return changeComponentBeforeRenderer(c, row, column);
    }

    @Override
    public void doLayout() {
        super.doLayout();

        setRowHeight(ConfigUI.Table.ROW_HEIGHT);
        getColumnModel().getColumn(0).setMaxWidth(50);
        getTableHeader().setPreferredSize(new Dimension(getWidth(), ConfigUI.Table.ROW_HEIGHT));
    }

    private Component changeComponentBeforeRenderer(Component c, int row, int column) {
        c.setBackground(row % 2 == 0 ? Color.WHITE : ConfigUI.SysColor.GRAY_LIGHT);

        if (c instanceof JCheckBox) {
            ((JCheckBox) c).setHorizontalAlignment(JCheckBox.LEADING);
        }

        ((JComponent) c).setBorder(null);
        ((JComponent) c).setBorder(new EmptyBorder(columnGap));
        return c;
    }

    private class TableHeaderRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);

            c.setFont(ConfigUI.DefaultFont.H3);
            c.setBorder(new EmptyBorder(columnGap));
            c.setBackground(ConfigUI.SysColor.GRAY);
            c.setHorizontalAlignment(JLabel.LEADING);
            return c;
        }
    }

    private class DateCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            c.setText(formatter.format((Date) value));

            return c;
        }
    }
}
