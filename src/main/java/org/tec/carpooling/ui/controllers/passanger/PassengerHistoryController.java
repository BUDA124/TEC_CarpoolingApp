package org.tec.carpooling.ui.controllers.passanger;

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
    private void goToLookForRide(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToScheduledRides(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger-scheduled-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToHistory(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
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