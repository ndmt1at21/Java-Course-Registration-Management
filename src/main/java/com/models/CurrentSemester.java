package com.models;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "current_semester")
public class CurrentSemester {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String currentSemesterID;

    @OneToOne
    @JoinColumn(name = "semester_id")
    @NotNull(message = "Semester cannot be null")
    private Semester semester;
}