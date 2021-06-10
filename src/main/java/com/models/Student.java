package com.models;

import java.util.Currency;
import java.util.List;

import javax.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import com.services.StudentServices;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "student")
public class Student extends User {

    @Override
    public String toString() {
        return "";
    }

    @Column(name = "student_id", unique = true, updatable = false)
    @Setter(value = AccessLevel.NONE)
    private String studentID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    @NotNull
    private Class studentClass;

    @Column
    @NotNull
    @Min(1900)
    @Max(3000)
    private int startYear;

    @OneToMany(mappedBy = "student")
    @MapKeyColumn(name = "course_registration_id")
    private List<CourseRegistration> registrations;

    @PrePersist
    private void generateStudentIDAndUsername() {
        String year = String.valueOf(this.getStartYear());
        String prefix = year.substring(year.length() - 2);

        StudentServices studentServices = new StudentServices();
        Long countStudent = studentServices.countStudentByYear(this.getStartYear());

        this.studentID = prefix + String.format("%04d", countStudent);
        this.setUsername(this.studentID);
    }
}
