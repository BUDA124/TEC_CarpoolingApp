package org.tec.carpooling.ui.controllers.admin;


import javafx.scene.control.Alert;
import org.springframework.stereotype.Controller;

@Controller
public class AdminCheckInstDetailView {



    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
