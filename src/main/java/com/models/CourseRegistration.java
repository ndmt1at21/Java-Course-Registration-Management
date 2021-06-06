package com.models;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.services.CourseRegistrationServices;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.ValidationException;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "studentID", "courseID" }))
public class CourseRegistration {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String courseRegistrationID;

    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course course;

    @Column(nullable = false)
    @CreationTimestamp
    private Date registeredAt;

    @PrePersist
    @PreUpdate
    private void validatorCourseRegistration() throws ValidationException {
        checkDuplicateTimetable();
        checkLimitCourse();
    }

    public CourseRegistration() {
    }

    public CourseRegistration(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public String getCourseRegistrationID() {
        return this.courseRegistrationID;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getRegisteredAt() {
        return this.registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    private void checkDuplicateTimetable() throws ValidationException {
        if (student == null || course == null)
            return;

        CourseRegistrationServices services = new CourseRegistrationServices();
        List<CourseRegistration> cRegs = services.findByStudentID(student.getUserId());

        Date startShift = course.getShiftTime().getStartTime();
        DayOfWeek dayOfWeek = course.getDayOfWeek();

        cRegs.forEach(cReg -> {
            Course cRegCourse = cReg.getCourse();

            if (cRegCourse.getDayOfWeek().equals(dayOfWeek)
                    && cRegCourse.getShiftTime().getStartTime().equals(startShift)) {
                throw new ValidationException("Cannot register two course in same timetable");
            }
        });
    }

    private void checkLimitCourse() throws ValidationException {
        if (student == null || course == null)
            return;

        CourseRegistrationServices services = new CourseRegistrationServices();
        List<CourseRegistration> cRegs = services.findByStudentID(student.getUserId());

        if (cRegs.size() == 8) {
            throw new ValidationException("The maximum number of course that you ca register is 8");
        }
    }
}