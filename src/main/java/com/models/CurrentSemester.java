package com.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import jakarta.validation.constraints.NotNull;

@Entity
public class CurrentSemester extends Semester {

    public CurrentSemester() {
        super();
    }

    public CurrentSemester(int name, Date startDate, Date endDate) {
        super(name, startDate, endDate);
    }

    @Column
    @NotNull(message = "State of current semester is not null")
    private boolean isActive;
}