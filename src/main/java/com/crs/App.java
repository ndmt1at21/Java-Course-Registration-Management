package com.crs;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import com.constants.ConfigUI;
import com.formdev.flatlaf.IntelliJTheme;
import com.views.TestFrame;

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
        // LoadDevDataToDB.loadAllDataDev();
        initTheme();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TestFrame tf = new TestFrame();
                tf.setVisible(true);
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

        loadFont();
        setUIFont(new FontUIResource(ConfigUI.DefaultFont.BODY));
    }

    public void loadFont() {
        try {
            Font f = Font.createFont(Font.TRUETYPE_FONT,
                    new File("./src/main/resources/fonts/Roboto-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(f);
        } catch (Exception e) {
            System.out.println("djhdfhjjh");
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
