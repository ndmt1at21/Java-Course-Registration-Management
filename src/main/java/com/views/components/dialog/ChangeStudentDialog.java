package com.views.components.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;
import com.constants.Sex;
import com.models.Student;
import com.utils.UIFactory;
import com.views.components.DatePicker;
import com.views.components.TComboBox;

/**
 * Use for editting or creating student
 */
public class ChangeStudentDialog extends JDialog {
      private final String pwdDefault = "123456789";

      private JTextField pwdTextField;
      private JTextField firstNameTextField;
      private JTextField lastNameTextField;
      private JTextField addressTextField;
      private JTextField idTextField;
      private JTextField startYearTextField;

      private JButton createBtn;
      private DatePicker birthPicker;
      private TComboBox<String> classPicker;

      private Student student;

      JLabel header;
      JLabel idLabel;
      JLabel pwdLabel;
      JLabel firstNameLabel;
      JLabel lastNameLabel;
      JLabel birthLabel;
      JLabel addressLabel;
      JLabel sexLabel;
      JLabel classLabel;
      JLabel startYearLabel;

      JRadioButton sexMale;
      JRadioButton sexFemale;
      ButtonGroup sexGroup;

      JPanel pContainer;
      JPanel pSex;

      public ChangeStudentDialog() {
            super();
            initComponents();
      }

      /**
       * If you want to edit current student, use this contructor
       * 
       * @param student student will be editted
       */
      public ChangeStudentDialog(Student student) {
            super();

            this.student = student;
            initComponents();
      }

      /**
       * If you want to edit current student, use this contructor
       * 
       * @param student student will be editted
       */
      public void setStudent(Student student) {
            this.student = student;

            loadStudentData();
            layoutComponents();
            repaint();
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

            pSex = new JPanel();
            pSex.setLayout(new BoxLayout(pSex, BoxLayout.X_AXIS));

            // Create components
            header = new JLabel("Create Student");
            idLabel = new JLabel("Student ID");
            pwdLabel = new JLabel("Password (Default)");
            firstNameLabel = new JLabel("First Name");
            lastNameLabel = new JLabel("Last Name");
            birthLabel = new JLabel("Birth");
            addressLabel = new JLabel("Address");
            sexLabel = new JLabel("Gender");
            classLabel = new JLabel("Class");
            startYearLabel = new JLabel("Start Year");

            sexMale = new JRadioButton("Male");
            sexFemale = new JRadioButton("Female");
            sexGroup = new ButtonGroup();

            firstNameTextField = UIFactory.createTextField();
            lastNameTextField = UIFactory.createTextField();
            pwdTextField = UIFactory.createTextField();
            addressTextField = UIFactory.createTextField();
            startYearTextField = UIFactory.createTextField();

            birthPicker = new DatePicker();
            classPicker = new TComboBox<>();

            createBtn = UIFactory.createBtn("Create", ConfigUI.SysColor.PRIMARY);
            createBtn.setFont(ConfigUI.DefaultFont.H3);
            createBtn.setForeground(ConfigUI.SysColor.WHITE);

            // Setting components
            header.setFont(ConfigUI.DefaultFont.H1);
            sexGroup.add(sexMale);
            sexGroup.add(sexFemale);

            pSex.add(sexMale);
            pSex.add(Box.createRigidArea(new Dimension(20, 0)));
            pSex.add(sexFemale);

            pwdTextField.setText(pwdDefault);
            birthPicker.setHGap(10);

            // Layout
            loadStudentData();
            layoutComponents();
      }

      /**
       * Load student data when student not null. <br>
       * Just run when dialog is use for editing current student <br>
       * It must run after load all data relative (ex. picker data)
       */
      private void loadStudentData() {
            if (this.student != null) {
                  idTextField.setText(student.getStudentID());
                  idTextField.setEditable(false);

                  firstNameTextField.setText(student.getFirstName());
                  lastNameTextField.setText(student.getLastName());
                  pwdTextField.setText(student.getPassword());
                  addressTextField.setText(student.getAddress());
                  sexGroup.setSelected(student.getSex() == Sex.MALE ? sexMale.getModel()
                              : sexFemale.getModel(), true);
                  birthPicker.setDate(student.getBirth());
                  classPicker.setSelectedItem(student.getStudentClass().getClassName());

                  startYearTextField.setText(String.valueOf(student.getStartYear()));
                  startYearTextField.setEditable(false);
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
            pContainer.add(header, gbc);

            if (student != null) {
                  gbc.gridy++;
                  pContainer.add(
                              UIFactory.generateForm(idLabel, idTextField, padLabelAndField, true),
                              gbc);
            }

            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 10, 0);
            pContainer.add(UIFactory.generateForm(firstNameLabel, firstNameTextField,
                        padLabelAndField, true), gbc);

            gbc.gridy++;
            pContainer.add(UIFactory.generateForm(lastNameLabel, lastNameTextField,
                        padLabelAndField, true), gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            pContainer.add(UIFactory.generateForm(pwdLabel, pwdTextField, padLabelAndField, true),
                        gbc);

            gbc.gridy++;
            pContainer.add(UIFactory.generateForm(sexLabel, pSex, padLabelAndField, true), gbc);

            gbc.gridy++;
            pContainer.add(UIFactory.generateForm(birthLabel, birthPicker, padLabelAndField, false),
                        gbc);

            gbc.gridy++;
            pContainer.add(UIFactory.generateForm(addressLabel, addressTextField, padLabelAndField,
                        true), gbc);

            gbc.gridy++;
            pContainer.add(UIFactory.generateForm(classLabel, classPicker, padLabelAndField, false),
                        gbc);

            if (student != null) {
                  gbc.gridx = 1;
                  gbc.gridy++;
                  pContainer.add(UIFactory.generateForm(startYearLabel, startYearTextField,
                              padLabelAndField, true), gbc);
            }

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.insets = new Insets(30, 0, 0, 0);
            pContainer.add(UIFactory.generateForm(null, createBtn, 0, false), gbc);
      }

      private void loadData() {

      }

      @Override
      public void doLayout() {
            super.doLayout();

            if (getWidth() < 700) {
                  pContainer.setBorder(new EmptyBorder(50, 50, 20, 50));
            }
      }
}
