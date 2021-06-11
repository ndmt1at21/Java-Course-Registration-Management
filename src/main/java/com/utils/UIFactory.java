package com.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;

public class UIFactory {
    public static JButton createBtn(String str, Color color) {
        JButton btn = new JButton(str);
        btn.setBorder(combineBorderPadding(btn.getBorder(), new Insets(ConfigUI.Padding.TINY,
                ConfigUI.Padding.SMALL, ConfigUI.Padding.TINY, ConfigUI.Padding.SMALL)));
        btn.setBackground(color);

        return btn;
    }

    public static JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBorder(
                combineBorderPadding(textField.getBorder(), new Insets(ConfigUI.Padding.NORMAL,
                        ConfigUI.Padding.TINY, ConfigUI.Padding.NORMAL, ConfigUI.Padding.TINY)));
        return textField;
    }

    public static JPasswordField createPasswordTextField() {
        JPasswordField textField = new JPasswordField();
        textField.setBorder(
                combineBorderPadding(textField.getBorder(), new Insets(ConfigUI.Padding.NORMAL,
                        ConfigUI.Padding.TINY, ConfigUI.Padding.NORMAL, ConfigUI.Padding.TINY)));
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

    public static JPanel generateForm(JLabel label, JTextField textField, int padBetween) {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        label.setFont(ConfigUI.DefaultFont.H3);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = padBetween;
        p.add(label, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1;
        p.add(textField, gbc);

        return p;
    }

}


class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }
}
