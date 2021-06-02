package com.constants;

import org.hibernate.Session;

@FunctionalInterface
public interface CommandRunInTransation<T> {
    public T runWith(Session session);
}