package com.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import jakarta.validation.constraints.NotNull;

@Entity
public class CurrentSemester {

    @Id
    private String currentSemesterID;

    @OneToOne
    @NotNull(message = "Semester cannot be null")
    @MapsId
    private Semester semester;

    public CurrentSemester() {
    }

    public CurrentSemester(Semester semester) {
        this.semester = semester;
    }

    public String getCurrentSemesterID() {
        return this.currentSemesterID;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "{" + " currentSemesterID='" + getCurrentSemesterID() + "'" + ", semester='" + getSemester().toString()
                + "'" + "}";
    }
}