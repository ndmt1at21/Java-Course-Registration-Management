package com.services;

import java.util.List;

import com.dao.AcademicManagerReponsitory;
import com.models.AcademicManager;

public class AcademicManagerServices {

    AcademicManagerReponsitory repo = new AcademicManagerReponsitory();

    public AcademicManagerServices() {}

    public void createAcademicManager(AcademicManager obj) {
        repo.createAcademicManager(obj);
    }

    public void updateAcademicManager(AcademicManager obj) {
        repo.updateAcademicManager(obj);
    }

    public void deleteAcademicManager(AcademicManager obj) {
        repo.deleteAcademicManager(obj);
    }

    public List<AcademicManager> getAcademicManagers(int pageNumber, int limit) {
        return repo.getAcademicManagers(pageNumber, limit);
    }
}
