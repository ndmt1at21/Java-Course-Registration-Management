package com.models;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String courseID;

    @Column(name = "teacher_name")
    @NotNull
    @Size(min = 5, max = 50, message = "Length of teacher name is too short or too long")
    private String teacherName;

    @Column(name = "department_name")
    @NotNull(message = "Department's name cannot empty")
    private String departmentName;

    @Column(name = "day_of_week")
    @NotNull(message = "Day of week cannot empty")
    private DayOfWeek dayOfWeek;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shift_time")
    @NotNull
    private ShiftTime shiftTime;

    @Column(name = "number_of_slot")
    @NotNull(message = "Number of slot cannot be null")
    private int numberOfSlot;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    private Class classObj;

    @OneToMany(mappedBy = "course")
    private List<CourseRegistration> registrations;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @Column(name = "created_at")
    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
    private Date createdAt;
}
