package com.dao;

import java.util.List;

import com.models.Semester;
import com.utils.DBFactory;

public class SemesterReponsitory {
    public void createSemester(Semester semester) {
        DBFactory.create(semester);
    }

    public void updateSemester(Semester semester) {
        DBFactory.update(semester);
    }

    public void deleteSemester(Semester semester) {
        DBFactory.delete(semester);
    }

    public List<Semester> getSemesters(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, Semester.class);
    }
}
