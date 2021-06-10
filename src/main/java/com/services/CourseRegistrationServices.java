package com.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dao.CourseRegistrationReponsitory;
import com.models.Course;
import com.models.CourseRegistration;
import com.models.ShiftTime;
import com.models.Student;

public class CourseRegistrationServices {
  private CourseRegistrationReponsitory repo = new CourseRegistrationReponsitory();

  public CourseRegistrationServices() {}

  public void createCourseRegistration(CourseRegistration courseRegistration) {
    repo.createCourseRegistration(courseRegistration);
  }

  public void createCourseRegistration(Student student, Course course) {
    CourseRegistration cReg = CourseRegistration.builder().course(course).student(student).build();
    repo.createCourseRegistration(cReg);
  }

  public void updateCourseRegistration(CourseRegistration courseRegistration) {
    repo.updateCourseRegistration(courseRegistration);
  }

  public void deleteCourseRegistration(CourseRegistration courseRegistration) {
    repo.deleteCourseRegistration(courseRegistration);
  }

  public List<CourseRegistration> findByStudentID(String id) {
    return repo.find("student.userID", id);
  }

  public List<ShiftTime> getCoursesTimeOfStudent(String id) {
    List<ShiftTime> courseTimes = new ArrayList<ShiftTime>();
    findByStudentID(id).forEach(student -> {
      courseTimes.add(student.getCourse().getShiftTime());
    });

    return courseTimes;
  }

  public List<CourseRegistration> findByCourseID(String id) {
    return repo.find("course.courseID", id);
  }

  public List<CourseRegistration> getCourseRegistrations(int pageNumber, int limit) {
    return repo.getCourseRegistrations(pageNumber, limit);
  }
}
