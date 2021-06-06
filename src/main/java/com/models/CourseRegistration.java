package com.models;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.services.CourseRegistrationServices;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.validation.ValidationException;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
        validateDuplicateTimetable();
        validateLimitCourse();
    }

    private void validateDuplicateTimetable() throws ValidationException {
        if (student == null || course == null)
            return;

        CourseRegistrationServices services = new CourseRegistrationServices();
        List<CourseRegistration> cRegs = services.findByStudentID(student.getUserID());

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

    private void validateLimitCourse() throws ValidationException {
        if (student == null || course == null)
            return;

        CourseRegistrationServices services = new CourseRegistrationServices();
        List<CourseRegistration> cRegs = services.findByStudentID(student.getUserID());

        if (cRegs.size() == 8) {
            throw new ValidationException("The maximum number of course that you ca register is 8");
        }
    }
}