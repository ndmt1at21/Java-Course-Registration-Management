package com.crs;

import java.util.Date;

import com.constants.Sex;
import com.dev.LoadDevDataToDB;
import com.models.Student;
import com.services.StudentServices;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.test();
    }

    public void test() {
        LoadDevDataToDB.loadAllDataDev();
    }
}
