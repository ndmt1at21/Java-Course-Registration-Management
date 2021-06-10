package com.services;

import java.util.List;

import com.dao.CourseReponsitory;
import com.models.Course;

public class CourseServices {

    CourseReponsitory repo = new CourseReponsitory();

    public CourseServices() {}

    public void createCourse(Course course) {
        repo.createCourse(course);
    }

    public void updateCourse(Course course) {
        repo.updateCourse(course);
    }

    public void deleteCourse(Course course) {
        repo.deleteCourse(course);
    }

    public List<Course> getCourses(int pageNumber, int limit) {
        return repo.getCourses(pageNumber, limit);
    }
}
