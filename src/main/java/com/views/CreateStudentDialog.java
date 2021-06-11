package com.views;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.views.components.DatePicker;

public class CreateStudentDialog extends JDialog {
    private JTextField usrTextField;
    private JTextField firstNameTextField;
    private JTtextField lastNameTextField;
    private JTextField addressTextField;

    public CreateStudentDialog() {
        super();
        initComponents();
    }

    private void initComponents() {
        // Setup frame
        setResizable(false);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setSize(1152, 700);
        setLayout(new BorderLayout(0, 0));

        // Set up content
        JPanel pContainer = new JPanel();
        pContainer.setBorder(new EmptyBorder(50, 50, 50, 50));
        add(pContainer);

        JLabel header = new JLabel("Create Student");

        DatePicker picker = new DatePicker();
        add(picker);
    }
}
