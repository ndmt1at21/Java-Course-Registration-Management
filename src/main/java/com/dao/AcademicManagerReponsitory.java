package com.dao;

import java.util.List;

import com.models.AcademicManager;
import com.utils.DBFactory;

public class AcademicManagerReponsitory {

    public void createAcademicManager(AcademicManager obj) {
        DBFactory.create(obj);
    }

    public void updateAcademicManager(AcademicManager obj) {
        DBFactory.update(obj);
    }

    public void deleteAcademicManager(AcademicManager obj) {
        DBFactory.delete(obj);
    }

    public List<AcademicManager> getAcademicManagers(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, AcademicManager.class);
    }
}
