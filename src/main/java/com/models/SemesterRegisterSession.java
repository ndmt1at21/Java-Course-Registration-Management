package com.models;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "semester_register_session")
public class SemesterRegisterSession {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String semesterRegisterSessionID;

    @OneToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @Column(name = "start_time")
    @NotNull
    private Date startTime;

    @Column(name = "end_time")
    @NotNull
    private Date endTime;
}