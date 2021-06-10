package com.models;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import jakarta.validation.ValidationException;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @Column(name = "start_time")
    @NotNull
    private Date startTime;

    @Column(name = "end_time")
    @NotNull
    private Date endTime;

    @PrePersist
    @PreUpdate
    private void validateSesstionTime() {

        Date startSem = semester.getStartDate();
        Date endSem = semester.getEndDate();

        if (startTime.getTime() < startSem.getTime() && endTime.getTime() < endTime.getTime()) {
            return;
        }

        throw new ValidationException("Time of session must between " + startSem.toString()
                + " and " + endSem.toString());
    }
}
