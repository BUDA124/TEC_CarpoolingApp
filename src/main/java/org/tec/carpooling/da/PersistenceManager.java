package org.tec.carpooling.da;

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
                // Cargar propiedades desde application.properties
                Properties dbProps = loadProperties();

                // Crear mapa de propiedades para pasar a JPA
                Map<String, String> persistenceMap = new HashMap<>();
                persistenceMap.put("jakarta.persistence.jdbc.driver", dbProps.getProperty("db.driver"));
                persistenceMap.put("jakarta.persistence.jdbc.url", dbProps.getProperty("db.url"));
                persistenceMap.put("jakarta.persistence.jdbc.user", dbProps.getProperty("db.user"));
                persistenceMap.put("jakarta.persistence.jdbc.password", dbProps.getProperty("db.password"));

                persistenceMap.put("hibernate.dialect", dbProps.getProperty("hibernate.dialect"));
                persistenceMap.put("hibernate.show_sql", dbProps.getProperty("hibernate.show_sql"));
                persistenceMap.put("hibernate.format_sql", dbProps.getProperty("hibernate.format_sql"));
                persistenceMap.put("hibernate.hbm2ddl.auto", dbProps.getProperty("hibernate.hbm2ddl.auto"));
                // Añade otras propiedades de Hibernate si las necesitas

                // Crear EntityManagerFactory pasando las propiedades cargadas
                // persistence.xml todavía se usa para <provider> y <class> listings
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

    // Obtiene un EntityManager (llamar cuando necesites interactuar con la BD)
    public static EntityManager getEntityManager() {
        if (emf == null) {
            // Podrías llamar a initializeFactory() aquí si quieres inicialización perezosa,
            // pero es mejor asegurarse de que esté inicializado al arrancar.
            throw new IllegalStateException("EntityManagerFactory has not been initialized. Call initializeFactory() first.");
            // O intentar inicializar:
            // initializeFactory(); // Asegurarse de que esté inicializado
        }
        return emf.createEntityManager();
    }

    // Cierra el EntityManagerFactory (llamar al cerrar la aplicación)
    public static void closeFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("INFO: EntityManagerFactory closed.");
        }
    }

    // Método de conveniencia para ejecutar operaciones dentro de una transacción
    // Ejemplo: PersistenceManager.executeInTransaction(em -> em.persist(newUser));
    public static void executeInTransaction(java.util.function.Consumer<EntityManager> operation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            operation.accept(em); // Ejecuta la operación que pasaste (lambda)
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Relanzar o manejar la excepción como prefieras
            throw new RuntimeException("Transaction failed", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // ¡Importante cerrar el EntityManager!
            }
        }
    }
}