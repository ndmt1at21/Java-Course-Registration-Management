package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.models.Student;
import com.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class StudentResponsitory {
    public void createStudent(Student student) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(student);

        session.getTransaction().commit();
        session.close();
    }

    public List<Student> getStudents(int pageNumber, int limit) {
        if (pageNumber < 0 || limit < 0) {
            return new ArrayList<Student>();
        }

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Student> query = session.createQuery("select * from student", Student.class);
        query.setFirstResult((pageNumber - 1) * limit);
        query.setMaxResults(limit);

        session.getTransaction().commit();
        session.close();

        return query.list();
    }

    public Long countAllStudents() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Long> query = session.createQuery("select count(*) from Student", Long.class);
        Long count = query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return count;
    }

    public Long countStudentByYear(int year) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Long> query = session.createQuery("select count(*) from Student s where s.startYear = :year", Long.class);
        query.setParameter("year", year);
        Long count = query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return count;
    }
}
