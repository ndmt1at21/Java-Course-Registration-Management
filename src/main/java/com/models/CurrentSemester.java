package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import jakarta.validation.constraints.NotNull;

@Entity
public class CurrentSemester {

    @Column
    @OneToOne
    @NotNull(message = "Semester cannot be null")
    private Semester semester;

    public CurrentSemester() {
    }

    public CurrentSemester(Semester semester) {
        this.semester = semester;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}