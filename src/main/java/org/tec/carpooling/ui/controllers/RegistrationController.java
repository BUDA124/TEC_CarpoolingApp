package org.tec.carpooling.ui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {

    @FXML
    private TextField TF_firstName;
    @FXML
    private TextField TF_secondName;
    @FXML
    private TextField TF_firstSurname;
    @FXML
    private TextField TF_secondSurname;
    @FXML
    private TextField TF_personalEmail;
    @FXML
    private TextField TF_idNumber;
    @FXML
    private TextField TF_username;
    @FXML
    private TextField TF_password;
    @FXML
    private TextField TF_confirmPassword;
    @FXML
    private TextField TF_institutionalEmail;
    @FXML
    private ComboBox<GenderEntity> CB_gender;

    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;

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

        CB_gender.setItems(FXCollections.observableArrayList(genders));

        CB_gender.setConverter(new StringConverter<GenderEntity>() {
            @Override
            public String toString(GenderEntity gender) {
                return gender != null ? gender.getGenderName() : "";
            }

            @Override
            public GenderEntity fromString(String string) {
                return CB_gender.getItems().stream()
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
