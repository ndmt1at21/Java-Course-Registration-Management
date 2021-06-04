package com.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import jakarta.validation.constraints.NotNull;

@Entity
public class SemesterRegisterSession {
    @Column
    @OneToOne()
    private Semester semester;

    @Column
    @NotNull
    private Date startDate;

    @Column
    @NotNull
    private Date endDate;

    public SemesterRegisterSession() {
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SemesterRegisterSession(Semester semester, Date startDate, Date endDate) {
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}