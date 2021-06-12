package com.views.components;

import java.awt.Component;
import java.time.DayOfWeek;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import com.models.Class;
import com.models.Semester;
import com.models.ShiftTime;
import com.models.Subject;

class CustomListCellRenderer extends DefaultListCellRenderer {

   @Override
   public Component getListCellRendererComponent(JList<?> list, Object value, int index,
         boolean isSelected, boolean cellHasFocus) {

      String showStr = "";

      if (value instanceof Subject) {
         Subject subject = (Subject) value;
         showStr = subject.getSubjectCode() + " - " + subject.getSubjectName();
      }

      if (value instanceof DayOfWeek) {
         showStr = value.toString();
      }

      if (value instanceof ShiftTime) {
         showStr = value.toString();
      }

      if (value instanceof Class) {
         showStr = ((Class) value).getClassName();
      }

      if (value instanceof Semester) {
         Semester semester = (Semester) value;
         showStr = semester.getYear() + " - " + semester.getSemNo();
      }

      return super.getListCellRendererComponent(list, showStr, index, isSelected, cellHasFocus);
   }

}
