package org.tec.carpooling.ui.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.bl.services.UserService;
import org.tec.carpooling.da.entities.GenderEntity;

import java.util.List;

@Component
public class RegistrationController {

    @FXML private ComboBox<GenderEntity> genderComboBox;

    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;

    @Autowired
    private UserService userService;

    @Autowired
    public RegistrationController() {}

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
    private void handleRegister() {
        // ... obtener otros datos de la persona (nombre, apellido, etc.)
        // String firstName = firstNameField.getText();
        // ...

        GenderEntity selectedGender = genderComboBox.getSelectionModel().getSelectedItem();

        if (selectedGender == null) {
            // Mostrar error: el género es obligatorio
            return;
        }

        UserRegistrationDTO userDTO = new UserRegistrationDTO();
        // personDTO.setFirstName(firstName);
        // ... setear otros campos del DTO ...

        userDTO.setIdGender(selectedGender.getId());
        // Institution u otros seleccionados de forma similar:
        // personDTO.setInstitutionId(selectedInstitution.getId());
        // ...


        try {
            userService.registerNewUser(userDTO);
            // Mostrar mensaje de éxito
        } catch (Exception e) {
            // Mostrar mensaje de error
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}