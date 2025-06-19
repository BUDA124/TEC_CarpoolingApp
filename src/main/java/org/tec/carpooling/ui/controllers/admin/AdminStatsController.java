package org.tec.carpooling.ui.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.dto.AdminDashboardStatsDTO;
import org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO;
import org.tec.carpooling.bl.services.StatsService;
import org.tec.carpooling.ui.SceneManager;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminStatsController {

    @Autowired
    private StatsService statsService;

    // Inyección de componentes FXML
    @FXML private PieChart ageGroupPieChart;
    @FXML private PieChart driversByGenderPieChart;
    @FXML private PieChart passengersByGenderPieChart;
    @FXML private LineChart<String, Number> peakTrafficLineChart;

    @FXML
    public void initialize() {
        Task<AdminDashboardStatsDTO> loadDataTask = new Task<>() {
            @Override
            protected AdminDashboardStatsDTO call() throws Exception {
                return statsService.getAdminDashboardStats();
            }
        };

        // El manejo del éxito ahora usa el DTO para obtener los datos.
        loadDataTask.setOnSucceeded(event -> {
            AdminDashboardStatsDTO stats = loadDataTask.getValue();
            populatePieChart(ageGroupPieChart, stats.getAgeGroupData());
            populatePieChart(driversByGenderPieChart, stats.getDriverGenderData());
            populatePieChart(passengersByGenderPieChart, stats.getPassengerGenderData());
            populateLineChart(peakTrafficLineChart, stats.getPeakTrafficData());
        });

        // El manejo de errores no cambia.
        loadDataTask.setOnFailed(e -> {
            showError("Failed to load statistics: " + loadDataTask.getException().getMessage());
            loadDataTask.getException().printStackTrace();
        });

        new Thread(loadDataTask).start();
    }

    private void populatePieChart(PieChart chart, List<ChartDataDTO> data) {
        if (data == null || data.isEmpty()) {
            chart.setData(FXCollections.observableArrayList(new PieChart.Data("No data", 1)));
            return;
        }

        ObservableList<PieChart.Data> pieChartData = data.stream()
                .map(dto -> new PieChart.Data(dto.getLabel() + " (" + dto.getValue() + ")", dto.getValue().doubleValue()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        chart.setData(pieChartData);
    }

    private void populateLineChart(LineChart<String, Number> chart, List<ChartDataDTO> data) {
        chart.getData().clear();

        if (data == null || data.isEmpty()) {
            return; // No hacer nada si no hay datos
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Trips");

        List<XYChart.Data<String, Number>> chartData = data.stream()
                .map(dto -> new XYChart.Data<>(String.format("%02d:00", Integer.parseInt(dto.getLabel())), dto.getValue()))
                .collect(Collectors.toList());

        series.getData().addAll(chartData);
        chart.getData().add(series);
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