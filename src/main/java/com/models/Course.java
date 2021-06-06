package com.models;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Course {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
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

    @OneToOne
    @JoinColumn(name = "shift_time")
    @NotNull
    private ShiftTime shiftTime;

    @Column(name = "number_of_slot")
    @NotNull(message = "Number of slot cannot be null")
    private int numberOfSlot;

    @OneToOne
    @JoinColumn(name = "subject")
    private Subject subject;

    @OneToMany(mappedBy = "course")
    private List<CourseRegistration> registrations;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}