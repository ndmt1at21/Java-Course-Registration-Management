package com.models;

import java.util.Date;

import javax.persistence.*;

import com.constants.SemesterNo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "semester")
public class Semester {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String semesterID;

    @Column(name = "semester_no")
    @NotNull(message = "Name of semester is not specified")
    @Min(value = 1, message = "Name of semester is at least 1")
    @Max(value = 3, message = "Name of semester is less than 4")
    private SemesterNo semNo;

    @Column(name = "start_date")
    @NotNull(message = "Start date of semester is not specified")
    private Date startDate;

    @Column(name = "end_date")
    @NotNull(message = "End date of semester is not specified")
    private Date endDate;

    @Column(name = "created_at")
    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
    private Date createdAt;
}