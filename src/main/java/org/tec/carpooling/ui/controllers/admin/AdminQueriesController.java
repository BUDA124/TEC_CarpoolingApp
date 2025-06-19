package org.tec.carpooling.ui.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopDriverDTO;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopStopDTO;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopUserDTO;
import org.tec.carpooling.bl.services.GeneralQueriesService;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminQueriesController {

    @Autowired
    private GeneralQueriesService generalQueriesService;

    // FXML Component Bindings
    @FXML
    private ListView<TopDriverDTO> topDriversListView;
    @FXML
    private ListView<TopUserDTO> topUsersListView;
    @FXML
    private ListView<TopStopDTO> topStopsListView;
    @FXML
    private Text newUsersCountText;
    @FXML
    private Text averageFareText;


    @FXML
    public void initialize() {
        loadQueriesData();
    }

    private void loadQueriesData() {
        try {
            // 1. Cargar Top 5 Conductores
            List<TopDriverDTO> topDrivers = generalQueriesService.getTop5Drivers();
            ObservableList<TopDriverDTO> topDriversObservableList = FXCollections.observableArrayList(topDrivers);
            topDriversListView.setItems(topDriversObservableList);

            // 2. Cargar Top 5 Puntos Concurrentes (usando un rango por defecto, p.ej., últimos 90 días)
            // Nota: La UI debería tener DatePickers para que el usuario elija el rango.
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(5000);
            List<TopStopDTO> topStops = generalQueriesService.getTop5ConcurrentStops(startDate, endDate);
            ObservableList<TopStopDTO> topStopsObservableList = FXCollections.observableArrayList(topStops);
            topStopsListView.setItems(topStopsObservableList);

            // 3. Cargar Top 5 Usuarios Activos
            List<TopUserDTO> topUsers = generalQueriesService.getTop5ActiveUsers();
            ObservableList<TopUserDTO> topUsersObservableList = FXCollections.observableArrayList(topUsers);
            topUsersListView.setItems(topUsersObservableList);

            // 4. Cargar Promedio de Cobro
            Double avgFare = generalQueriesService.getAverageFare();
            averageFareText.setText(String.format("₡%.2f", avgFare));

            // 5. Cargar Usuarios Nuevos
            Long newUsers = generalQueriesService.getNewUsersInLast3Months();
            newUsersCountText.setText(String.valueOf(newUsers));

        } catch (Exception e) {
            e.printStackTrace();
            showError("Could not load query data. Please check the database connection and logs.");
        }
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