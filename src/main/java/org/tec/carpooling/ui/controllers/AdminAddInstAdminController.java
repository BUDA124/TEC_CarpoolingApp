package org.tec.carpooling.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.AdministratorEntity;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.ui.SceneManager;

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
    private Pane dailyReportText;
    @FXML
    private Pane generalQueriesText;
    @FXML
    private Pane historyPane;
    @FXML
    private Pane analyticsPane;
    @FXML
    private Pane requestsPane;
    @FXML
    private ComboBox<String>  addAdminComboBox;

    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;

    @Autowired
    public AdminAddInstAdminController() {}

    @FXML
    private void onCreateInstitutionButton(ActionEvent event) {
        String institutionName = institutionNameTextField.getText();
        String institutionEmail = institutionEmailTextField.getText();
        String institutionWebsite = institutionWebsiteURLTextField.getText();
        String selectedAdmin = addAdminComboBox.getValue();

        if (institutionName.isEmpty() || institutionEmail.isEmpty() || institutionWebsite.isEmpty() || selectedAdmin == null || selectedAdmin.isEmpty()) {
            showAlert("There Are Blank Spaces", Alert.AlertType.ERROR, "Fill all the fields.");
            return;
        }

        showAlert("Institution Added Succesfully ", Alert.AlertType.INFORMATION, "The institution has been added successfully.");
    }

    @FXML
    private void onDailyReportText(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin-report-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void onGeneralQueriesText(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin-queries-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void onHistoryPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void onAnalyticsPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin-stats-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void onRequestPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin-requests-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void initialize() {
        List<AdministratorEntity> administrators = simpleDataRetrievalService.getAllAdministrators();
        List<PersonEntity> persons = simpleDataRetrievalService.getAllPersons();
        ArrayList<String> administratorNames = new ArrayList<>();

        for (int i = 0; i < persons.size(); i++) {
            for (int j = 0; j < administrators.size(); j++) {

                if (persons.get(i).getId() == administrators.get(j).getId()) {
                    String fullName = persons.get(i).getFirstName() + " " + persons.get(i).getFirstSurname();
                    administratorNames.add(fullName);

                }
            }
        }
        ObservableList<String> adminNames = FXCollections.observableArrayList(administratorNames);
        addAdminComboBox.setItems(adminNames);
    }

    private void showAlert(String title, Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
