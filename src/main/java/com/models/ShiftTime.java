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
    private int id;

    @Column
    @NotNull(message = "Start shift time cannot empty")
    private Date startTime;

    @Column
    @NotNull(message = "End shift time cannot empty")
    private Date endTime;
}
