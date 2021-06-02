package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.models.Student;
import com.utils.DBFactory;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class StudentResponsitory {
    public void createStudent(Student student) {
        DBFactory.create(student);
    }

    public void updateStudent(Student student) {
        DBFactory.update(student);
    }

    public List<Student> getStudents(int pageNumber, int limit) {
        return DBFactory.paginate(pageNumber, limit, Student.class);
    }

    public Long countAllStudents() {
        return DBFactory.countAll(Student.class);
    }

    public Long countStudentByYear(int year) {
        return DBFactory.runTransaction((Session session) -> {
            Query<Long> query = session.createQuery("select count(*) from Student s where s.startYear = :year",
                    Long.class);
            query.setParameter("year", year);
            return query.uniqueResult();
        });
    }
}
