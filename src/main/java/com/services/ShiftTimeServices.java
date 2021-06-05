package com.services;

import java.util.List;

import com.dao.ShiftTimeReponsitory;
import com.models.ShiftTime;

public class ShiftTimeServices {

    ShiftTimeReponsitory repo = new ShiftTimeReponsitory();

    public ShiftTimeServices() {
    }

    public void createShiftTime(ShiftTime shiftTime) {
        repo.createShiftTime(shiftTime);
    }

    public void updateShiftTime(ShiftTime shiftTime) {
        repo.updateShiftTime(shiftTime);
    }

    public void deleteShiftTime(ShiftTime shiftTime) {
        repo.deleteShiftTime(shiftTime);
    }

    public List<ShiftTime> getShiftTImes(int pageNumber, int limit) {
        return repo.getShiftTimes(pageNumber, limit);
    }
}
