package com.dao;

import java.util.List;

import com.models.AcademicManager;
import com.models.Course;
import com.utils.DBFactory;

public class AcademicManagerReponsitory {

    public void createAcademicManager(Course course) {
        DBFactory.create(course);
    }

    public void updateAcademicManager(Course course) {
        DBFactory.update(course);
    }

    public void deleteAcademicManager(Course course) {
        DBFactory.delete(course);
    }

    public List<AcademicManager> getAcademicManagers(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, AcademicManager.class);
    }
}
