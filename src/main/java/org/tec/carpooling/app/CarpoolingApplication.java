package org.tec.carpooling.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tec.carpooling.da.PersistenceManager; // Importa tu clase

import java.io.IOException;
import java.net.URL;

public class CarpoolingApplication extends Application {

    @Override
    public void init() throws Exception {
        // MÉTODO init(): Se ejecuta ANTES de start().
        // Es un buen lugar para inicializaciones que pueden fallar,
        // como la conexión a la base de datos.
        System.out.println("INFO: Application initializing...");
        try {
            PersistenceManager.initializeFactory(); // <-- ¡AQUÍ INTENTAS CONECTAR!
            // Si initializeFactory() lanza una excepción, la aplicación no continuará a start().
        } catch (Exception e) {
            System.err.println("FATAL: Database initialization failed. Application cannot start.");
            // Aquí podrías mostrar un diálogo de error al usuario antes de salir.
            // Lanzar la excepción aquí detendrá el inicio de JavaFX.
            throw new RuntimeException("Database initialization failed", e);
        }
        System.out.println("INFO: Database connection pool initialized (EntityManagerFactory created).");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("INFO: Application starting UI...");
        try {
            // Carga tu FXML inicial
            URL fxmlUrl = getClass().getResource("/views/LoginView.fxml"); // Ajusta la ruta a tu FXML principal
            if (fxmlUrl == null) {
                System.err.println("ERROR: Cannot find FXML file.");
                // Manejar el error adecuadamente
                return;
            }
            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root);

            // Carga tu CSS (opcional)
            // URL cssUrl = getClass().getResource("/css/styles.css");
            // if (cssUrl != null) {
            //     scene.getStylesheets().add(cssUrl.toExternalForm());
            // } else {
            //     System.out.println("WARN: CSS file not found.");
            // }

            primaryStage.setTitle("Carpooling App");
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("INFO: UI started successfully.");

        } catch (IOException e) {
            System.err.println("ERROR: Failed to load FXML or start UI: " + e.getMessage());
            e.printStackTrace();
            // Mostrar un error al usuario
        } catch (Exception e) {
            System.err.println("ERROR: Unexpected error during application start: " + e.getMessage());
            e.printStackTrace();
            // Mostrar un error al usuario
        }
    }

    @Override
    public void stop() throws Exception {
        // MÉTODO stop(): Se ejecuta cuando la aplicación se cierra.
        // Es el lugar ideal para liberar recursos, como la conexión a la BD.
        System.out.println("INFO: Application stopping...");
        PersistenceManager.closeFactory(); // <-- ¡AQUÍ CIERRAS LA FÁBRICA (y las conexiones)!
        System.out.println("INFO: Database connection pool closed.");
        super.stop();
    }

    // Tu método main que simplemente lanza la aplicación JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}