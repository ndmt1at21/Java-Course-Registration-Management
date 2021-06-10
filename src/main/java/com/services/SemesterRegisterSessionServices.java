package com.services;

import java.util.List;
import com.dao.SemesterRegisterSessionReponsitory;
import com.models.SemesterRegisterSession;

public class SemesterRegisterSessionServices {
    private SemesterRegisterSessionReponsitory repo = new SemesterRegisterSessionReponsitory();

    public void createSession(SemesterRegisterSession regSession) {
        repo.createSession(regSession);
    }

    public void updateSession(SemesterRegisterSession regSession) {
        repo.updateSession(regSession);
    }

    public void deleteSession(SemesterRegisterSession regSession) {
        repo.deleteSession(regSession);
    }

    public List<SemesterRegisterSession> getSession(int pageNumber, int limit) {
        return repo.getSession(pageNumber, limit);
    }
}
