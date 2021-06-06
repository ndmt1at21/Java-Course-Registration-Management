package com.models;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
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
    @NotNull
    private ShiftTime shiftTime;

    @Column
    @NotNull
    private int numberOfSlot;

    @OneToOne
    private Subject subject;

    @OneToMany(mappedBy = "course")
    private List<CourseRegistration> registrations;

    @Column
    @CreationTimestamp
    private Date createdAt;

    public Course() {
        registrations = new ArrayList<CourseRegistration>();
    }

    public Course(String teacherName, String departmentName, DayOfWeek dayOfWeek, ShiftTime shiftTime, int numberOfSlot,
            Subject subject, List<CourseRegistration> registrations) {
        this.teacherName = teacherName;
        this.departmentName = departmentName;
        this.dayOfWeek = dayOfWeek;
        this.shiftTime = shiftTime;
        this.numberOfSlot = numberOfSlot;
        this.subject = subject;
        this.registrations = registrations;
    }

    public Course(String courseID, String teacherName, String departmentName, DayOfWeek dayOfWeek, ShiftTime shiftTime,
            int numberOfSlot, Subject subject, Date createdAt) {
        this.courseID = courseID;
        this.teacherName = teacherName;
        this.departmentName = departmentName;
        this.dayOfWeek = dayOfWeek;
        this.shiftTime = shiftTime;
        this.numberOfSlot = numberOfSlot;
        this.subject = subject;
        this.createdAt = createdAt;
        this.registrations = new ArrayList<CourseRegistration>();
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

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getCourseID() {
        return this.courseID;
    }

    public int getNumberOfSlot() {
        return this.numberOfSlot;
    }

    public void setNumberOfSlot(int numberOfSlot) {
        this.numberOfSlot = numberOfSlot;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<CourseRegistration> getRegistration() {
        return this.registrations;
    }

    public void setregistration(List<CourseRegistration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "{" + " courseID='" + getCourseID() + "'" + ", teacherName='" + getTeacherName() + "'"
                + ", departmentName='" + getDepartmentName() + "'" + ", dayOfWeek='" + getDayOfWeek() + "'"
                + ", shiftTime='" + getShiftTime() + "'" + ", numberOfSlot='" + getNumberOfSlot() + "'" + ", subject='"
                + getSubject() + "'" + "}";
    }
}