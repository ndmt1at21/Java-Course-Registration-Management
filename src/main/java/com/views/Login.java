package com.views;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.constants.ConfigUI;

public class Login extends JFrame {
    JButton btn;
    Navigate nav;

    public Login() {
        super();
        initComponent();
    }

    private void initComponent() {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Navigate nav = new Navigate();
        add(nav);
    }
}
