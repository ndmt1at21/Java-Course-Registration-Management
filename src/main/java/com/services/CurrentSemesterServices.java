package com.services;

import com.dao.CurrentSemesterRepository;
import com.models.CurrentSemester;

public class CurrentSemesterServices {

    CurrentSemesterRepository repo = new CurrentSemesterRepository();

    public CurrentSemesterServices() {
    }

    public void createSemester(CurrentSemester currSemester) {
        repo.createSemester(currSemester);
    }

    public void updateSemester(CurrentSemester currSemester) {
        repo.updateSemester(currSemester);
    }

    public CurrentSemester getCurrentSemester() {
        return repo.getCurrentSemester();
    }
}
