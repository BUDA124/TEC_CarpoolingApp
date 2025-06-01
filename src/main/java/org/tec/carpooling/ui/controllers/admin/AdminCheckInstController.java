package org.tec.carpooling.ui.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.InstitutionEntity;

import java.util.ArrayList;
import java.util.List;

@Controller

public class AdminCheckInstController {

    @FXML
    private ListView<String> institutionListView;


    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;

    @FXML
    public void initialize() {
        List<InstitutionEntity> institutions = simpleDataRetrievalService.getAllInstitutions();
        ArrayList<String> institutionNames = new ArrayList<>();

        for (InstitutionEntity institution : institutions) {
            institutionNames.add(institution.getInstitutionName());
        }

        ObservableList<String> institutionObservableList = FXCollections.observableArrayList(institutionNames);
        institutionListView.setItems(institutionObservableList);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}