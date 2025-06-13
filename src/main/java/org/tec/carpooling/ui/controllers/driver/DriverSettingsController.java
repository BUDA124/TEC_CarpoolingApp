package org.tec.carpooling.ui.controllers.driver;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;

@Controller
public class DriverSettingsController {

    @FXML
    private void goToPublishRidePane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-ride-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToScheduleRidePane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-scheduled-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToHistoryPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToAnalytics(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-stats-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSignOut(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSettings(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-settings-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}