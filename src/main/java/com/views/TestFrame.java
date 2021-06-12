package com.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import com.views.components.dialog.ChangeCourseDialog;

public class TestFrame extends JFrame {
    public TestFrame() {
        super();
        setSize(500, 500);

        JButton btn = new JButton("CLick");
        add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCourseDialog dia = new ChangeCourseDialog();
                dia.setVisible(true);
            }
        });
    }
}
