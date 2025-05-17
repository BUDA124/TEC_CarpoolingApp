package org.tec.carpooling.ui.controllers; // Changed package

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.tec.carpooling.bl.services.UserService;
import org.tec.carpooling.common.exceptions.EntityNotFoundException;
import org.tec.carpooling.common.exceptions.UserRegistrationException;
import org.tec.carpooling.common.exceptions.ValidationException;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;
import org.tec.carpooling.da.entities.TypeOfCredentialEntity;
import org.tec.carpooling.da.repositories.GenderRepository;
import org.tec.carpooling.da.repositories.InstitutionRepository;
import org.tec.carpooling.da.repositories.TypeOfCredentialRepository;

@Component
public class RegistrationController {

    @FXML private TextField firstNameField;
    @FXML private TextField secondNameField;
    @FXML private TextField firstSurnameField;
    @FXML private TextField secondSurnameField;
    @FXML private TextField emailField;
    @FXML private TextField institutionalEmailField;
    @FXML private DatePicker birthdatePicker;
    @FXML private TextField nationalityField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField credentialNumberField;
    @FXML private ComboBox<InstitutionEntity> institutionComboBox;
    @FXML private ComboBox<GenderEntity> genderComboBox;
    @FXML private ComboBox<TypeOfCredentialEntity> typeOfCredentialComboBox;
    @FXML private Label messageLabel;

    private final UserService userService;
    private final InstitutionRepository institutionRepository;
    private final GenderRepository genderRepository;
    private final TypeOfCredentialRepository typeOfCredentialRepository;

    @Autowired
    public RegistrationController(UserService userService,
                                  InstitutionRepository institutionRepository,
                                  GenderRepository genderRepository,
                                  TypeOfCredentialRepository typeOfCredentialRepository) {
        this.userService = userService;
        this.institutionRepository = institutionRepository;
        this.genderRepository = genderRepository;
        this.typeOfCredentialRepository = typeOfCredentialRepository;
    }

    @FXML
    public void initialize() {
        institutionComboBox.getItems().addAll(institutionRepository.findAll());
        genderComboBox.getItems().addAll(genderRepository.findAll());
        typeOfCredentialComboBox.getItems().addAll(typeOfCredentialRepository.findAll());
        messageLabel.setText("");
    }

    @FXML
    private void handleRegister() {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        try {
            dto.setFirstName(firstNameField.getText());
            dto.setSecondName(secondNameField.getText());
            dto.setFirstSurname(firstSurnameField.getText());
            dto.setSecondSurname(secondSurnameField.getText());
            dto.setEmail(emailField.getText());
            dto.setInstitutionalEmail(institutionalEmailField.getText());
            dto.setBirthdate(birthdatePicker.getValue());
            dto.setNationality(nationalityField.getText());
            dto.setUsername(usernameField.getText());
            dto.setPassword(passwordField.getText());
            dto.setCredentialNumber(credentialNumberField.getText());

            InstitutionEntity selectedInstitution = institutionComboBox.getValue();
            if (selectedInstitution == null) {
                showError("Please select an institution.");
                return;
            }
            dto.setIdInstitution(selectedInstitution.getId());

            GenderEntity selectedGender = genderComboBox.getValue();
            if (selectedGender == null) {
                showError("Please select a gender.");
                return;
            }
            dto.setIdGender(selectedGender.getId());

            TypeOfCredentialEntity selectedTypeOfCredential = typeOfCredentialComboBox.getValue();
            if (selectedTypeOfCredential == null) {
                showError("Please select a type of credential.");
                return;
            }
            dto.setIdTypeOfCredential(selectedTypeOfCredential.getId());

            UserDTO registeredUser = userService.registerNewUser(dto); // userService is now injected
            showSuccess("Registration successful for user: " + registeredUser.getUsername() +
                        ". Please check your institutional email for verification (simulated).");
            // TODO: Navigate to login screen or a "check email" screen

        } catch (ValidationException | UserRegistrationException | EntityNotFoundException e) {
            showError(e.getMessage());
        } catch (NullPointerException npe) {
            showError("Please fill all required fields and selections. Details: " + npe.getMessage());
             npe.printStackTrace();
        }
         catch (Exception e) {
            showError("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        messageLabel.setText("Error: " + message);
        messageLabel.setStyle("-fx-text-fill: red;");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        messageLabel.setText("Success: " + message);
        messageLabel.setStyle("-fx-text-fill: green;");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}