package org.tec.carpooling.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminAddInstAdminController {
    //Inicializacion de controles de la pantalla [Admin: Add Institution]
    @FXML
    private ListView<String> addAdminListView;
    @FXML
    private TextField institutionNameTextField;
    @FXML
    private TextField institutionEmailTextField;
    @FXML
    private TextField institutionWebsiteURLTextField;
    @FXML
    private Button createInstitutionButton;

    @FXML
    private void onCreateInstitutionButton(ActionEvent event) {
        String institutionName = institutionNameTextField.getText();
        String institutionEmail = institutionEmailTextField.getText();
        String institutionWebsite = institutionWebsiteURLTextField.getText();

        if (institutionName.isEmpty() || institutionEmail.isEmpty() || institutionWebsite.isEmpty()) {
            showAlert("There Are Blank Spaces", Alert.AlertType.ERROR, "Fill all the fields.");
            return;
        }

        showAlert("Institution Added Succesfully ", Alert.AlertType.INFORMATION, "The institution has been added successfully.");
    }

    @Autowired
    public AdminAddInstAdminController() {}

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
