package com.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;

@Entity
public class ShiftTime {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column
    @NotNull(message = "Start shift time cannot empty")
    private Date startTime;

    @Column
    @NotNull(message = "End shift time cannot empty")
    private Date endTime;

    public ShiftTime() {
    }

    public ShiftTime(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return this.id;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", startTime='" + getStartTime() + "'" + ", endTime='" + getEndTime()
                + "'" + "}";
    }
}
