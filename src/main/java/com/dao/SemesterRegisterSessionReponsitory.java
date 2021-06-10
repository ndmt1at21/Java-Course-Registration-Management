package com.dao;

import java.util.List;
import com.models.SemesterRegisterSession;
import com.utils.DBFactory;

public class SemesterRegisterSessionReponsitory {
    public void createSession(SemesterRegisterSession regSession) {
        DBFactory.create(regSession);
    }

    public void updateSession(SemesterRegisterSession regSession) {
        DBFactory.update(regSession);
    }

    public void deleteSession(SemesterRegisterSession regSession) {
        DBFactory.delete(regSession);
    }

    public List<SemesterRegisterSession> getSession(int pageNumber, int limit) {
        List<SemesterRegisterSession> list =
                DBFactory.paginate(pageNumber, limit, SemesterRegisterSession.class);
        return list;
    }
}
