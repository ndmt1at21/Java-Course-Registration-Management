package com.dao;

import com.models.CurrentSemester;
import com.utils.DBFactory;

public class CurrentSemesterRepository {
    public void createSemester(CurrentSemester currSemester) {
        if (countAllCurrentSemester() < 2)
            return;

        DBFactory.create(currSemester);
    }

    public void updateSemester(CurrentSemester currSemester) {
        DBFactory.update(currSemester);
    }

    public Long countAllCurrentSemester() {
        return DBFactory.countAll(CurrentSemester.class);
    }
}
