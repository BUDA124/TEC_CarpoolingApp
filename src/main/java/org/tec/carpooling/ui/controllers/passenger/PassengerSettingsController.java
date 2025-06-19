package org.tec.carpooling.ui.controllers.passenger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.dto.UI_BL.UpdateUserProfileDTO;
import org.tec.carpooling.bl.dto.UserProfileDTO;
import org.tec.carpooling.bl.services.UserProfileService;
import org.tec.carpooling.common.exceptions.NotFoundException;
import org.tec.carpooling.common.exceptions.OperationFailedException;
import org.tec.carpooling.common.exceptions.ValidationException;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.ui.SceneManager;
import org.tec.carpooling.ui.UserSession;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class PassengerSettingsController {

    @FXML private Text T_fullName;
    @FXML private Text T_idProfile;
    @FXML private TextField TF_personalEmail;
    @FXML private TextField TF_institutionalEmail;
    @FXML private ComboBox<GenderEntity> CB_gender;
    @FXML private DatePicker DP_birthdate;
    @FXML private TextField TF_currentPassword;
    @FXML private PasswordField TF_newPassword;
    @FXML private PasswordField TF_confirmNewPassword;
    @FXML private Button BTN_edit;

    // Se eliminan los repositorios y el SimpleDataRetrievalService.
    // Solo necesitamos el servicio de perfil de usuario.
    @Autowired private UserProfileService userProfileService;


    @FXML
    public void initialize() {
        try {
            // 1. Llamada única al servicio para obtener todos los datos necesarios
            String currentUsername = UserSession.getInstance().getCurrentUser(); // Asumiendo una sesión de usuario
            UserProfileDTO profileData = userProfileService.getUserProfile(currentUsername);

            // 2. Poblar la UI con los datos del DTO
            T_fullName.setText(profileData.getFullName());
            T_idProfile.setText(profileData.getIdProfile());
            TF_personalEmail.setText(profileData.getPersonalEmail());
            TF_institutionalEmail.setText(profileData.getInstitutionalEmail());
            DP_birthdate.setValue(profileData.getBirthdate());

            ObservableList<GenderEntity> observableGenders = FXCollections.observableArrayList(profileData.getAllGenders());
            CB_gender.setItems(observableGenders);
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

            CB_gender.setValue(profileData.getCurrentGender());

        } catch (NotFoundException e) {
            e.printStackTrace();
            showError("Could not load user profile data: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            showError("An unexpected error occurred while loading the profile.");
        }
    }

    @FXML
    public void on_BTN_edit(ActionEvent event) {
        // 1. Recoger datos de la UI
        String personalEmail = TF_personalEmail.getText().trim();
        String institutionalEmail = TF_institutionalEmail.getText().trim();
        GenderEntity selectedGender = CB_gender.getValue();
        LocalDate birthdate = DP_birthdate.getValue();
        String currentPassword = TF_currentPassword.getText();
        String newPassword = TF_newPassword.getText();
        String confirmNewPassword = TF_confirmNewPassword.getText();

        // 2. Validaciones que pertenecen a la UI
        if (personalEmail.isEmpty() || institutionalEmail.isEmpty() || selectedGender == null || birthdate == null) {
            showError("Please fill in all required fields (emails, gender, birthdate).");
            return;
        }

        boolean isChangingPassword = !currentPassword.isEmpty() || !newPassword.isEmpty() || !confirmNewPassword.isEmpty();
        if (isChangingPassword && !newPassword.equals(confirmNewPassword)) {
            showError("The new passwords do not match.");
            return;
        }

        if (isChangingPassword && newPassword.isEmpty()) {
            showError("The new password cannot be empty.");
            return;
        }

        try {
            // 3. Crear DTO para enviar al servicio
            UpdateUserProfileDTO updateDto = new UpdateUserProfileDTO(
                    UserSession.getInstance().getCurrentUser(),
                    personalEmail,
                    institutionalEmail,
                    selectedGender,
                    birthdate,
                    currentPassword,
                    isChangingPassword ? newPassword : null // Enviar null si no se cambia
            );

            // 4. Llamar al servicio para que haga el trabajo
            userProfileService.updateUserProfile(updateDto);

            showSuccess("Profile updated successfully!");

        } catch (ValidationException e) {
            showError(e.getMessage()); // Muestra el mensaje de error de validación del servicio
        } catch (NotFoundException | OperationFailedException e) {
            e.printStackTrace();
            showError("An error occurred while updating the profile. Please try again.");
        }
    }


    @FXML
    private void goToLookRide(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Look Ride view.");
        }
    }

    @FXML
    private void goToScheduledRides(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-scheduled-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Scheduled Rides view.");
        }
    }

    @FXML
    private void goToHistory(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passanger-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load History view.");
        }
    }

    @FXML
    private void goToAnalytics(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-stats-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Analytics view.");
        }
    }

    @FXML
    private void goToSettings(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger/passenger-settings-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Settings view.");
        }
    }

    @FXML
    private void goToSignOut(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not sign out.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}