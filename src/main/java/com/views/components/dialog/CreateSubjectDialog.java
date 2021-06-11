package com.views.components.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;
import com.utils.UIFactory;
import com.views.components.TComboBox;

public class CreateSubjectDialog extends JDialog {
    private JTextField subjectCodeTextField;
    private JTextField subjectNameTextField;
    private TComboBox<Integer> creditPicker;
    private JButton createBtn;

    public CreateSubjectDialog() {
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

        // Create components
        JLabel header = new JLabel("Create Subject");
        JLabel subjectCodeLabel = new JLabel("Subject Code");
        JLabel subjectNameLabel = new JLabel("Subject Name");
        JLabel creditLabel = new JLabel("Credits");

        subjectCodeTextField = UIFactory.createTextField();
        subjectNameTextField = UIFactory.createTextField();
        creditPicker = new TComboBox<>();

        createBtn = UIFactory.createBtn("Create", ConfigUI.SysColor.PRIMARY);

        // Components setting
        header.setFont(ConfigUI.DefaultFont.H1);
        createBtn.setForeground(ConfigUI.SysColor.WHITE);

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
        gbc.insets = new Insets(0, 0, 10, 0);
        pContainer.add(UIFactory.generateForm(subjectCodeLabel, subjectCodeTextField,
                padLabelAndField, true), gbc);

        gbc.gridy++;
        pContainer.add(UIFactory.generateForm(subjectNameLabel, subjectNameTextField,
                padLabelAndField, true), gbc);

        gbc.gridy++;
        pContainer.add(UIFactory.generateForm(creditLabel, creditPicker, padLabelAndField, false),
                gbc);

        gbc.gridy++;
        gbc.insets = new Insets(30, 0, 0, 0);
        pContainer.add(UIFactory.generateForm(null, createBtn, 0, false), gbc);
    }
}
