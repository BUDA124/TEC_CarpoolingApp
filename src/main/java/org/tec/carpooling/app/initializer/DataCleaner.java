package org.tec.carpooling.app.initializer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class DataCleaner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager;

    private static final List<String> TRUNCATE_ORDER_SQL_TABLES = Arrays.asList(
            // Tablas de relación N:N (tablas "de unión") - Dependen de dos entidades
            "TRIPHASSTOPHASPAYMENTMETHOD",
            "TRIPHASSTOP",
            "PASSENGERJOINTRIP",
            "PASSENGERQUERYTRIP",
            "TRIPREPORTDAILYREPORT",
            "TRIPHASTRIPSTATUS",
            "STOPHASCOORDINATELOCATION",
            "LOGBOOKHASENTITYMODIFIED",
            "ATTRMODHASENTMOD",
            "ADMINRECEIVEDAILYREPORT",
            "ADMINMANAGEINSTITUTION",
            "CREDENTIALHASTYPEOFCREDENTIAL",

            // Tablas dependientes de entidades core (ej. PERSON, INSTITUTION, TRIP)
            "INSTITUTIONALEMAIL",
            "ACCESSSTATUS",
            "CREDENTIAL",
            "PHONENUMBER",
            "EMAIL",
            "STOP",
            "DAILYREPORT",
            "CONTACTPHONENUMBER",
            "CONTACTEMAIL",
            "ATTRIBUTEMODIFIED",

            // Entidades Core - Ordenadas de hijo a padre para borrado
            "TRIP",
            "VEHICLE",
            "PERSONALUSER",
            "DRIVER",
            "PASSENGER",
            "ADMINISTRATOR",
            "PERSON",
            "INSTITUTION",

            // Datos Geográficos (dependientes entre sí, luego de AUDITLOG)
            "DISTRICT",
            "CANTON",
            "PROVINCE",
            "COUNTRY",

            // Tablas de Catálogo/Estáticas (si los datos de prueba los modifican)
            "PRICESTATUS",
            "TRIPSTATUS",
            "PAYMENTMETHOD",
            "USERSTATUS",
            "TYPEOFCREDENTIAL",
            "GENDER",
            "PARAMETER",
            "COORDINATELOCATION",

            // Tablas de Auditoría (dependientes de AUDITLOG)
            "LOGBOOK",
            "ENTITYMODIFIED",
            "AUDITLOG"
    );

    // Listado de secuencias para reiniciar (según la sección III del resumen de tu sistema)
    private static final List<String> SEQUENCE_NAMES = Arrays.asList(
            "SEQ_DAILYREPORT", "SEQ_INSTITUTION", "SEQ_CONTACTPHONENUMBER", "SEQ_CONTACTEMAIL",
            "SEQ_ACCESSSTATUS", "SEQ_PERSON", "SEQ_PHONENUMBER", "SEQ_EMAIL",
            "SEQ_CREDENTIAL", "SEQ_TYPEOFCREDENTIAL", "SEQ_GENDER", "SEQ_PERSONALUSER",
            "SEQ_USERSTATUS", "SEQ_INSTITUTIONALEMAIL", "SEQ_VEHICLE", "SEQ_TRIP",
            "SEQ_PRICESTATUS", "SEQ_TRIPSTATUS", "SEQ_STOP", "SEQ_COORDINATELOCATION",
            "SEQ_DISTRICT", "SEQ_CANTON", "SEQ_PROVINCE", "SEQ_COUNTRY",
            "SEQ_PAYMENTMETHOD", "SEQ_AUDITLOG", "SEQ_PARAMETER", "SEQ_LOGBOOK",
            "SEQ_ENTITYMODIFIED", "SEQ_ATTRIBUTEMODIFIED"
    );

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        truncateAndResetAllTables();
    }

    @Transactional
    public void truncateAndResetAllTables() {

        // 1. Deshabilitar todas las restricciones de clave foránea
        alterAllForeignKeyConstraints("DISABLE");

        // 2. Truncar todas las tablas en el orden definido
        for (String tableName : TRUNCATE_ORDER_SQL_TABLES) {
            try {
                // TRUNCATE TABLE para Oracle no soporta RESTART IDENTITY, se hace aparte con secuencias.
                entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
                System.out.println("Truncada tabla: " + tableName);
            } catch (Exception e) {
                System.err.println("Error truncando tabla " + tableName + ": " + e.getMessage());
                // Lanzar la excepción para asegurar que la transacción se revierta si algo falla.
                throw new RuntimeException("Fallo al truncar la tabla: " + tableName, e);
            }
        }

        // 3. Reiniciar todas las secuencias (ahora usando DROP y CREATE)
        resetAllSequences();

        // 4. Re-habilitar todas las restricciones de clave foránea
        alterAllForeignKeyConstraints("ENABLE");

    }

    private void executeSql(String sql) {
        try {
            entityManager.createNativeQuery(sql).executeUpdate();
        } catch (Exception e) {
            System.err.println("Error ejecutando SQL: " + sql + " - " + e.getMessage());
            throw new RuntimeException("Error al ejecutar DDL.", e);
        }
    }

    private void alterAllForeignKeyConstraints(String action) {
        // Se añade el cast explícito para la advertencia "Unchecked assignment" que discutimos previamente
        List<Object[]> constraints = (List<Object[]>) entityManager.createNativeQuery(
                "SELECT table_name, constraint_name FROM user_constraints WHERE constraint_type = 'R' AND owner = user"
        ).getResultList();

        for (Object[] constraint : constraints) {
            String tableName = (String) constraint[0];
            String constraintName = (String) constraint[1];
            String sql = String.format("ALTER TABLE %s %s CONSTRAINT %s", tableName, action, constraintName);
            try {
                executeSql(sql);
            } catch (RuntimeException e) {
                System.err.println("Advertencia: No se pudo " + action + " la restricción " + constraintName + " en la tabla " + tableName + ". Error: " + e.getMessage());
                throw e;
            }
        }
    }

    private void resetAllSequences() {
        for (String seqName : SEQUENCE_NAMES) {
            try {
                // Intenta eliminar la secuencia. Si no existe, Oracle lanzará un error (ORA-02289).
                executeSql("DROP SEQUENCE " + seqName);
                System.out.println("Eliminada secuencia: " + seqName);
            } catch (RuntimeException e) {
                // Si la secuencia no existe (ORA-02289), simplemente logueamos y continuamos para crearla.
                // En un entorno de prueba, es común que las secuencias no existan la primera vez.
                if (e.getMessage() != null && e.getMessage().contains("ORA-02289")) { // Check for "sequence does not exist" error
                    System.out.println("Secuencia " + seqName + " no existía. Procediendo a crearla.");
                } else {
                    System.err.println("Error al eliminar secuencia " + seqName + ": " + e.getMessage());
                    // Otros errores al eliminar pueden ser críticos, así que los relanzamos.
                    throw e;
                }
            }

            try {
                // Crea la secuencia de nuevo, empezando por 1
                executeSql("CREATE SEQUENCE " + seqName + " START WITH 1 INCREMENT BY 1 CACHE 20 NOCYCLE");
                System.out.println("Creada/Reiniciada secuencia: " + seqName);
            } catch (RuntimeException e) {
                System.err.println("Error al crear secuencia " + seqName + ": " + e.getMessage());
                // Un fallo al crear una secuencia es un error crítico y debe detener el proceso.
                throw new RuntimeException("Fallo al crear la secuencia: " + seqName, e);
            }
        }
        System.out.println("Todas las secuencias reiniciadas.");
    }
}