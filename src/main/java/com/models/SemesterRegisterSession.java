package com.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;

@Entity
public class SemesterRegisterSession {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String semesterRegisterSessionID;

    @OneToOne()
    private Semester semester;

    @Column
    @NotNull
    private Date startTime;

    @Column
    @NotNull
    private Date endTime;

    public SemesterRegisterSession() {
    }

    public SemesterRegisterSession(Semester semester, Date startTime, Date endTime) {
        this.semester = semester;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getSemesterRegisterSessionID() {
        return this.semesterRegisterSessionID;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "{" + " semesterRegisterSessionID='" + getSemesterRegisterSessionID() + "'" + ", semester='"
                + getSemester() + "'" + ", startTime='" + getStartTime() + "'" + ", endTime='" + getEndTime() + "'"
                + "}";
    }
}