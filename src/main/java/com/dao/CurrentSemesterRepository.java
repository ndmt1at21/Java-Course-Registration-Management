package com.dao;

import java.util.List;

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

    public CurrentSemester getCurrentSemester() {
        List<CurrentSemester> tmp = DBFactory.paginate(1, 1, CurrentSemester.class);

        if (tmp.size() == 0)
            return null;
        return tmp.get(0);
    }

    private Long countAllCurrentSemester() {
        return DBFactory.countAll(CurrentSemester.class);
    }
}
