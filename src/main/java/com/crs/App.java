package com.crs;

import java.util.Date;
import java.util.List;

import com.constants.Sex;
import com.dev.LoadDevDataToDB;
import com.models.Class;
import com.models.Student;
import com.services.ClassServices;
import com.services.StudentServices;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.test();

        ClassServices ser = new ClassServices();
        List<Class> allClass = ser.getClass(1, 10);

        allClass.forEach(classObj -> {
            System.out.println(classObj);
            classObj.getStudents().forEach(student -> {
                System.out.println(student);
            });
        });
    }

    public void test() {
        LoadDevDataToDB.loadAllDataDev();
    }
}
