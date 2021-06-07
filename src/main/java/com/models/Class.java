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

    @OneToMany(mappedBy = "studentClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapKeyJoinColumn(name = "student_id")
    private List<Student> students;

    @Transient
    private int numberOfStudent;

    @Transient
    private Map<Sex, Integer> numberOfStudentBySex;

    @Column(name = "created_at")
    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
    private Date createdAt;

    @PostLoad
    private void countNumberOfStudents() {
        this.numberOfStudent = students.size();

        students.forEach(student -> {
            Integer currNum = this.numberOfStudentBySex.get(student.getSex());
            this.numberOfStudentBySex.put(student.getSex(), currNum++);
        });
    }

    public Class() {
        this.students = new ArrayList<Student>();
        this.numberOfStudent = 0;
        this.numberOfStudentBySex = new HashMap<Sex, Integer>();

        for (Sex sex : Sex.values())
            this.numberOfStudentBySex.put(sex, 0);
    }

    public Class(String className) {
        this.className = className;
        this.students = new ArrayList<Student>();
        this.numberOfStudent = 0;
        this.numberOfStudentBySex = new HashMap<Sex, Integer>();

        for (Sex sex : Sex.values())
            this.numberOfStudentBySex.put(sex, 0);
    }

    public Class(String className, List<Student> students) {
        this.className = className;
        this.students = students;
    }
}