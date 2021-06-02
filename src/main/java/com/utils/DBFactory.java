package com.utils;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import com.constants.CommandRunInTransation;

public class DBFactory {
    public static <T> void create(T data) {
        runTransaction((Session session) -> {
            session.save(data);
            return null;
        });
    }

    public static <T> List<T> paginate(int page, int limit, Class<T> modelClass) {
        return runTransaction((session) -> {
            int firstIndexRow = (page - 1) * limit;

            if (page < 0 || limit < 0 || firstIndexRow > countAll(modelClass)) {
                return new ArrayList<T>();
            }

            String queryStr = "select * from " + modelClass.getName();

            Query<T> query = session.createQuery(queryStr, modelClass);
            query.setFirstResult(firstIndexRow);
            query.setMaxResults(limit);

            return query.list();
        });
    }

    public static <T> void update(T data) {
        runTransaction((Session session) -> {
            session.update(data);
            return null;
        });
    }

    public static <T> void delete(T data) {
        runTransaction((Session session) -> {
            session.delete(data);
            return null;
        });
    }

    public static <T> void deleteByID(Class<T> modelClass, int id) {
        runTransaction((Session session) -> {
            T obj = session.get(modelClass, id);

            if (obj == null)
                return null;
            session.delete(obj);
            return null;
        });
    }

    public static <T> void deleteByID(Class<T> modelClass, String id) {
        runTransaction(session -> {
            T obj = session.get(modelClass, id);

            if (obj == null)
                return null;
            session.delete(obj);
            return null;
        });
    }

    public static <T> Long countAll(Class<T> modelClass) {
        String queryStr = "select count(*) from " + modelClass.getName();

        return runTransaction((Session session) -> {
            Query<Long> query = session.createQuery(queryStr, Long.class);
            Long count = query.uniqueResult();
            return count;
        });
    }

    /**
     * 
     * @param <T>      type return
     * @param actionFn action function in transaction
     * @return result when query to database
     */
    public static <T> T runTransaction(CommandRunInTransation<T> actionFn) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        T result = actionFn.runWith(session);

        session.getTransaction().commit();
        session.close();

        return result;
    }
}
