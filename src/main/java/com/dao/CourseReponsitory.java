package com.dao;

import java.util.List;

import com.models.Course;
import com.utils.DBFactory;

public class CourseReponsitory {
    public void createCourse(Course course) {
        DBFactory.create(course);
    }

    public void updateCourse(Course course) {
        DBFactory.update(course);
    }

    public void deleteCourse(Course course) {
        DBFactory.delete(course);
    }

    public List<Course> getCourses(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, Course.class);
    }
}
