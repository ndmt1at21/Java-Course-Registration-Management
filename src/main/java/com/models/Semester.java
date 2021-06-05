package com.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.constants.SemesterNo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Semester {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String semesterID;

    @Column
    @NotNull(message = "Name of semester is not specified")
    @Min(value = 1, message = "Name of semester is at least 1")
    @Max(value = 3, message = "Name of semester is less than 4")
    private SemesterNo semNo;

    @Column
    @NotNull(message = "Start date of semester is not specified")
    private Date startDate;

    @Column
    @NotNull(message = "End date of semester is not specified")
    private Date endDate;

    @Column
    @CreationTimestamp
    private Date createdAt;

    public Semester() {
    }

    public Semester(SemesterNo semNo, Date startDate, Date endDate) {
        this.semNo = semNo;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SemesterNo getName() {
        return this.semNo;
    }

    public void setName(SemesterNo semNo) {
        this.semNo = semNo;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getSemesterID() {
        return this.semesterID;
    }

    @Override
    public String toString() {
        return "{" + " semesterID='" + getSemesterID() + "'" + ", semNo='" + getName() + "'" + ", startDate='"
                + getStartDate() + "'" + ", endDate='" + getEndDate() + "'" + ", createdAt='" + getCreatedAt() + "'"
                + "}";
    }
}