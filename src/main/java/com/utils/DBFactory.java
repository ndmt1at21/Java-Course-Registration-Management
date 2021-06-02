package com.utils;

import org.hibernate.Session;

public class DBFactory {
    static <T> void create(T data) {
        runTransaction(session -> session.save(data));
    }

    static <T> void update(T data) {
        runTransaction(session -> session.update(data));
    }

    static <T> void delete(T data) {
        runTransaction(session -> session.delete(data));
    }

    static <T> void deleteByID(Class<T> modelClass, int id) {
        runTransaction(session -> {
            session.get(modelClass, id);
        });
    }

    static <T> void deleteByID(Class<T> modelClass, String id) {
        runTransaction(session -> {
            session.get(modelClass, id);
        });
    }

    private static void runTransaction(CommandRunInTransation actionFn) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        actionFn.runWith(session);

        session.getTransaction().commit();
        session.close();
    }
}

interface CommandRunInTransation {
    public void runWith(Session session);
}
