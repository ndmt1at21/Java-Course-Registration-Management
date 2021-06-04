package com.models;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.constants.Sex;

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

    @OneToMany()
    private List<Student> students;

    @Transient
    private int numberOfStudent;

    @Transient
    private Map<Sex, Integer> numberOfStudentBySex;

    @PostLoad
    private void countNumberOfStudents() {
        this.numberOfStudent = students.size();

        students.forEach(student -> {
            Integer currNum = this.numberOfStudentBySex.get(student.getSex());
            this.numberOfStudentBySex.put(student.getSex(), currNum++);
        });
    }
}