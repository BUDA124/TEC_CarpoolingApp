package org.tec.carpooling.app.initializer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Order(1)
public class DataCleaner implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager;

    // El orden de truncado es crucial. De las tablas "hijas" a las "padres".
    private static final List<String> TRUNCATE_ORDER_SQL_TABLES = Arrays.asList(
            // Tablas de relación N:N
            "TRIPHASSTOPHASPAYMENTMETHOD", "TRIPHASSTOP", "PASSENGERJOINTRIP", "PASSENGERQUERYTRIP",
            "TRIPREPORTDAILYREPORT", "TRIPHASTRIPSTATUS", "STOPHASCOORDINATELOCATION", "LOGBOOKHASENTITYMODIFIED",
            "ATTRMODHASENTMOD", "ADMINRECEIVEDAILYREPORT", "ADMINMANAGEINSTITUTION", "CREDENTIALHASTYPEOFCREDENTIAL",

            // Tablas dependientes
            "INSTITUTIONALEMAIL", "ACCESSSTATUS", "PHONENUMBER", "EMAIL", "STOP", "DAILYREPORT",
            "CONTACTPHONENUMBER", "CONTACTEMAIL", "ATTRIBUTEMODIFIED", "CREDENTIAL",

            // Entidades Core (ordenadas de hijo a padre)
            "TRIP", "VEHICLE", "PERSONALUSER", "DRIVER", "PASSENGER", "ADMINISTRATOR", "PERSON", "INSTITUTION",

            // Geografía
            "DISTRICT", "CANTON", "PROVINCE", "COUNTRY",

            // Catálogos
            "PRICESTATUS", "TRIPSTATUS", "PAYMENTMETHOD", "USERSTATUS", "TYPEOFCREDENTIAL", "GENDER",
            "PARAMETER", "COORDINATELOCATION",

            // Auditoría
            "LOGBOOK", "ENTITYMODIFIED", "AUDITLOG"
    );

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("--- INICIANDO LIMPIEZA DE BASE DE DATOS PARA MariaDB ---");
        truncateAndResetAllTables();
        System.out.println("--- LIMPIEZA DE BASE DE DATOS COMPLETADA ---");
    }

    @Transactional
    public void truncateAndResetAllTables() {
        executeSql("SET FOREIGN_KEY_CHECKS = 0;");
        System.out.println("Verificación de claves foráneas DESHABILITADA.");
        List<String> reversedTables = new java.util.ArrayList<>(TRUNCATE_ORDER_SQL_TABLES);
        Collections.reverse(reversedTables);

        for (String tableName : reversedTables) {
            try {
                // Usamos TRUNCATE TABLE que también reinicia los contadores AUTO_INCREMENT
                executeSql("TRUNCATE TABLE " + tableName);
                System.out.println("Tabla truncada y AUTO_INCREMENT reiniciado: " + tableName);
            } catch (Exception e) {
                System.err.println("Error truncando tabla " + tableName + ": " + e.getMessage());
                // Si algo falla, es crucial rehabilitar los checks antes de terminar
                executeSql("SET FOREIGN_KEY_CHECKS = 1;");
                throw new RuntimeException("Fallo al truncar la tabla: " + tableName, e);
            }
        }

        executeSql("SET FOREIGN_KEY_CHECKS = 1;");
        System.out.println("Verificación de claves foráneas HABILITADA.");
    }

    private void executeSql(String sql) {
        try {
            entityManager.createNativeQuery(sql).executeUpdate();
        } catch (Exception e) {
            System.err.println("Error ejecutando SQL: " + sql + " - " + e.getMessage());
            throw new RuntimeException("Error al ejecutar DDL.", e);
        }
    }
}