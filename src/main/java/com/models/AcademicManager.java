package com.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.constants.Sex;

import jakarta.validation.constraints.NotNull;

@Entity
public class AcademicManager extends User {
    @Column(unique = true, updatable = false)
    @NotNull(message = "Academic Manager Id cannnot empty")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String academicManagerID;

    public AcademicManager() {
        super();
    }

    public AcademicManager(String username, String password, String firstName, String lastName, String address,
            Date birth, Sex sex) {
        super(username, password, firstName, lastName, address, birth, sex);
    }

    public String getAcademicManagerID() {
        return this.academicManagerID;
    }

    @PrePersist
    private void generateAcademicID() {
        this.academicManagerID = UUID.randomUUID().toString().substring(0, 9).replace("-", "");
    }

    @Override
    public String toString() {
        return super.toString() + "{" + " academicManagerID='" + getAcademicManagerID() + "'" + "}";
    }
}