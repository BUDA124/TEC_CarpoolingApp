package org.tec.carpooling.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
// Ya no necesitas PersistenceManager aquí, Spring lo gestionará
// import org.tec.carpooling.common.utils.PersistenceManager;

import java.io.IOException;
import java.net.URL;

public class CarpoolingApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception {
        System.out.println("INFO: Initializing Spring Context...");
        try {
            springContext = new SpringApplicationBuilder(CarpoolingSpringApp.class).run();
            CarpoolingSpringApp.setSpringContext(this.springContext);
            System.out.println("INFO: Spring Context initialized.");
        } catch (Exception e) {
            System.err.println("FATAL: Spring Context initialization failed. Application cannot start.");
            e.printStackTrace();
            throw new RuntimeException("Spring Context initialization failed", e);
        }
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("INFO: Application starting UI...");
        try {
            URL fxmlUrl = getClass().getResource("/views/login-view.fxml");
            if (fxmlUrl == null) {
                System.err.println("ERROR: Cannot find FXML file: /views/login-view.fxml");
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            fxmlLoader.setControllerFactory(springContext::getBean);

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/css/styles.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.out.println("WARN: CSS file /css/styles.css not found.");
            }

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
        System.out.println("INFO: Application stopping...");
        if (springContext != null) {
            springContext.close();
            System.out.println("INFO: Spring Context closed.");
        }
        CarpoolingSpringApp.setSpringContext(null);
        super.stop();
    }

    // Aquí NO inicia la aplicación. Iniciar en CarpoolingSpringApp.
    public static void main(String[] args) {
        launch(args);
    }
}