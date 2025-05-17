// src/main/java/org/tec/carpooling/ui/utils/SceneManager.java (example snippet)
package org.tec.carpooling.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.tec.carpooling.app.CarpoolingSpringApp;

import java.io.IOException;

public class SceneManager {

    private static ApplicationContext getContext() {
        ApplicationContext ctx = CarpoolingSpringApp.getSpringContext();
        if (ctx == null) {
            System.err.println("CRITICAL: SceneManager.getContext() - Spring context is null! Subsequent operations will likely fail.");
        }
        return ctx;
    }

    public static void switchToScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/tec/carpooling/ui/controllers/" + fxmlFile));
        ApplicationContext context = getContext();
        if (context != null) {
            loader.setControllerFactory(context::getBean);
        } else {
            System.err.println("WARN: SceneManager.switchToScene - Spring context is null. Controllers might not be injected for " + fxmlFile);
        }
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToScene(Stage stage, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/tec/carpooling/ui/controllers/" + fxmlFile));
        ApplicationContext context = getContext();
        if (context != null) {
            loader.setControllerFactory(context::getBean);
        } else {
            System.err.println("WARN: SceneManager.switchToScene(Stage) - Spring context is null. Controllers might not be injected for " + fxmlFile);
        }
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Carpooling App - " + fxmlFile.replace(".fxml", ""));
        stage.show();
    }

    public static <T> T loadSceneAndGetController(Stage stage, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/org/tec/carpooling/ui/controllers/" + fxmlFile));
        ApplicationContext context = getContext();
        if (context != null) {
            loader.setControllerFactory(context::getBean);
        } else {
            System.err.println("WARN: SceneManager.loadSceneAndGetController - Spring context is null. Controllers might not be injected for " + fxmlFile);
        }
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Carpooling App - " + fxmlFile.replace(".fxml", ""));
        stage.show();
        return loader.getController();
    }
}