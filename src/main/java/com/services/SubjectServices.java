package com.services;

import java.util.List;

import com.dao.SubjectReponsitory;
import com.models.Subject;

public class SubjectServices {
    SubjectReponsitory repo = new SubjectReponsitory();

    public SubjectServices() {
    }

    public void createSubject(Subject subject) {
        repo.createSubject(subject);
    }

    public void updateSubject(Subject subject) {
        repo.updateSubject(subject);
    }

    public void deleteSubject(Subject subject) {
        repo.deleteSubject(subject);
    }

    public List<Subject> getSubjects(int pageNumber, int limit) {
        return repo.getSubjects(pageNumber, limit);
    }
}