package com.views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;
import com.utils.UIFactory;

public class Login extends JFrame {
    private JTextField usrTextField;
    private JPasswordField pwdTextField;

    public Login() {
        super();
        initComponents();
    }

    private void initComponents() {
        // Setup frame
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout(0, 0));

        // Set up content
        JPanel pContainer = new JPanel();
        pContainer.setBorder(new EmptyBorder(50, 50, 50, 50));
        pContainer.setBackground(ConfigUI.SysColor.YELLOW);
        add(pContainer);

        JLabel header = new JLabel("Sign in");
        header.setFont(ConfigUI.DefaultFont.H1);
        header.setForeground(ConfigUI.SysColor.DARK);

        JLabel usrLabel = new JLabel("Username");
        usrLabel.setFont(ConfigUI.DefaultFont.H3);
        usrLabel.setForeground(ConfigUI.SysColor.DARK);

        JLabel pwdLabel = new JLabel("Password");
        pwdLabel.setFont(ConfigUI.DefaultFont.H3);
        pwdLabel.setForeground(ConfigUI.SysColor.DARK);

        usrTextField = UIFactory.createTextField();
        pwdTextField = UIFactory.createPasswordTextField();

        // Setup container
        pContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        pContainer.add(header, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 5, 0);
        pContainer.add(usrLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 20, 0);
        pContainer.add(usrTextField, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 5, 0);
        pContainer.add(pwdLabel, gbc);

        gbc.gridy++;
        pContainer.add(pwdTextField, gbc);
    }
}
