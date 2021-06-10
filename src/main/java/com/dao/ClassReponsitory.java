package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.constants.Sex;
import com.models.Class;
import com.models.Student;
import com.utils.DBFactory;

import org.hibernate.Session;
import org.hibernate.query.Query;

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
        List<Class> list = DBFactory.paginate(pageNumber, limit, Class.class);
        return list;
    }

    public List<Class> getAllClassWithStudents() {
        String queryStr = "from " + Class.class.getSimpleName();

        return DBFactory.runTransaction((Session session) -> {
            Query<Class> query = session.createQuery(queryStr, Class.class);
            List<Class> list = query.list();
            list.forEach(classObj -> {
                List<Student> students = classObj.getStudents();

                classObj.setNumberOfStudent(students.size());

                students.forEach(student -> {
                    if (student.getSex() == Sex.FEMALE) {
                        classObj.setNumberOfFemale(classObj.getNumberOfFemale() + 1);
                    } else {
                        classObj.setNumberOfMale(classObj.getNumberOfMale() + 1);
                    }
                });
            });

            return list;
        });
    }
}
