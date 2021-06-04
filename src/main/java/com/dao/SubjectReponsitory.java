package com.dao;

import java.util.List;

import com.models.Subject;
import com.utils.DBFactory;

public class SubjectReponsitory {
    public void createSubject(Subject subject) {
        DBFactory.create(subject);
    }

    public void updateSubject(Subject subject) {
        DBFactory.update(subject);
    }

    public void deleteSubject(Subject subject) {
        DBFactory.delete(subject);
    }

    public List<Subject> getSubjects(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, Subject.class);
    }
}
