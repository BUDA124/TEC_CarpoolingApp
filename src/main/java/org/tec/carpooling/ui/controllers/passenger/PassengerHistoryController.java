package org.tec.carpooling.ui.controllers.passenger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;

@Controller
public class PassengerHistoryController {

    @FXML
    private Pane P_lookForRide;

    @FXML
    private Pane P_scheduledRides;

    @FXML
    private Pane P_history;

    @FXML
    public void initialize() {
        // Inicialización si es necesaria
    }

    @FXML
    private void goToLookRide(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Look Ride view.");
        }
    }

    @FXML
    private void goToScheduledRides(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-scheduled-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Scheduled Rides view.");
        }
    }

    @FXML
    private void goToHistory(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passanger-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load History view.");
        }
    }

    @FXML
    private void goToAnalytics(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-stats-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Analytics view.");
        }
    }

    @FXML
    private void goToSettings(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-settings-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Settings view.");
        }
    }

    @FXML
    private void goToSignOut(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not sign out.");
        }
    }



    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}