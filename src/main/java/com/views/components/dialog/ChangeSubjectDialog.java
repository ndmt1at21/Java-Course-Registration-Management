package com.views.components.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;
import com.models.Subject;
import com.utils.UIFactory;
import com.views.components.TComboBox;

/**
 * Use for editting or creating subject
 */
public class ChangeSubjectDialog extends JDialog {
    private JTextField subjectCodeTextField;
    private JTextField subjectNameTextField;
    private TComboBox<Integer> creditPicker;
    private JButton createBtn;

    private Subject subject;

    private JLabel header;
    private JLabel subjectCodeLabel;
    private JLabel subjectNameLabel;
    private JLabel creditLabel;

    private JPanel pContainer;

    public ChangeSubjectDialog() {
        super();
        initComponents();
    }

    /**
     * If you want to edit current subject, use this contructor
     * 
     * @param subject subject will be editted
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
        loadSubjectData();
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

        // Create components
        header = new JLabel("Create Subject");
        subjectCodeLabel = new JLabel("Subject Code");
        subjectNameLabel = new JLabel("Subject Name");
        creditLabel = new JLabel("Credits");

        subjectCodeTextField = UIFactory.createTextField();
        subjectNameTextField = UIFactory.createTextField();
        creditPicker = new TComboBox<>();

        createBtn = UIFactory.createBtn("Create", ConfigUI.SysColor.PRIMARY);

        // Components setting
        header.setFont(ConfigUI.DefaultFont.H1);
        createBtn.setForeground(ConfigUI.SysColor.WHITE);

        loadSubjectData();
        layoutComponents();
    }

    /**
     * Load subject data when subject not null <br>
     * Just run when dialog is use for editing current subject<br>
     * It must run after load all data relative (ex. picker data)
     */
    private void loadSubjectData() {
        if (subject != null) {
            subjectCodeTextField.setText(subject.getSubjectCode());
            subjectNameLabel.setText(subject.getSubjectName());
            creditPicker.setSelectedItem(subject.getCredits());
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

    private List<Integer> getListCredits() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(i);
        }

        return list;
    }

    @Override
    public void doLayout() {
        super.doLayout();

        if (getWidth() < 700) {
            pContainer.setBorder(new EmptyBorder(50, 50, 20, 50));
        }
    }
}
