package com.views.components;

import java.awt.Insets;
import javax.swing.JComboBox;
import com.constants.ConfigUI;
import com.utils.UIFactory;

public class TComboBox<T> extends JComboBox<T> {
    public TComboBox() {
        super();
        setRenderer(new TComboBoxListCellRenderer<T>());
        initComponents();
    }

    public TComboBox(T[] items) {
        super(items);
        setRenderer(new TComboBoxListCellRenderer<T>());
        initComponents();
    }

    private void initComponents() {
        setBorder(UIFactory.combineBorderPadding(getBorder(), new Insets(ConfigUI.Padding.TINY,
                ConfigUI.Padding.SMALL, ConfigUI.Padding.TINY, ConfigUI.Padding.SMALL)));
    }
}

