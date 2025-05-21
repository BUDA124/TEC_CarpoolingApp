package org.tec.carpooling.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;

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

    @FXML
    private void On_BTN_enterLogIn(ActionEvent event) {
        String username = TF_username.getText();
        String password = TF_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("There Are Blank Spaces", Alert.AlertType.ERROR, "Fill all the fields.");
            return;
        }
        try {
            SceneManager.switchToScene(event, "pick-role-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

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