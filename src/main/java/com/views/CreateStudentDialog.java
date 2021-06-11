package com.views;

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
import com.utils.UIFactory;
import com.views.components.DatePicker;
import com.views.components.TComboBox;

public class CreateStudentDialog extends JDialog {
    private JTextField pwdTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField addressTextField;
    private JButton createBtn;
    private DatePicker datePicker;
    private TComboBox<String> classPicker;

    public CreateStudentDialog() {
        super();
        initComponents();
    }

    private void initComponents() {
        // Setup frame
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(1152, 700);
        setLayout(new BorderLayout(0, 0));

        // Set up container
        JPanel pContainer = new JPanel();
        pContainer.setBorder(new EmptyBorder(50, 50, 50, 50));
        pContainer.setLayout(new GridBagLayout());
        add(new JScrollPane(pContainer), BorderLayout.CENTER);

        JPanel pSex = new JPanel();
        pSex.setLayout(new BoxLayout(pSex, BoxLayout.X_AXIS));

        // Create components
        JLabel header = new JLabel("Create Student");
        JLabel pwdLabel = new JLabel("Password (Default)");
        JLabel firstNameLabel = new JLabel("First Name");
        JLabel lastNameLabel = new JLabel("Last Name");
        JLabel birthLabel = new JLabel("Birth");
        JLabel addressLabel = new JLabel("Address");
        JLabel sexLabel = new JLabel("Gender");
        JLabel classLabel = new JLabel("Class");

        JRadioButton sexMale = new JRadioButton("Male");
        JRadioButton sexFemale = new JRadioButton("Female");
        ButtonGroup sexGroup = new ButtonGroup();

        firstNameTextField = UIFactory.createTextField();
        lastNameTextField = UIFactory.createTextField();
        pwdTextField = UIFactory.createTextField();
        addressTextField = UIFactory.createTextField();

        datePicker = new DatePicker();
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

        pwdTextField.setText("123456789");
        datePicker.setHGap(10);

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

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 20);
        pContainer.add(
                UIFactory.generateForm(firstNameLabel, firstNameTextField, padLabelAndField, true),
                gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        pContainer.add(
                UIFactory.generateForm(lastNameLabel, lastNameTextField, padLabelAndField, true),
                gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 20);
        pContainer.add(UIFactory.generateForm(pwdLabel, pwdTextField, padLabelAndField, true), gbc);

        gbc.gridy++;
        pContainer.add(UIFactory.generateForm(sexLabel, pSex, padLabelAndField, true), gbc);

        gbc.gridy++;
        pContainer.add(UIFactory.generateForm(birthLabel, datePicker, padLabelAndField, false),
                gbc);

        gbc.gridy++;
        pContainer.add(
                UIFactory.generateForm(addressLabel, addressTextField, padLabelAndField, true),
                gbc);

        gbc.gridy++;
        pContainer.add(UIFactory.generateForm(classLabel, classPicker, padLabelAndField, false),
                gbc);

        gbc.gridy++;
        gbc.insets = new Insets(30, 0, 0, 0);
        pContainer.add(UIFactory.generateForm(null, createBtn, 0, false), gbc);
    }
}
