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
@Table(name = "course_registration", uniqueConstraints = @UniqueConstraint(columnNames = { "student_id", "course_id" }))
public class CourseRegistration {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String courseRegistrationID;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "registered_at", nullable = false)
    @CreationTimestamp
    @Setter(value = AccessLevel.NONE)
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