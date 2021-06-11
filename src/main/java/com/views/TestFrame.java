package com.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import com.views.components.dialog.CreateSubjectDialog;

public class TestFrame extends JFrame {
    public TestFrame() {
        super();
        setSize(500, 500);

        JButton btn = new JButton("CLick");
        add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateSubjectDialog dia = new CreateSubjectDialog();
                dia.setVisible(true);
            }
        });
    }
}
