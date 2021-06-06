package com.utils;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
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

            String queryStr = "from " + modelClass.getSimpleName();

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

    public static <T, U extends Serializable> T findByID(Class<T> modelClass, U id) {
        return runTransaction((Session session) -> {
            T obj = session.get(modelClass, id);
            return obj;
        });
    }

    public static <T, U> List<T> find(Class<T> modelClass, String property, U value) {
        return runTransaction((Session session) -> {
            String className = modelClass.getSimpleName();
            String queryStr = "select s from " + className + " s where " + "s." + property + " = :value";
            Query<T> query = session.createQuery(queryStr, modelClass);
            query.setParameter("value", value.toString());

            return query.list();
        });
    }

    public static <T> List<T> groupBy(Class<T> modelClass, String property) {
        return runTransaction((Session session) -> {
            String className = modelClass.getSimpleName();
            String sql = "from " + className + " group by " + className + "." + property;
            Query<T> query = session.createQuery(sql, modelClass);

            return query.list();
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
