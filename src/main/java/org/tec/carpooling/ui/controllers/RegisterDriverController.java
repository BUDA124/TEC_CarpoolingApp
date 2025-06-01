package org.tec.carpooling.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
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
    @FXML private Button BTN_cancelDriverRegistration;

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




    }

    @FXML
    private void On_BTN_cancelDriverRegistration(MouseEvent event) {
        try {
            ConfirmationController controller = SceneManager.showPopupWindowAndGetController(event, "confirmation-view.fxml");

            if (controller != null && controller.isOkPressed()) { // If "OK" was pressed on the popup
                SceneManager.switchToScene(event, "pick-role-view.fxml");
            }
        } catch (IOException e) {
            System.err.println("Failed to load confirmation dialog: " + e.getMessage());
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