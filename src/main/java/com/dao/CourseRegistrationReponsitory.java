package com.dao;

import java.util.List;

import com.models.CourseRegistration;
import com.utils.DBFactory;

public class CourseRegistrationReponsitory {
    public void createCourseRegistration(CourseRegistration courseRegistration) {
        DBFactory.create(courseRegistration);
    }

    public void updateCourseRegistration(CourseRegistration courseRegistration) {
        DBFactory.update(courseRegistration);
    }

    public void deleteCourseRegistration(CourseRegistration courseRegistration) {
        DBFactory.delete(courseRegistration);
    }

    public List<CourseRegistration> find(String property, String value) {
        return DBFactory.find(CourseRegistration.class, property, value);
    }

    public List<CourseRegistration> getCourseRegistrations(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, CourseRegistration.class);
    }
}
