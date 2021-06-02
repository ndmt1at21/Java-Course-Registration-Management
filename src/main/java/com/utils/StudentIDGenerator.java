package com.utils;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class StudentIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        System.out.println("kdjfhdfhjdfhjdhfj------");

        String prefix = "DEP";
        Connection connection = session.connection();

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select count(id) from Student");

            if (rs.next()) {
                int id = rs.getInt(1) + 101;
                String generatedId = "18120612";
                return generatedId;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}