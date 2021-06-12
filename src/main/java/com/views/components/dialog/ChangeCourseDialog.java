package com.views.components.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.DayOfWeek;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;
import com.models.Class;
import com.models.Course;
import com.models.Semester;
import com.models.ShiftTime;
import com.models.Subject;
import com.utils.UIFactory;
import com.views.components.TComboBox;
import com.views.components.TSpinner;

/**
 * Use for editting or creating course
 */
public class ChangeCourseDialog extends JDialog {
   private JLabel headerLabel;
   private JLabel teacherNameLabel;
   private JLabel departLabel;
   private JLabel numberSlotLabel;
   private JLabel subjectLabel;
   private JLabel creditsLabel;
   private JLabel dayOfWeekLabel;
   private JLabel shiftTimeLabel;
   private JLabel classLabel;
   private JLabel semesterLabel;

   private JTextField teacherNameTextField;
   private JTextField departTextField;
   private JTextField creditsTextField;
   private TSpinner numberSlotSpinner;

   private TComboBox<Subject> subjectPicker;
   private TComboBox<DayOfWeek> dayOfWeekPicker;
   private TComboBox<ShiftTime> shiftTimePicker;
   private TComboBox<Class> classPicker;
   private TComboBox<Semester> semesterPicker;

   private Course course;

   JPanel pContainer;
   JButton createBtn;

   public ChangeCourseDialog() {
      super();
      initComponents();
   }

   public ChangeCourseDialog(Course course) {
      super();

      this.course = course;
      initComponents();
   }

   public void setCourse(Course course) {
      this.course = course;

      loadCourseData();
      layoutComponents();
      repaint();
   }

   public Course getCourse() {
      return this.course;
   }

   private void initComponents() {
      // Setup frame
      setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      setSize(1152, 700);
      setLayout(new BorderLayout(0, 0));

      // Set up container
      pContainer = new JPanel();
      pContainer.setBorder(new EmptyBorder(100, 100, 50, 100));
      pContainer.setLayout(new GridBagLayout());

      JScrollPane scrollPane = new JScrollPane(pContainer);
      scrollPane.getVerticalScrollBar().setUnitIncrement(ConfigUI.SCROLL_SPEED);
      add(scrollPane, BorderLayout.CENTER);

      // Create components
      headerLabel = new JLabel(course == null ? "Create Course" : "Edit Course");
      teacherNameLabel = new JLabel("Teacher Name");
      departLabel = new JLabel("Department Name");
      numberSlotLabel = new JLabel("Number of Slot");
      subjectLabel = new JLabel("Subject");
      creditsLabel = new JLabel("Credits");
      dayOfWeekLabel = new JLabel("Day of Week");
      shiftTimeLabel = new JLabel("Shift Time");
      classLabel = new JLabel("Class");
      semesterLabel = new JLabel("Semester");

      teacherNameTextField = UIFactory.createTextField();
      departTextField = UIFactory.createTextField();
      creditsTextField = UIFactory.createTextField();
      numberSlotSpinner = new TSpinner();

      subjectPicker = new TComboBox<>();
      dayOfWeekPicker = new TComboBox<>();
      shiftTimePicker = new TComboBox<>();
      classPicker = new TComboBox<>();
      semesterPicker = new TComboBox<>();

      createBtn = UIFactory.createBtn("Create", ConfigUI.SysColor.PRIMARY);

      // Components setting
      headerLabel.setFont(ConfigUI.DefaultFont.H1);
      creditsTextField.setEditable(false);

      subjectPicker.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
               Subject subject = (Subject) e.getItem();
               creditsTextField.setText(String.valueOf(subject.getCredits()));
            }
         }
      });

      createBtn.setFont(ConfigUI.DefaultFont.H3);
      createBtn.setForeground(ConfigUI.SysColor.WHITE);

      loadData();
      loadCourseData();
      layoutComponents();
   }

   /**
    * Load data for all spinnner, combox box
    */
   private void loadData() {

   }

   /**
    * Load manager data when manager not null. <br>
    * Just run when dialog is use for editing current mananger. <br>
    * It must run after load all data relative (ex. picker data)
    */
   private void loadCourseData() {
      if (course != null) {
         headerLabel = new JLabel(course == null ? "Create Course" : "Edit Course");

         teacherNameTextField.setText(course.getTeacherName());
         departTextField.setText(course.getDepartmentName());
         numberSlotSpinner.setValue(course.getNumberOfSlot());
         subjectPicker.setSelectedItem(course.getSubject());
         creditsTextField.setText(String.valueOf(course.getSubject().getCredits()));
         dayOfWeekPicker.setSelectedItem(course.getDayOfWeek());
         shiftTimePicker.setSelectedItem(course.getShiftTime());
         classPicker.setSelectedItem(course.getClassObj());
         semesterPicker.setSelectedItem(course.getSemester());
      }
   }

   private void layoutComponents() {
      // Create constraints
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      gbc.gridx = 0;
      gbc.weightx = 1;

      // Add componennts
      int padLabelAndField = 10;

      gbc.gridy = 0;
      gbc.ipady = 20;
      gbc.insets = new Insets(0, 0, 50, 0);
      pContainer.add(headerLabel, gbc);

      gbc.gridy++;
      gbc.gridwidth = 2;
      gbc.insets = new Insets(0, 0, 0, 20);
      pContainer.add(UIFactory.generateForm(subjectLabel, subjectPicker, padLabelAndField, true),
            gbc);

      // Column 1
      gbc.gridy++;
      gbc.gridwidth = 1;
      gbc.gridx = 0;
      pContainer.add(UIFactory.generateForm(semesterLabel, semesterPicker, padLabelAndField, true),
            gbc);

      gbc.gridy++;
      pContainer.add(UIFactory.generateForm(classLabel, classPicker, padLabelAndField, true), gbc);

      gbc.gridy++;
      pContainer.add(UIFactory.generateForm(departLabel, departTextField, padLabelAndField, true),
            gbc);

      gbc.gridy++;
      pContainer.add(
            UIFactory.generateForm(dayOfWeekLabel, dayOfWeekPicker, padLabelAndField, true), gbc);

      // Column 2
      gbc.gridy = 2;
      gbc.gridx = 1;
      gbc.gridy++;
      pContainer.add(UIFactory.generateForm(creditsLabel, creditsTextField, padLabelAndField, true),
            gbc);


      pContainer.add(
            UIFactory.generateForm(numberSlotLabel, numberSlotSpinner, padLabelAndField, true),
            gbc);

      gbc.gridy++;
      pContainer.add(
            UIFactory.generateForm(teacherNameLabel, teacherNameTextField, padLabelAndField, true),
            gbc);

      gbc.gridy++;
      pContainer.add(
            UIFactory.generateForm(shiftTimeLabel, shiftTimePicker, padLabelAndField, true), gbc);

      gbc.gridx = 0;
      gbc.gridy++;
      gbc.insets = new Insets(30, 0, 0, 0);
      pContainer.add(UIFactory.generateForm(null, createBtn, 0, false), gbc);
   }

   @Override
   public void doLayout() {
      super.doLayout();

      if (getWidth() < 700) {
         pContainer.setBorder(new EmptyBorder(50, 50, 20, 50));
      }
   }
}


