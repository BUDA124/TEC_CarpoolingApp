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
import org.tec.carpooling.bl.services.AuditLogService;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.bl.services.StartUpService;
import org.tec.carpooling.common.constants.AppConstants;
import org.tec.carpooling.da.entities.*;
import org.tec.carpooling.da.repositories.UserStatusRepository;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;
import java.time.LocalDate;
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
    @FXML private CheckBox CB_termsAndConditions;
    @FXML private ComboBox<GenderEntity> CB_gender;
    @FXML private ComboBox<CountryEntity> CB_country;
    @FXML private ComboBox<TypeOfCredentialEntity> CB_typeId;
    @FXML private ComboBox<InstitutionEntity> CB_institution;
    @FXML private DatePicker DP_birthdate;
    @FXML private Text T_LogIn;
    @FXML private Button BTN_enter;

    @Autowired private SimpleDataRetrievalService simpleDataRetrievalService;
    @Autowired private StartUpService startUpService;
    @Autowired private AuditLogService auditLogService;

    private final Validator validator = AppConstants.getValidator();
    @Autowired
    private UserStatusRepository userStatusRepository;

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

        List<TypeOfCredentialEntity> typesOfCredentialsList = simpleDataRetrievalService.getAllTypeOfCredentials();
        ObservableList<TypeOfCredentialEntity> observableTypesOfCredentials = FXCollections.observableArrayList(typesOfCredentialsList);
        CB_typeId.setItems(observableTypesOfCredentials);
        CB_typeId.setConverter(new StringConverter<TypeOfCredentialEntity>() {
            @Override
            public String toString(TypeOfCredentialEntity typeOfCredential) {
                return typeOfCredential != null ? typeOfCredential.getName() : "";
            }

            @Override
            public TypeOfCredentialEntity fromString(String string) {
                return observableTypesOfCredentials.stream()
                        .filter(typeOfCredential -> typeOfCredential != null && typeOfCredential.getName().equalsIgnoreCase(string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    @FXML
    private void on_BTN_enter(ActionEvent event) {
        String firstName = TF_firstName.getText().trim();
        String secondName = TF_secondName.getText().trim();
        String firstSurname = TF_firstSurname.getText().trim();
        String secondSurname = TF_secondSurname.getText().trim();
        String personalEmail = TF_personalEmail.getText().trim();
        String idNumber = TF_idNumber.getText().trim();
        String username = TF_username.getText().trim();
        String password = TF_password.getText().trim();
        String confirmPassword = TF_confirmPassword.getText().trim();
        String institutionalEmail = TF_institutionalEmail.getText().trim();
        LocalDate birthDate = DP_birthdate.getValue();

        GenderEntity selectedGender = CB_gender.getValue();
        CountryEntity selectedCountry = CB_country.getValue();
        InstitutionEntity selectedInstitution = CB_institution.getValue();
        TypeOfCredentialEntity selectedType = CB_typeId.getValue();

        List<String> errorMessages = new ArrayList<>();

        if (firstName.isBlank()) {
            errorMessages.add("El primer nombre es obligatorio.");
        }
        if (firstSurname.isBlank()) {
            errorMessages.add("El primer apellido es obligatorio.");
        }
        if (personalEmail.isBlank()) {
            errorMessages.add("El correo personal es obligatorio.");
        }
        if (idNumber.isBlank()) {
            errorMessages.add("El número de identificación es obligatorio.");
        }
        if (username.isBlank()) {
            errorMessages.add("El nombre de usuario es obligatorio.");
        }
        if (password.isBlank()) {
            errorMessages.add("La contraseña es obligatoria.");
        }
        if (!password.equals(confirmPassword)) {
            errorMessages.add("Las contraseñas no coinciden.");
        }
        if (institutionalEmail.isBlank()) {
            errorMessages.add("El correo institucional es obligatorio.");
        }
        if (selectedGender == null) {
            errorMessages.add("Debe seleccionar un género.");
        }
        if (selectedCountry == null) {
            errorMessages.add("Debe seleccionar un país de nacionalidad.");
        }
        if (selectedInstitution == null) {
            errorMessages.add("Debe seleccionar una institución.");
        }
        if (selectedType == null) {
            errorMessages.add("Debe seleccionar un tipo de identificación.");
        }
        if (!errorMessages.isEmpty()) {
            String fullErrorMessage = String.join("\n", errorMessages);
            showAlert("Campos Inválidos", Alert.AlertType.WARNING, fullErrorMessage);
            return;
        }
        if (birthDate == null) {
            String fullErrorMessage = String.join("\n", errorMessages);
            showAlert("Campos Inválidos", Alert.AlertType.WARNING, fullErrorMessage);
            return;
        }
        if (!CB_termsAndConditions.isSelected()) {
            showAlert("Campos Inválidos", Alert.AlertType.WARNING, "Se deben aceptar los términos y condiciones.");
            return;
        }


        try {
            AuditLogEntity auditLog = auditLogService.createInitialAuditLog(AppConstants.SYSTEM_USER);
            Optional<UserStatusEntity> optionalUserStatus = userStatusRepository.findByStatus(AppConstants.USER_STATUS_IS_PASSENGER );


            if (optionalUserStatus.isEmpty()) {
                showAlert("Loading Exception", Alert.AlertType.ERROR, "Error loading status");
            }
            UserStatusEntity userStatusEntity = optionalUserStatus.get();

            UserRegistrationDTO registrationDTO = new UserRegistrationDTO();
            registrationDTO.setFirstName(firstName);
            registrationDTO.setSecondName(secondName);
            registrationDTO.setFirstSurname(firstSurname);
            registrationDTO.setSecondSurname(secondSurname);
            registrationDTO.setEmail(personalEmail);
            registrationDTO.setCredentialNumber(idNumber);
            registrationDTO.setUsername(username);
            registrationDTO.setPassword(password);
            registrationDTO.setBirthdate(birthDate);
            registrationDTO.setInstitutionalEmail(institutionalEmail);
            registrationDTO.setIdGender(selectedGender);
            registrationDTO.setNationality(selectedCountry.getName());
            registrationDTO.setIdInstitution(selectedInstitution);
            registrationDTO.setIdTypeOfCredential(selectedType);
            registrationDTO.setAuditLog(auditLog);
            registrationDTO.setUserStatus(userStatusEntity);

            Set<ConstraintViolation<UserRegistrationDTO>> violations = validator.validate(registrationDTO);
            if (violations.isEmpty()) {
                if (startUpService.registerNewUser(registrationDTO)) {
                    showAlert("Registro Exitoso", Alert.AlertType.INFORMATION, "Usuario registrado correctamente.");
                    SceneManager.switchToScene(event, "login-view.fxml");
                } else {
                    showAlert("Fallo en el Registro", Alert.AlertType.ERROR, "La información no es correcta o el usuario ya existe.");
                }
            } else {
                String validationErrors = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining("\n"));
                showAlert("Error de Validación", Alert.AlertType.ERROR, validationErrors);
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error de Navegación", Alert.AlertType.ERROR, "No se pudo cambiar de pantalla.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error Inesperado", Alert.AlertType.ERROR, "Ocurrió un error al procesar el registro: " + e.getMessage());
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

