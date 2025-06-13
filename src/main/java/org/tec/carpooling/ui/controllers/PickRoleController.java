package org.tec.carpooling.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.services.StartUpService;
import org.tec.carpooling.da.repositories.DriverRepository;
import org.tec.carpooling.da.repositories.PersonRepository;
import org.tec.carpooling.da.repositories.PersonalUserRepository;
import org.tec.carpooling.ui.SceneManager;
import org.tec.carpooling.ui.UserSession;

import java.io.IOException;

@Controller
public class PickRoleController {

    @FXML private AnchorPane AP_driverWheel;
    @FXML private AnchorPane AP_passangerSeat;
    @FXML private Button registerDriverButton;

    @Autowired private PersonalUserRepository personalUserRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private DriverRepository driverRepository;

    @Autowired private StartUpService startUpService;

    @FXML
    private void onPassangerPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void onDriverPane(MouseEvent event) {
        String username = UserSession.getInstance().getCurrentUser();

        if (username == null || username.isEmpty()) {
            showError("User not found.");
            return;
        }

        boolean isDriver = startUpService.IsUserDriver(username);

        try {
            if (isDriver) {
                SceneManager.switchToScene(event, "driver/driver-ride-view.fxml");
            } else {
                showWarning("Please register as a driver first.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML
    private void onRegisterDriverButton(ActionEvent event) {
        try {
            SceneManager.switchToScene(event, "register-driver-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {

    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}