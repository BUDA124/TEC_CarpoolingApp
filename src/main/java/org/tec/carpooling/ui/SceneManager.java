package org.tec.carpooling.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.tec.carpooling.app.CarpoolingSpringApp;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {

    // You need to set this context when your application starts
    private static ConfigurableApplicationContext springContext;

    public static void setSpringContext(ConfigurableApplicationContext context) {
        SceneManager.springContext = context;
    }

    private static final String FXML_BASE_PATH = "/org/tec/carpooling/ui/controllers/";
    private static final String defaultTitle = "Carpooling App";

    private static ApplicationContext getContext() {
        ApplicationContext ctx = CarpoolingSpringApp.getApplicationContext();
        if (ctx == null) {
            System.err.println("CRITICAL: SceneManager.getContext() - Spring context is null! Subsequent operations will likely fail.");
        }
        return ctx;
    }

    private static FXMLLoader getLoader(String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                SceneManager.class.getResource(FXML_BASE_PATH + fxmlFile),
                "FXML file not found: " + FXML_BASE_PATH + fxmlFile
        ));
        ApplicationContext context = getContext();
        if (context != null) {
            loader.setControllerFactory(context::getBean);
        } else {
            System.err.println("WARN: SceneManager - Spring context is null. Controllers might not be injected for " + fxmlFile);
        }
        return loader;
    }

    public static void switchToScene(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setConfigurationForScene(stage, fxmlFile);
    }

    public static void switchToScene(MouseEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setConfigurationForScene(stage, fxmlFile);
    }

    private static void setConfigurationForScene(Stage stage, String fxmlFile) throws IOException {
        FXMLLoader loader = getLoader(fxmlFile);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(defaultTitle);
        stage.show();
    }

    public static <T> T loadSceneAndGetController(Stage stage, String fxmlFile) throws IOException {
        FXMLLoader loader = getLoader(fxmlFile);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(defaultTitle);
        stage.show();
        return loader.getController();
    }

    /**
     * Creates, shows a new popup window, and returns its controller.
     * The popup is shown modally and waits for closure.
     *
     * @param event    The ActionEvent that triggered the popup.
     * @param fxmlFile The FXML file for the popup content.
     * @return The controller of the loaded FXML.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static <T> T showPopupWindowAndGetController(ActionEvent event, String fxmlFile) throws IOException {
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        return showPopupWindowInternal(owner, fxmlFile, Modality.WINDOW_MODAL, true);
    }

    /**
     * Creates, shows a new popup window, and returns its controller.
     * The popup is shown modally and waits for closure.
     *
     * @param event    The MouseEvent that triggered the popup.
     * @param fxmlFile The FXML file for the popup content.
     * @return The controller of the loaded FXML.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static <T> T showPopupWindowAndGetController(MouseEvent event, String fxmlFile) throws IOException {
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        return showPopupWindowInternal(owner, fxmlFile, Modality.WINDOW_MODAL, true);
    }


    /**
     * Internal helper method to create and show a popup window.
     *
     * @param ownerWindow The owner window of this popup. Can be null.
     * @param fxmlFile    The FXML file name.
     * @param modality    The modality for the popup (e.g., Modality.APPLICATION_MODAL, Modality.WINDOW_MODAL).
     *                    If null, Modality.NONE is used.
     * @param wait        If true, uses showAndWait(), otherwise uses show().
     * @return The controller if wait is true, otherwise null.
     * @throws IOException If FXML loading fails.
     */
    private static <T> T showPopupWindowInternal(Window ownerWindow, String fxmlFile, Modality modality, boolean wait) throws IOException {
        FXMLLoader loader = getLoader(fxmlFile);
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle(defaultTitle);

        if (modality != null) {
            popupStage.initModality(modality);
        } else {
            popupStage.initModality(Modality.NONE);
        }

        if (ownerWindow != null) {
            popupStage.initOwner(ownerWindow);
        }

        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        if (wait) {
            popupStage.showAndWait();
            return loader.getController();
        } else {
            popupStage.show();
            return null; // Or loader.getController() if you want it immediately for non-waiting popups too
        }
    }
}