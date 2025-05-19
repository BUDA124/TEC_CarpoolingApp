package org.tec.carpooling.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;
import org.springframework.stereotype.Controller;

@Controller
public class AdminReportsController {

    @FXML
    private Pane historyPane;
    @FXML
    private Pane analyticsPane;
    @FXML
    private Pane requestsPane;
    @FXML
    private Pane viewInstitutionPane;

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
    private void onViewInstitutionPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "admin-check-inst-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    @Autowired
    public AdminReportsController() {}

    @FXML
    public void initialize() {

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}