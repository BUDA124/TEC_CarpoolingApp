package org.tec.carpooling.ui.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.AdministratorEntity;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.ui.SceneManager;

@Controller
public class AdminAddInstController {
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

    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;


    @FXML
    private void onCreateInstitutionButton(ActionEvent event) {
        String institutionName = institutionNameTextField.getText();
        String institutionEmail = institutionEmailTextField.getText();
        String institutionWebsite = institutionWebsiteURLTextField.getText();

        if (institutionName.isEmpty() || institutionEmail.isEmpty() || institutionWebsite.isEmpty()) {
            showError("There Are Blank Spaces");
            return;
        }

        showError("Institution Added Succesfully ");
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
        addAdminListView.setItems(adminNames);
    }


    @FXML
    private void goToDailyReport(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin/admin-report-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Daily Report view.");
        }
    }

    @FXML
    private void goToGeneralQueries(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin/admin-queries-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load General Queries view.");
        }
    }

    @FXML
    private void goToHistory(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin/admin-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load History view.");
        }
    }

    @FXML
    private void goToAnalytics(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin/admin-stats-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Analytics view.");
        }
    }

    @FXML
    private void goToRequests(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin/admin-requests-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Requests view.");
        }
    }

    @FXML
    private void goToAddInstitution(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin/admin-add-inst-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load Add Institution view.");
        }
    }

    @FXML
    private void goToViewInstitution(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin/admin-check-inst-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load View Institution view.");
        }
    }

    @FXML
    private void goToSignOut(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not sign out");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
