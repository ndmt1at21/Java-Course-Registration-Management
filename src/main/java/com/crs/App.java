package com.crs;

import java.util.Date;
import java.util.List;

import com.constants.Sex;
import com.dev.LoadDevDataToDB;
import com.models.Class;
import com.models.CourseRegistration;
import com.models.Student;
import com.services.ClassServices;
import com.services.CourseRegistrationServices;
import com.services.CourseServices;
import com.services.StudentServices;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.test();

        CourseRegistrationServices ser = new CourseRegistrationServices();
        CourseServices ser2 = new CourseServices();
        List<CourseRegistration> cReg = ser.findByCourseID(ser2.getCourses(1, 1).get(0).getCourseID());

        cReg.forEach(c -> {
            System.out.println(c.getStudent().getUserId());
        });
    }

    public void test() {
        LoadDevDataToDB.loadAllDataDev();
    }
}
