package com.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.constants.Sex;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;

@Entity
public class Class {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String classID;

    @Column(unique = true)
    @NotNull(message = "Class name cannot null")
    private String className;

    @OneToMany(mappedBy = "studentClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    @Transient
    private int numberOfStudent;

    @Transient
    private Map<Sex, Integer> numberOfStudentBySex;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @PostLoad
    private void countNumberOfStudents() {
        this.numberOfStudent = students.size();

        students.forEach(student -> {
            Integer currNum = this.numberOfStudentBySex.get(student.getSex());
            this.numberOfStudentBySex.put(student.getSex(), currNum++);
        });
    }

    public Class() {
        this.students = new ArrayList<Student>();
    }

    public Class(String className) {
        this.className = className;
        this.students = new ArrayList<Student>();
    }

    public Class(String classID, String className, List<Student> students) {
        this.className = className;
        this.students = students;
    }

    public String getClassID() {
        return this.classID;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getNumberOfStudent() {
        return this.numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public Map<Sex, Integer> getNumberOfStudentBySex() {
        return this.numberOfStudentBySex;
    }

    public void setNumberOfStudentBySex(Map<Sex, Integer> numberOfStudentBySex) {
        this.numberOfStudentBySex = numberOfStudentBySex;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public String toString() {
        return "{" + " classID='" + getClassID() + "'" + ", className='" + getClassName() + "'" + ", students='"
                + getStudents() + "'" + ", numberOfStudent='" + getNumberOfStudent() + "'" + ", numberOfStudentBySex='"
                + getNumberOfStudentBySex() + "'" + ", createdAt='" + getCreatedAt() + "'" + "}";
    }

}