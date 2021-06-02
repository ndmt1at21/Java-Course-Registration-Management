package com.crs;

import java.util.Date;

import com.constants.Sex;
import com.models.Student;
import com.services.StudentServices;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.test();
    }

    public void test() {
        StudentServices service = new StudentServices();

        Date date = new Date();

        Student student = new Student("ndmt", "1234567", "Tri", "NguyenDuc", "Cay Cach", date, Sex.FEMALE, 2018);
        service.createStudent(student);
    }
}
