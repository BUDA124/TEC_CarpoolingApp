package org.tec.carpooling.ui.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.dto.UI_BL.StartUp.LogInDTO;
import org.tec.carpooling.bl.services.StartUpService;
import org.tec.carpooling.common.constants.AppConstants;
import org.tec.carpooling.common.exceptions.AuthenticationException;
import org.tec.carpooling.da.repositories.PersonRepository;
import org.tec.carpooling.da.repositories.PersonalUserRepository;
import org.tec.carpooling.ui.SceneManager;
import org.tec.carpooling.ui.UserSession;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class LoginController {
    @FXML
    private TextField TF_username;

    @FXML
    private TextField TF_password;

    @FXML
    private Button BTN_enterLogIn;

    @FXML
    private Text T_signUp;

    @Autowired StartUpService startUpService;

    private final Validator validator = AppConstants.getValidator();

    @FXML
    private void On_BTN_enterLogIn(ActionEvent event) {
        String username = TF_username.getText();
        String password = TF_password.getText();

        LogInDTO logInDTO = new LogInDTO(username, password);

        Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

        if (violations.isEmpty()) {
            try {
                if (startUpService.logInUser(logInDTO)) {
                    UserSession.getInstance().setLogInUser(logInDTO.getUsername());
                    if (username.equals("MainAdmin")) {
                        SceneManager.switchToScene(event, "admin/admin-report-view.fxml");
                    } else {
                        SceneManager.switchToScene(event, "pick-role-view.fxml");
                    }
                } else {
                    showAlert("Login Failed", Alert.AlertType.ERROR, "Invalid username or password.");
                }
            } catch (AuthenticationException e) {
                showAlert("Authentication Error", Alert.AlertType.ERROR, e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                String errorMessage = "An unexpected error occurred while logging in.";
                showAlert("Login Error", Alert.AlertType.ERROR, errorMessage);
            }
        } else {
            String errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
            showAlert("Validation Error", Alert.AlertType.ERROR, errorMessages);
        }
    }

    @FXML
    private void On_T_signUp(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "sign-up-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    @FXML
    public void initialize() {
    }

    private void showAlert(String title, Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}