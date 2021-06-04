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
}