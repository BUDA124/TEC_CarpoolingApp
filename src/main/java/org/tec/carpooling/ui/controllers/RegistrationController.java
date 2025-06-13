package org.tec.carpooling.ui.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.tec.carpooling.bl.dto.UI_BL.StartUp.UserRegistrationDTO;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.bl.services.StartUpService;
import org.tec.carpooling.common.constants.AppConstants;
import org.tec.carpooling.da.entities.CountryEntity;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;
import org.tec.carpooling.da.entities.TypeOfCredentialEntity;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {

    @FXML private TextField TF_firstName;
    @FXML private TextField TF_secondName;
    @FXML private TextField TF_firstSurname;
    @FXML private TextField TF_secondSurname;
    @FXML private TextField TF_personalEmail;
    @FXML private TextField TF_idNumber;
    @FXML private TextField TF_username;
    @FXML private TextField TF_password;
    @FXML private TextField TF_confirmPassword;
    @FXML private TextField TF_institutionalEmail;
    @FXML private ComboBox<GenderEntity> CB_gender;
    @FXML private ComboBox<CountryEntity> CB_country;
    @FXML private ComboBox<TypeOfCredentialEntity> CB_typeId;
    @FXML private ComboBox<InstitutionEntity> CB_institution;
    @FXML private Text T_LogIn;
    @FXML private Button BTN_enter;

    @Autowired private SimpleDataRetrievalService simpleDataRetrievalService;
    @Autowired StartUpService startUpService;

    private final Validator validator = AppConstants.getValidator();

    @FXML
    public void initialize() {
        List<GenderEntity> genderNames = simpleDataRetrievalService.getAllGenders();
        ObservableList<GenderEntity> observableGenders = FXCollections.observableArrayList(genderNames);
        CB_gender.setItems(FXCollections.observableArrayList(genderNames));
        CB_gender.setConverter(new StringConverter<GenderEntity>() {
            @Override
            public String toString(GenderEntity gender) {
                return gender != null ? gender.getGenderName() : "";
            }

            @Override
            public GenderEntity fromString(String string) {
                return observableGenders.stream()
                        .filter(g -> g.getGenderName().equals(string))
                        .findFirst().orElse(null);
            }
        });

        List<CountryEntity> countryNames = simpleDataRetrievalService.getAllCountries();
        ObservableList<CountryEntity> observableCountries = FXCollections.observableArrayList(countryNames);
        CB_country.setItems(FXCollections.observableArrayList(countryNames));
        CB_country.setConverter(new StringConverter<CountryEntity>() {
            @Override
            public String toString(CountryEntity country) {
                return country != null ? country.getName() : "";
            }

            @Override
            public CountryEntity fromString(String string) {
                return observableCountries.stream()
                        .filter(c -> c.getName().equals(string))
                        .findFirst().orElse(null);
            }
        });

        List<InstitutionEntity> institutionsList = simpleDataRetrievalService.getAllInstitutions();
        ObservableList<InstitutionEntity> observableInstitutions = FXCollections.observableArrayList(institutionsList);
        CB_institution.setItems(observableInstitutions);
        CB_institution.setConverter(new StringConverter<InstitutionEntity>() {
            @Override
            public String toString(InstitutionEntity institution) {
                return institution != null ? institution.getInstitutionName() : "";
            }

            @Override
            public InstitutionEntity fromString(String string) {
                return observableInstitutions.stream()
                        .filter(institution -> institution != null && institution.getInstitutionName().equalsIgnoreCase(string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    @FXML
    private void on_BTN_enter(ActionEvent event) {
        String firstName = TF_firstName.getText();
        String secondName = TF_secondName.getText();
        String firstSurname = TF_firstSurname.getText();
        String secondSurname = TF_secondSurname.getText();
        String personalEmail = TF_personalEmail.getText();
        String idNumber = TF_idNumber.getText();
        Optional<TypeOfCredentialEntity> typeOfCredentialEntityOptional = Optional.ofNullable(CB_typeId.getValue());
        String username = TF_username.getText();
        String password = TF_password.getText();
        String confirmPassword = TF_confirmPassword.getText();
        String institutionalEmail = TF_institutionalEmail.getText();

        boolean isValid = true;

        if (firstName.isBlank()) {
            System.out.println("Error: El primer nombre es obligatorio.");
            isValid = false;
        } else {
            System.out.println("Primer nombre: OK");
        }

        if (!secondName.isBlank()) {
            System.out.println("Segundo nombre ingresado: " + secondName);
        } else {
            System.out.println("Segundo nombre: (Opcional - vacío)");
        }

        if (firstSurname.isBlank()) {
            System.out.println("Error: El primer apellido es obligatorio.");
            isValid = false;
        } else {
            System.out.println("Primer apellido: OK");
        }

        if (!secondSurname.isBlank()) {
            System.out.println("Segundo apellido ingresado: " + secondSurname);
        } else {
            System.out.println("Segundo apellido: (Opcional - vacío)");
        }

        if (personalEmail.isBlank()) {
            System.out.println("Error: El correo electrónico personal es obligatorio.");
            isValid = false;
        } else {
              System.out.println("Correo personal: OK");
        }

        if (idNumber.isBlank()) {
            System.out.println("Error: El número de identificación es obligatorio.");
            isValid = false;
        } else {
            System.out.println("Número de ID: OK");
        }

        if (typeOfCredentialEntityOptional.isEmpty()) {
            System.out.println("Error: Debe seleccionar un tipo de identificación.");
            isValid = false;
        } else {
            System.out.println("Tipo de ID seleccionado: " + typeOfCredentialEntityOptional.get());
        }

        if (username.isBlank()) {
            System.out.println("Error: El nombre de usuario es obligatorio.");
            isValid = false;
        } else {
            System.out.println("Nombre de usuario: OK");
        }

        if (password.isBlank()) {
            System.out.println("Error: La contraseña es obligatoria.");
            isValid = false;
        } else {
            System.out.println("Contraseña: OK (cumple longitud mínima)");
        }

        if (confirmPassword.isBlank()) {
            System.out.println("Error: La confirmación de contraseña es obligatoria.");
            isValid = false;
        } else {
            System.out.println("Confirmación de contraseña: OK (coincide)");
        }

        if (institutionalEmail.isBlank()) {
            System.out.println("Error: El correo electrónico institucional es obligatorio.");
            isValid = false;
        } else {
            System.out.println("Correo institucional: OK");
        }

        if (!isValid) {
            System.out.println("Hay errores en el formulario.");
        }

        try {
            UserRegistrationDTO registrationDTO = new UserRegistrationDTO();
            registrationDTO.setFirstName(firstName);
            registrationDTO.setSecondName(secondName);
            registrationDTO.setFirstSurname(firstSurname);
            registrationDTO.setSecondSurname(secondSurname);
            registrationDTO.setEmail(personalEmail);
            registrationDTO.setCredentialNumber(idNumber);
            registrationDTO.setUsername(username);
            registrationDTO.setPassword(password);

            registrationDTO.setInstitutionalEmail(institutionalEmail);

            // Set values from ComboBoxes
            registrationDTO.setIdGender(CB_gender.getValue());
            registrationDTO.setNationality(CB_country.getValue());
            registrationDTO.setIdInstitution(CB_institution.getValue());
            registrationDTO.setIdTypeOfCredential(CB_typeId.getValue());


            Set<ConstraintViolation<UserRegistrationDTO>> violations = validator.validate(registrationDTO);
            if (violations.isEmpty()) {
                try {
                    if (startUpService.registerNewUser(registrationDTO)) {
                        SceneManager.switchToScene(event, "pick-role-view.fxml");
                    }
                    else {
                        showAlert("Login Failed", Alert.AlertType.ERROR, "Information is not correct."
                        );
                    }
                } catch (Exception e) {
                    showAlert("Error", Alert.AlertType.ERROR, "Information is not correct.");
                }
            } else {
                String errorMessages = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining("\n"));
                showAlert("Validation Error", Alert.AlertType.ERROR, errorMessages);
            }
            SceneManager.switchToScene(event, "pick-role-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    @FXML
    private void On_T_LogIn(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void showAlert(String title, Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

