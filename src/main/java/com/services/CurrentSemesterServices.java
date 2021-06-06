package com.services;

import com.dao.CurrentSemesterRepository;
import com.models.CurrentSemester;
import com.models.Semester;

public class CurrentSemesterServices {

    private CurrentSemesterRepository repo = new CurrentSemesterRepository();

    public CurrentSemesterServices() {}

    public void setCurrentSemester(Semester semester) {
        CurrentSemester currentSemester = CurrentSemester.builder().semester(semester).build();
        if (repo.countAllCurrentSemester() == 0) {
            repo.createSemester(currentSemester);
            return;
        }

        repo.updateSemester(currentSemester);
    }

    public Semester getCurrentSemester() {
        return repo.getCurrentSemester().getSemester();
    }
}
