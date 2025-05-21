package org.tec.carpooling.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;

@Controller
public class DriverScheduledController {
    @FXML
    private javafx.scene.layout.Pane historyPane;

    @FXML
    private javafx.scene.layout.Pane scheduleRidePane;

    @FXML
    private javafx.scene.layout.Pane publishRidePane;

    @FXML
    private void goToHistoryPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToScheduleRidePane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver-scheduled-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToPublishRidePane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver-ride-view.fxml");
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