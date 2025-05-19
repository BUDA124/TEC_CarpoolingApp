package org.tec.carpooling.ui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField secondNameTextField;
    @FXML
    private TextField firstSurnameTextField;
    @FXML
    private TextField secondSurnameTextField;
    @FXML
    private TextField personalEmailTextField;
    @FXML
    private TextField idNumberTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private TextField institutionalEmailTextField;

    @FXML
    private ComboBox<GenderEntity> genderComboBox;

    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;

    @Autowired
    public RegistrationController() {}

    @FXML
    private void onSignUp(ActionEvent event) {
        try {
            SceneManager.switchToScene(event, "pick-role-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void onLogIn(ActionEvent event) {
        try {
            SceneManager.switchToScene(event, "login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void initialize() {
        // Load Genders
        List<GenderEntity> genders = simpleDataRetrievalService.getAllGenders();

        genderComboBox.setItems(FXCollections.observableArrayList(genders));

        genderComboBox.setConverter(new StringConverter<GenderEntity>() {
            @Override
            public String toString(GenderEntity gender) {
                return gender != null ? gender.getGenderName() : "";
            }

            @Override
            public GenderEntity fromString(String string) {
                return genderComboBox.getItems().stream()
                        .filter(g -> g.getGenderName().equals(string))
                        .findFirst().orElse(null);
            }
        });
    }

    @FXML
    private void handleRegister() {}

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
