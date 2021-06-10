package com.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.constants.Sex;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "class")
public class Class {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String classID;

    @Column(name = "class_name", unique = true)
    @NotNull(message = "Class name cannot null")
    private String className;

    @OneToMany(mappedBy = "studentClass", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "student_id")
    @Builder.Default
    private List<Student> students = new ArrayList<Student>();

    @Transient
    @Builder.Default
    private int numberOfStudent = 0;

    @Transient
    @Builder.Default
    private int numberOfFemale = 0;

    @Transient
    @Builder.Default
    private int numberOfMale = 0;

    @Column
    @OneToMany(mappedBy = "courseID", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Course> courses = new ArrayList<>();


    @Column(name = "created_at")
    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
    private Date createdAt;

    public Class(String className) {
        this.className = className;
    }

    public Class(String className, List<Student> students) {
        this.className = className;
        this.students = students;
    }
}
