package org.tec.carpooling.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tec.carpooling.common.utils.PersistenceManager;

import java.io.IOException;
import java.net.URL;

public class CarpoolingApplication extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("INFO: Application initializing...");
        try {
            PersistenceManager.initializeFactory();
        } catch (Exception e) {
            System.err.println("FATAL: Database initialization failed. Application cannot start.");
            throw new RuntimeException("Database initialization failed", e);
        }
        System.out.println("INFO: Database connection pool initialized (EntityManagerFactory created).");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("INFO: Application starting UI...");
        try {
            URL fxmlUrl = getClass().getResource("/views/login-view.fxml"); // Ajusta la ruta a tu FXML principal
            if (fxmlUrl == null) {
                System.err.println("ERROR: Cannot find FXML file.");
                return;
            }
            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root);

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
        } catch (Exception e) {
            System.err.println("ERROR: Unexpected error during application start: " + e.getMessage());
            e.printStackTrace();
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