package org.tec.carpooling.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.CountryEntity;
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
    @FXML
    private ComboBox<CountryEntity> CB_country;

    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;

    @FXML
    private void BTN_enter(ActionEvent event) {
        String username = TF_username.getText();
        String password = TF_password.getText();
        String confirmPassword = TF_confirmPassword.getText();
        String institutionalEmail = TF_institutionalEmail.getText();

        String idNumber = TF_idNumber.getText();
        String firstName = TF_firstName.getText();
        String surname = TF_firstSurname.getText();
        String personalEmail = TF_personalEmail.getText();


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

        List<CountryEntity> countriesList = simpleDataRetrievalService.getAllCountries();
        ObservableList<CountryEntity> observableCountries = FXCollections.observableArrayList(countriesList);
        CB_country.setItems(observableCountries);
        CB_country.setConverter(new StringConverter<CountryEntity>() {
            @Override
            public String toString(CountryEntity country) {
                return country != null ? country.getName() : "";
            }

            @Override
            public CountryEntity fromString(String string) {
                return observableCountries.stream()
                        .filter(country -> country != null && country.getName().equalsIgnoreCase(string))
                        .findFirst()
                        .orElse(null); // Return null if no match is found
            }
        });
    }


    private void showAlert(String title, Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

 }

