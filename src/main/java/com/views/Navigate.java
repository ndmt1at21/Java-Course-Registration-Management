package com.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.constants.ConfigUI;
import com.utils.UIFactory;

public class Navigate extends JPanel {
    private final List<String> items = Arrays.asList("Student", "Academic Manager", "Subject",
            "Course", "Semester Register Session", "Semester");

    JPanel pTop;
    JPanel pCenter;
    JPanel pBottom;
    JButton accountBtn;
    JButton logoutBtn;
    private List<JButton> navItemBtns;

    public Navigate() {
        super();
        this.initComponent();
    }

    private void initComponent() {
        // Init nav
        setLayout(new BorderLayout());

        // Create components
        pTop = new JPanel();
        pCenter = new JPanel();
        pBottom = new JPanel();
        accountBtn = UIFactory.createBtn("NGUYEN DUC MINH TRI", null);
        logoutBtn = UIFactory.createBtn("", null);

        navItemBtns = new ArrayList<>();
        items.forEach(item -> {
            JButton btn = new JButton(item);
            btn.setOpaque(false);
            btn.setBackground(ConfigUI.SysColor.DARK);
            btn.setBorder(new EmptyBorder(0, 50, 0, 50));
            btn.setHorizontalAlignment(JButton.LEFT);
            btn.setForeground(ConfigUI.SysColor.WHITE);
            btn.setFont(ConfigUI.DefaultFont.H3);
            navItemBtns.add(btn);
        });

        // Setting components
        pTop.setOpaque(false);
        pCenter.setOpaque(false);
        pBottom.setOpaque(false);

        pTop.setLayout(new BoxLayout(pTop, BoxLayout.Y_AXIS));
        pTop.setBorder(new EmptyBorder(10, 0, 10, 0));
        pCenter.setLayout(new GridLayout(items.size(), 1));
        pBottom.setLayout(new BoxLayout(pBottom, BoxLayout.Y_AXIS));
        pBottom.setBorder(new EmptyBorder(10, 0, 10, 0));

        accountBtn.setFont(ConfigUI.DefaultFont.H2);
        accountBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        logoutBtn.setFont(ConfigUI.DefaultFont.H3);
        logoutBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        logoutBtn.setBackground(ConfigUI.SysColor.DARK);


        pTop.add(accountBtn);
        pBottom.add(logoutBtn);
        navItemBtns.forEach(btn -> {
            pCenter.add(btn);
        });

        add(pTop, BorderLayout.NORTH);
        add(pBottom, BorderLayout.SOUTH);
        add(pCenter, BorderLayout.CENTER);
    }

    @Override
    public void doLayout() {
        super.doLayout();

        pCenter.setBorder(new EmptyBorder(pCenter.getHeight() / 4, 0, pCenter.getHeight() / 4, 0));
    }
}
