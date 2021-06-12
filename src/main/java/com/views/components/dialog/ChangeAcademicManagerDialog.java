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
import com.models.AcademicManager;
import com.utils.UIFactory;
import com.views.components.DatePicker;

/**
 * Use to update or create new academic mananger
 */
public class ChangeAcademicManagerDialog extends JDialog {
      private final String pwdDefault = "123456789";

      private JTextField idTextField;
      private JTextField pwdTextField;
      private JTextField firstNameTextField;
      private JTextField lastNameTextField;
      private JTextField addressTextField;
      private JButton createBtn;
      private DatePicker birthPicker;

      private AcademicManager manager;

      private JLabel header;
      private JLabel idLabel;
      private JLabel pwdLabel;
      private JLabel firstNameLabel;
      private JLabel lastNameLabel;
      private JLabel birthLabel;
      private JLabel addressLabel;
      private JLabel sexLabel;

      JRadioButton sexMale;
      JRadioButton sexFemale;
      ButtonGroup sexGroup;

      JPanel pContainer;
      JPanel pSex;


      public ChangeAcademicManagerDialog() {
            super();
            initComponents();
      }

      /**
       * If you want to edit current manager, use this contructor
       * 
       * @param manager manager will be edited
       */
      public ChangeAcademicManagerDialog(AcademicManager mananger) {
            super();

            this.manager = mananger;
            initComponents();
      }

      /**
       * If you want to edit current manager, use this contructor
       * 
       * @param manager manager will be editted
       */
      public void setManager(AcademicManager manager) {
            this.manager = manager;

            loadManagerData();
            layoutComponents();
            repaint();
      }

      public AcademicManager getManager() {
            return this.manager;
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
            header = new JLabel("Create Academic Manager");
            idLabel = new JLabel("Academic Mananger ID");
            pwdLabel = new JLabel("Password");
            firstNameLabel = new JLabel("First Name");
            lastNameLabel = new JLabel("Last Name");
            birthLabel = new JLabel("Birth");
            addressLabel = new JLabel("Address");
            sexLabel = new JLabel("Gender");

            sexMale = new JRadioButton("Male");
            sexFemale = new JRadioButton("Female");
            sexGroup = new ButtonGroup();

            idTextField = UIFactory.createTextField();
            firstNameTextField = UIFactory.createTextField();
            lastNameTextField = UIFactory.createTextField();
            pwdTextField = UIFactory.createTextField();
            addressTextField = UIFactory.createTextField();

            birthPicker = new DatePicker();

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

            loadManagerData();
            layoutComponents();
      }

      /**
       * Load manager data when manager not null.<br>
       * Just run when dialog is use for editing current mananger <br>
       * It must run after load all data relative (ex. picker data)
       */
      private void loadManagerData() {
            if (this.manager != null) {
                  idTextField.setText(manager.getAcademicManagerID());
                  idTextField.setEditable(false);
                  firstNameTextField.setText(manager.getFirstName());
                  lastNameTextField.setText(manager.getLastName());
                  pwdTextField.setText(manager.getPassword());
                  addressTextField.setText(manager.getAddress());
                  sexGroup.setSelected(manager.getSex() == Sex.MALE ? sexMale.getModel()
                              : sexFemale.getModel(), true);
                  birthPicker.setDate(manager.getBirth());
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

            if (manager != null) {
                  gbc.gridy++;
                  pContainer.add(
                              UIFactory.generateForm(idLabel, idTextField, padLabelAndField, true),
                              gbc);
            }

            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 0, 20);
            pContainer.add(UIFactory.generateForm(firstNameLabel, firstNameTextField,
                        padLabelAndField, true), gbc);

            gbc.gridy++;
            pContainer.add(UIFactory.generateForm(lastNameLabel, lastNameTextField,
                        padLabelAndField, true), gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.insets = new Insets(0, 0, 0, 20);
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
