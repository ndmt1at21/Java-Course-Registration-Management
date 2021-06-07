package com.models;

import javax.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "current_semester")
public class CurrentSemester {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String currentSemesterID;

    @OneToOne
    @JoinColumn(name = "semester_id")
    @NotNull(message = "Semester cannot be null")
    private Semester semester;
}