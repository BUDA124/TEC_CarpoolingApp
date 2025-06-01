package org.tec.carpooling.ui.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.AdministratorEntity;
import org.tec.carpooling.da.entities.PersonEntity;

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
    private Pane viewInstitutionPane;

    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;


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

    private void showAlert(String title, Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
