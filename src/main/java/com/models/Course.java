package com.models;

import java.time.DayOfWeek;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Course {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String courseID;

    @Column
    @NotNull
    @Size(min = 5, max = 50, message = "Length of teacher name is too short or too long")
    private String teacherName;

    @Column
    @NotNull(message = "Department's name cannot empty")
    private String departmentName;

    @Column
    @NotNull(message = "Day of week cannot empty")
    private DayOfWeek dayOfWeek;

    @OneToOne
    @Column
    @NotNull
    private ShiftTime shiftTime;

    @Column
    @NotNull
    private int numberOfSlot;

    @OneToOne
    private Subject subject;

    public Course() {
    }

    public Course(String teacherName, String departmentName, DayOfWeek dayOfWeek, ShiftTime shiftTime) {
        this.teacherName = teacherName;
        this.departmentName = departmentName;
        this.dayOfWeek = dayOfWeek;
        this.shiftTime = shiftTime;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public ShiftTime getShiftTime() {
        return this.shiftTime;
    }

    public void setShiftTime(ShiftTime shiftTime) {
        this.shiftTime = shiftTime;
    }
}