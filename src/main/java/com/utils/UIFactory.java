package com.utils;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.constants.ConfigUI;

public class UIFactory {
    public static JButton createBtn(String str, Color color) {
        JButton btn = new JButton(str);
        btn.setBorder(combineBorderPadding(btn.getBorder(), new Insets(ConfigUI.Padding.SMALL, ConfigUI.Padding.NORMAL,
                ConfigUI.Padding.SMALL, ConfigUI.Padding.NORMAL)));
        btn.setBackground(color);
        return btn;
    }

    public static JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBorder(combineBorderPadding(textField.getBorder(), new Insets(ConfigUI.Padding.SMALL,
                ConfigUI.Padding.NORMAL, ConfigUI.Padding.SMALL, ConfigUI.Padding.NORMAL)));
        return textField;
    }

    private static Border combineBorderPadding(Border currBorder, Insets padding) {
        Border currOutsideBorder = currBorder;
        Border newInsideBorder = new EmptyBorder(padding);

        if (currBorder instanceof CompoundBorder) {
            currOutsideBorder = ((CompoundBorder) currBorder).getOutsideBorder();
        }

        Border border = BorderFactory.createCompoundBorder(currOutsideBorder, newInsideBorder);
        return border;
    }
}
