package org.tec.carpooling.ui.controllers.driver;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
public class DriverSettingsController {

    @Autowired
    public DriverSettingsController() {}

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