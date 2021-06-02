package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import jakarta.validation.constraints.NotNull;

@Entity
public class AcademicManager {
    @Column(unique = true, updatable = false)
    @NotNull(message = "Academic Manager Id cannnot empty")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String academicManagerID;

    public String getAcademicManagerID() {
        return this.academicManagerID;
    }
}