package com.services;

import java.util.List;

import com.dao.StudentReponsitory;
import com.models.Course;
import com.models.CourseRegistration;
import com.models.Student;

public class StudentServices {
    StudentReponsitory repo = new StudentReponsitory();

    public StudentServices() {
    }

    public List<Student> getStudents(int pageNumber, int limit) {
        return repo.getStudents(pageNumber, limit);
    }

    public Long countAllStudents() {
        return repo.countAllStudents();
    }

    public Long countStudentByYear(int year) {
        return repo.countStudentByYear(year);
    }

    public void createStudent(Student student) {
        repo.createStudent(student);
    }

    public void registerCourse(Student student, Course course) {

    }
}
