package com.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Semester {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String semesterID;

    @Column
    @NotNull(message = "Name of semester is not specified")
    @Min(value = 1, message = "Name of semester is at least 1")
    @Max(value = 4, message = "Name of semester is less than 5")
    private int name;

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

    public Semester(int name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getName() {
        return this.name;
    }

    public void setName(int name) {
        this.name = name;
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
}