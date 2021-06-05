package com.services;

import com.dao.CurrentSemesterRepository;
import com.models.CurrentSemester;
import com.models.Semester;

public class CurrentSemesterServices {

    private CurrentSemesterRepository repo = new CurrentSemesterRepository();

    public CurrentSemesterServices() {
    }

    public void setCurrentSemester(Semester semester) {
        if (repo.countAllCurrentSemester() == 0) {
            repo.createSemester(new CurrentSemester(semester));
            return;
        }

        repo.updateSemester(new CurrentSemester(semester));
    }

    public Semester getCurrentSemester() {
        return repo.getCurrentSemester().getSemester();
    }
}
