package com.models;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String subjectID;

    @Column(name = "subject_code", unique = true)
    @NotNull(message = "Subject id cannot empty")
    private String subjectCode;

    @Column(name = "subject_name")
    @NotNull(message = "Subject's name cannot empty")
    @Size(max = 100, message = "Subject name is too long")
    private String subjectName;

    @Column(name = "number_of_credits")
    @Positive(message = "Number of credit cannot be negative")
    private int credits;
}
