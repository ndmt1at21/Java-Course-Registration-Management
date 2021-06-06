package com.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import com.constants.Sex;
import com.services.StudentServices;

@Entity
public class Student extends User {

    @Column(unique = true, updatable = false)
    @NotNull
    private String studentID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classID")
    private Class studentClass;

    @Column
    @NotNull
    @Min(1900)
    @Max(3000)
    private int startYear;

    @OneToMany(mappedBy = "student")
    private List<CourseRegistration> registrations;

    @PrePersist
    private void generateStudentID() {
        String year = String.valueOf(this.getStartYear());
        String prefix = year.substring(year.length() - 2);

        StudentServices studentServices = new StudentServices();
        Long countStudent = studentServices.countStudentByYear(this.getStartYear());

        this.studentID = prefix + String.format("%04d", countStudent);
    }

    public Student() {
        this.registrations = new ArrayList<CourseRegistration>();
    }

    public Student(String username, String password, String firstName, String lastName, String address, Date birth,
            Sex sex, int startYear, Class studentClass) {
        super(username, password, firstName, lastName, address, birth, sex);
        this.studentClass = studentClass;
        this.startYear = startYear;
        this.registrations = new ArrayList<CourseRegistration>();
    }

    public Student(String username, String password, String firstName, String lastName, String address, Date birth,
            Sex sex, int startYear, Class studentClass, List<CourseRegistration> registrations) {
        super(username, password, firstName, lastName, address, birth, sex);
        this.studentClass = studentClass;
        this.startYear = startYear;
        this.registrations = registrations;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public Class getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(Class studentClass) {
        this.studentClass = studentClass;
    }

    public List<CourseRegistration> getRegistrations() {
        return this.registrations;
    }

    public void setRegistrations(List<CourseRegistration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "{" + super.toString() + " studentID='" + getStudentID() + "'" + ", studentClass='"
                + getStudentClass().getClassName() + "'" + ", startYear='" + getStartYear() + "'" + "}";
    }
}
