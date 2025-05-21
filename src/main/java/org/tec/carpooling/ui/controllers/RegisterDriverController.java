package org.tec.carpooling.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;

@Controller
public class RegisterDriverController {

    @FXML private TextField TF_carModel;
    @FXML private TextField TF_carColor;
    @FXML private TextField TF_licencePlate;
    @FXML private TextField TF_licenseId;
    @FXML private Spinner SB_modelYear;
    @FXML private Spinner SB_capacityPassangers;
    @FXML private Spinner SB_yearsExperience;
    @FXML private DatePicker DP_expirationDate;
    @FXML private CheckBox CheckB_termsAndConditions;
    @FXML private Button BTN_submitDriverRegistration;

    @FXML
    public void initialize() {

        int currentYear = Year.now().getValue();

        SpinnerValueFactory<Integer> modelYearValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1990, currentYear, currentYear);
        SB_modelYear.setValueFactory(modelYearValueFactory);
        SB_modelYear.setEditable(true);

        SpinnerValueFactory<Integer> capacityValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7, 4);
        SB_capacityPassangers.setValueFactory(capacityValueFactory);
        SB_capacityPassangers.setEditable(true);

        SpinnerValueFactory<Integer> experienceValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0);
        SB_yearsExperience.setValueFactory(experienceValueFactory);
        SB_yearsExperience.setEditable(true);

    }

    @FXML
    private void On_BTN_submitDriverRegistration(ActionEvent event) throws IOException {
        String carModel = TF_carModel.getText();
        String carColor = TF_carColor.getText();
        String licencePlate = TF_licencePlate.getText();
        String licenseId = TF_licenseId.getText();
        Integer modelYear = (Integer) SB_modelYear.getValue();
        Integer capacityPassengers = (Integer) SB_capacityPassangers.getValue();
        Integer yearsExperience = (Integer) SB_yearsExperience.getValue();
        LocalDate expirationDate = DP_expirationDate.getValue();
        boolean termsAccepted = CheckB_termsAndConditions.isSelected();

        StringBuilder errors = new StringBuilder();

        if (carModel == null || carModel.isBlank()) errors.append("- El modelo del auto es obligatorio.\n");
        if (carColor == null || carColor.isBlank()) errors.append("- El color del auto es obligatorio.\n");
        if (licencePlate == null || licencePlate.isBlank()) errors.append("- La placa es obligatoria.\n");
        if (licenseId == null || licenseId.isBlank()) errors.append("- El ID de licencia es obligatorio.\n");
        if (modelYear == null) errors.append("- El año del modelo es obligatorio.\n");
        if (capacityPassengers == null) errors.append("- La capacidad de pasajeros es obligatoria.\n");
        if (yearsExperience == null) errors.append("- Los años de experiencia son obligatorios.\n");
        if (expirationDate == null) errors.append("- La fecha de expiración es obligatoria.\n");
        if (!termsAccepted) errors.append("- Debes aceptar los términos y condiciones.\n");

        if (licenseId != null && !licenseId.matches("\\d+"))
            errors.append("- El ID de licencia debe contener solo números.\n");

        if (licencePlate != null && !licencePlate.matches("\\d+"))
            errors.append("- La placa debe contener solo números.\n");

        int currentYear = Year.now().getValue();
        if (modelYear != null && modelYear > currentYear)
            errors.append("- El año del modelo no puede ser mayor al actual.\n");

        if (yearsExperience != null && yearsExperience > 50)
            errors.append("- Los años de experiencia no pueden ser mayores a 50.\n");

        if (expirationDate != null && !expirationDate.isAfter(LocalDate.now()))
            errors.append("- La fecha de expiración debe ser posterior al día de hoy.\n");

        if (errors.length() > 0) {
            showAlert("Errores de validación", Alert.AlertType.ERROR, errors.toString());
        } else {
            showAlert("Registro exitoso", Alert.AlertType.INFORMATION, "Los datos fueron registrados correctamente.");
            try {
                SceneManager.switchToScene(event, "pick-role-view.fxml");
            } catch (IOException e) {
                e.printStackTrace();

            }
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