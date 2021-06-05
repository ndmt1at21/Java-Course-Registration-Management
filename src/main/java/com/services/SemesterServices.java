package com.services;

import java.util.List;

import com.dao.SemesterReponsitory;
import com.models.Semester;

public class SemesterServices {

    SemesterReponsitory repo = new SemesterReponsitory();

    public SemesterServices() {
    }

    public void createSemester(Semester semester) {
        repo.createSemester(semester);
    }

    public void updateSemester(Semester semester) {
        repo.updateSemester(semester);
    }

    public void deleteSemester(Semester semester) {
        repo.deleteSemester(semester);
    }

    public List<Semester> getSemesters(int pageNumber, int limit) {
        return repo.getSemesters(pageNumber, limit);
    }

    public Semester getCurrentSemester() {
        CurrentSemesterServices services = new CurrentSemesterServices();
        return services.getCurrentSemester();
    }

    public void setCurrentSemester(Semester semester) {
        CurrentSemesterServices services = new CurrentSemesterServices();
        services.setCurrentSemester(semester);
    }
}
