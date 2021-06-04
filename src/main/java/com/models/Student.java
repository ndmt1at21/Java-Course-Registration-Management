package com.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import jakarta.validation.constraints.NotNull;

import com.constants.Sex;
import com.services.StudentServices;

@Entity
public class Student extends User {

    @Column(unique = true, updatable = false)
    @NotNull
    private String studentID;

    @NotNull
    @OneToOne
    private Class studentClass;

    public Student() {
    }

    public Student(String username, String password, String firstName, String lastName, String address, Date birth,
            Sex sex, int startYear, Class studentClass) {
        super(username, password, firstName, lastName, address, birth, sex, startYear);
        this.studentClass = studentClass;
    }

    public Class getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(Class studentClass) {
        this.studentClass = studentClass;
    }

    @PrePersist
    private void generateStudentID() {
        String year = String.valueOf(this.getStartYear());
        String prefix = year.substring(year.length() - 2);

        StudentServices studentServices = new StudentServices();
        Long countStudent = studentServices.countStudentByYear(this.getStartYear());

        this.studentID = prefix + String.format("%04d", countStudent);
    }
}
