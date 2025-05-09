package org.tec.carpooling.common.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PersistenceManager {

    private static final String PERSISTENCE_UNIT_NAME = "CarpoolingAppPU";
    private static EntityManagerFactory emf;

    public static void initializeFactory() {
        if (emf == null) {
            try {
                Properties dbProps = loadProperties();
                Map<String, String> persistenceMap = new HashMap<>();
                persistenceMap.put("jakarta.persistence.jdbc.driver", dbProps.getProperty("db.driver"));
                persistenceMap.put("jakarta.persistence.jdbc.url", dbProps.getProperty("db.url"));
                persistenceMap.put("jakarta.persistence.jdbc.user", dbProps.getProperty("db.user"));
                persistenceMap.put("jakarta.persistence.jdbc.password", dbProps.getProperty("db.password"));
                persistenceMap.put("hibernate.dialect", dbProps.getProperty("hibernate.dialect"));
                persistenceMap.put("hibernate.show_sql", dbProps.getProperty("hibernate.show_sql"));
                persistenceMap.put("hibernate.format_sql", dbProps.getProperty("hibernate.format_sql"));
                persistenceMap.put("hibernate.hbm2ddl.auto", dbProps.getProperty("hibernate.hbm2ddl.auto"));

                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, persistenceMap);
                System.out.println("INFO: EntityManagerFactory created successfully using external properties.");
            } catch (Exception e) {
                System.err.println("ERROR: Failed to create EntityManagerFactory: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize database connection", e);
            }
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = PersistenceManager.class.getClassLoader()
                .getResourceAsStream("config/application.properties")) { // Ruta desde classpath
            if (input == null) {
                System.err.println("ERROR: Unable to find config/application.properties");
                throw new RuntimeException("Configuration file not found.");
            }
            properties.load(input);
        } catch (Exception ex) {
            System.err.println("ERROR: Failed to load configuration file: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Failed to load configuration.", ex);
        }
        return properties;
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory has not been initialized. Call initializeFactory() first.");
        }
        return emf.createEntityManager();
    }

    public static void closeFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("INFO: EntityManagerFactory closed.");
        }
    }
}