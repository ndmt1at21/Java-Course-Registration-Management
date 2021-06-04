package com.dao;

import java.util.List;

import com.models.Class;
import com.utils.DBFactory;

public class ClassReponsitory {
    public void createClass(Class classObj) {
        DBFactory.create(classObj);
    }

    public void updateClass(Class classObj) {
        DBFactory.update(classObj);
    }

    public void deleteClass(Class classObj) {
        DBFactory.delete(classObj);
    }

    public List<Class> getClass(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, Class.class);
    }
}
