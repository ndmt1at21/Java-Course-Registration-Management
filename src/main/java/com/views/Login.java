package com.views;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.constants.ConfigUI;

public class Login extends JFrame {
    JButton btn;

    public Login() {
        btn = new JButton("Button");
        btn.setFont(ConfigUI.DefaultFont.h1);
        add(btn);

    }
}
