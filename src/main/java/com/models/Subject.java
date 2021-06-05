package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Subject {

    @Id
    @Column
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(unique = true)
    @NotNull(message = "Subject id cannot empty")
    private String subjectID;

    @Column
    @NotNull(message = "Subject's name cannot empty")
    @Size(max = 100, message = "Subject name is too long")
    private String subjectName;

    @Column
    @Positive(message = "Number of credit cannot be negative")
    private int credits;

    public Subject() {
    }

    public Subject(String subjectID, String subjectName, int credits) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    public String getId() {
        return this.id;
    }

    public String getSubjectID() {
        return this.subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", subjectID='" + getSubjectID() + "'" + ", subjectName='"
                + getSubjectName() + "'" + ", credits='" + getCredits() + "'" + "}";
    }
}