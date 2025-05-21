package org.tec.carpooling.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;

@Controller
public class DriverRideController {

    @FXML
    private javafx.scene.control.Spinner<Integer> passangerCapacitySpinner;

    @FXML
    private javafx.scene.control.DatePicker dateTripDatePicker;

    @FXML
    private javafx.scene.control.TextField arrivesAtTextField1;

    @FXML
    private javafx.scene.control.TextField leavesAtTextField1;

    @FXML
    private javafx.scene.control.TextField arrivesAtTextField2;

    @FXML
    private javafx.scene.control.TextField leavesAtTextField2;

    @FXML
    private javafx.scene.control.TextField costTextField1;

    @FXML
    private javafx.scene.control.TextField costTextField2;

    @FXML
    private javafx.scene.control.Button publishRideButton;

    @FXML
    private javafx.scene.control.ComboBox<String> payMethodComboBox1;

    @FXML
    private javafx.scene.control.ComboBox<String> payMethodComboBox2;

    @FXML
    private javafx.scene.layout.Pane historyPane;

    @FXML
    private javafx.scene.layout.Pane scheduleRidePane;

    @FXML
    private javafx.scene.layout.Pane publishRidePane;


    @FXML
    private void onPublishRideButton() {
        // Verificar campos vacíos
        if (dateTripDatePicker.getValue() == null ||
                passangerCapacitySpinner.getValue() == null ||
                arrivesAtTextField1.getText().isEmpty() ||
                leavesAtTextField1.getText().isEmpty() ||
                arrivesAtTextField2.getText().isEmpty() ||
                leavesAtTextField2.getText().isEmpty() ||
                costTextField1.getText().isEmpty() ||
                costTextField2.getText().isEmpty() ||
                payMethodComboBox1.getValue() == null ||
                payMethodComboBox2.getValue() == null) {

            showAlert("Error", Alert.AlertType.ERROR, "Por favor complete todos los campos.");
            return;
        }

        // Validar fecha
        if (!dateTripDatePicker.getValue().isAfter(java.time.LocalDate.now())) {
            showAlert("Error", Alert.AlertType.ERROR, "La fecha del viaje debe ser futura.");
            return;
        }

        // Validar capacidad
        if (passangerCapacitySpinner.getValue() >= 4) {
            showAlert("Error", Alert.AlertType.ERROR, "La capacidad máxima permitida es 3 pasajeros.");
            return;
        }

        // Validar horas (formato HH:mm)
        try {
            java.time.LocalTime a1 = java.time.LocalTime.parse(arrivesAtTextField1.getText());
            java.time.LocalTime l1 = java.time.LocalTime.parse(leavesAtTextField1.getText());
            java.time.LocalTime a2 = java.time.LocalTime.parse(arrivesAtTextField2.getText());
            java.time.LocalTime l2 = java.time.LocalTime.parse(leavesAtTextField2.getText());

            if (!(a1.isBefore(l1) && l1.isBefore(a2) && a2.isBefore(l2))) {
                showAlert("Error", Alert.AlertType.ERROR, "Las horas deben estar en orden: Arrives1 < Leaves1 < Arrives2 < Leaves2");
                return;
            }
        } catch (Exception e) {
            showAlert("Error", Alert.AlertType.ERROR, "Formato de hora inválido. Use HH:mm (ej. 14:30).");
            return;
        }

        // Validar costos
        try {
            Double.parseDouble(costTextField1.getText());
            Double.parseDouble(costTextField2.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", Alert.AlertType.ERROR, "Los costos deben ser valores numéricos.");
            return;
        }
    }

    @FXML
    private void goToHistoryPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToScheduleRidePane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver-scheduled-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToPublishRidePane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver-ride-view.fxml");
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