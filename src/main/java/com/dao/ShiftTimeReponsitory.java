package com.dao;

import java.util.List;

import com.models.ShiftTime;
import com.utils.DBFactory;

public class ShiftTimeReponsitory {
    public void createShiftTime(ShiftTime shiftTime) {
        DBFactory.create(shiftTime);
    }

    public void updateShiftTime(ShiftTime shiftTime) {
        DBFactory.update(shiftTime);
    }

    public void deleteShiftTime(ShiftTime shiftTime) {
        DBFactory.delete(shiftTime);
    }

    public List<ShiftTime> getShiftTimes(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, ShiftTime.class);
    }
}
