package org.tec.carpooling.ui.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
public class AdminRequestMenuController {

    @Autowired
    public AdminRequestMenuController() {}

    @FXML
    public void initialize() {

    }

    private void Show(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}