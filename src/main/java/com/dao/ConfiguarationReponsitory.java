package com.dao;

import java.lang.reflect.Method;

import com.models.Configuration;
import com.utils.DBFactory;

public class ConfiguarationReponsitory {
    public <T> void saveConfiguaration(String configName, T configVar) {
        Configuration config = DBFactory.findByID(Configuration.class, configName);

        if (config == null) {
            addConfiguaration(configName, configVar, null);
            return;
        }
    }

    public <T> void addConfiguaration(String configName, T configVar) {
        if (!configVar.getClass().getName().startsWith("java.lang"))
            return;

        addConfiguaration(configName, configVar, null);
    }

    public <T> void addConfiguaration(String configName, T configVar, String notes) {
        Configuration config = new Configuration(configName, configVar, notes);
        DBFactory.create(config);
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfiguration(String configName) {
        Configuration config = DBFactory.findByID(Configuration.class, configName);
        if (config == null)
            return null;

        try {
            Class<T> classType = (Class<T>) Class.forName(config.getDataType());
            if (classType == null || !classType.getName().startsWith("java.lang"))
                return null;

            Method valueOf = classType.getMethod("valueOf", String.class);
            T value = (T) valueOf.invoke(null, config.getValue());

            return value != null ? value : null;
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteConfiguration(String configName) {
        DBFactory.deleteByID(Configuration.class, configName);
    }

    public void updateConfiguration(Configuration config) {
        DBFactory.update(config);
    }
}
