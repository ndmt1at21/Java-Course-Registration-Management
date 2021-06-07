package com.crs;

import java.util.Date;
import java.util.List;

import javax.swing.UIManager;

import com.constants.ConfigUI;
import com.constants.Sex;
import com.dao.ConfiguarationReponsitory;
import com.dev.LoadDevDataToDB;
import com.formdev.flatlaf.FlatLightLaf;
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
        LoadDevDataToDB.loadAllDataDev();
        // initTheme();
        // setUIFont(ConfigUI.DefaultFont.body);
        // Login login = new Login();
        // login.setVisible(true);
    }

    public void initTheme() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

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
