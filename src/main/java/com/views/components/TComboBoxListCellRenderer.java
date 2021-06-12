package com.views.components;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;

class TComboBoxListCellRenderer<T> extends DefaultListCellRenderer {

   public TComboBoxListCellRenderer() {
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
