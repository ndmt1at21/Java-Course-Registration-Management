package com.crs;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.constants.ConfigUI;
import com.constants.Sex;
import com.dao.ConfiguarationReponsitory;
import com.dev.LoadDevDataToDB;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.models.Class;
import com.models.CourseRegistration;
import com.models.Student;
import com.services.ClassServices;
import com.services.CourseRegistrationServices;
import com.services.CourseServices;
import com.services.StudentServices;
import com.views.Login;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.test();

        Student stu = new Student();

        // CourseRegistrationServices ser = new CourseRegistrationServices();
        // CourseServices ser2 = new CourseServices();
        // List<CourseRegistration> cReg = ser.findByCourseID(ser2.getCourses(1,
        // 1).get(0).getCourseID());

        // cReg.forEach(c -> {
        // System.out.println(c.getStudent().getUserId());
        // });

        // ConfiguarationReponsitory res = new ConfiguarationReponsitory();
        // res.addConfiguaration("test", Integer.valueOf(2));

        // System.out.println(res.getConfiguration("test").toString());
    }

    public void test() {
        // LoadDevDataToDB.loadAllDataDev();
        initTheme();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.setVisible(true);
            }
        });
    }

    public void initTheme() {
        try {
            File file = new File("./src/main/resources/LightFlatTheme.theme.json");
            UIManager.setLookAndFeel(IntelliJTheme.createLaf(new FileInputStream(file)));
            UIManager.put("Button.arc", ConfigUI.BUTTON_ARC);
            UIManager.put("TextComponent.arc", ConfigUI.TEXTFIELD_ARC);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        setUIFont(new FontUIResource(ConfigUI.DefaultFont.BODY));
    }

    public void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
}
