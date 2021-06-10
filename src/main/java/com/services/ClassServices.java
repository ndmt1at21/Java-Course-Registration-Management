package com.services;

import java.util.List;

import com.dao.ClassReponsitory;
import com.models.Class;

public class ClassServices {

    ClassReponsitory repo = new ClassReponsitory();

    public ClassServices() {}

    public void createClass(Class classObj) {
        repo.createClass(classObj);
    }

    public void updateClass(Class classObj) {
        repo.updateClass(classObj);
    }

    public void deleteClass(Class classObj) {
        repo.deleteClass(classObj);
    }

    public List<Class> getAllClassWithStudents() {
        return repo.getAllClassWithStudents();
    }

    public List<Class> getClass(int pageNumber, int limit) {
        List<Class> list = repo.getClass(pageNumber, limit);
        return list;
    }
}
