package org.tec.carpooling.ui.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Controller;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;

@Controller
public class AdminCheckInstDetailView {

    @FXML
    private void initialize() {

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
