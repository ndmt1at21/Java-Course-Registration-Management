package com.views.components;

import java.awt.Component;
import java.awt.Insets;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;
import com.utils.UIFactory;

public class TComboBox<T> extends JComboBox<T> {
    public TComboBox() {
        super();
        setRenderer(new TComboBoxRenderer<T>());
        initComponents();
    }

    public TComboBox(T[] items) {
        super(items);
        setRenderer(new TComboBoxRenderer<T>());
        initComponents();
    }

    private void initComponents() {
        setBorder(UIFactory.combineBorderPadding(getBorder(), new Insets(ConfigUI.Padding.SMALL,
                ConfigUI.Padding.TINY, ConfigUI.Padding.SMALL, ConfigUI.Padding.TINY)));
    }
}


class TComboBoxRenderer<T> extends DefaultListCellRenderer {

    public TComboBoxRenderer() {
        super();
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        Component c =
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (c instanceof JLabel) {
            ((JLabel) c).setBorder(new EmptyBorder(ConfigUI.Padding.NORMAL, ConfigUI.Padding.SMALL,
                    ConfigUI.Padding.NORMAL, ConfigUI.Padding.SMALL));
        }

        return c;
    }
}
