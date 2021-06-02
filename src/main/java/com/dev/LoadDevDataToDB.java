package com.dev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.constants.Sex;
import com.models.Student;
import com.services.StudentServices;

public class LoadDevDataToDB {
    public static void loadAllDataDev() {
        try {
            loadStudent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadClassData() throws IOException {
        File file = new File("./src/main/java/com/dev/Class.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = null;

        while (true) {
            line = reader.readLine();

            if (line.length() == 0)
                continue;
        }
    }

    private static void loadAcademicManager() {

    }

    private static void loadSemester() {

    }

    private static void loadStudent() throws IOException, ParseException {
        File file = new File("./src/main/java/com/dev/Student.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        StudentServices services = new StudentServices();
        Student student = new Student();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            student.setUsername(reader.readLine());
            student.setPassword(reader.readLine());
            student.setFirstName(reader.readLine());
            student.setLastName(reader.readLine());
            student.setAddress(reader.readLine());
            student.setBirth(formatter.parse(reader.readLine()));

            String sex = reader.readLine();
            switch (sex) {
                case "0":
                    student.setSex(Sex.MALE);
                    break;
                case "1":
                    student.setSex(Sex.FEMALE);
                    break;
                default:
            }
            student.setStartYear(Integer.parseInt(reader.readLine()));

            if (reader.readLine().length() == 0) {
                services.createStudent(student);
            }
        }

    }

    private static void loadSubjectData() {

    }
}
