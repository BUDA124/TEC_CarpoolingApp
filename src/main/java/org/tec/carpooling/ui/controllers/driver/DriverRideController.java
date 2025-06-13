package org.tec.carpooling.ui.controllers.driver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.dto.UI_BL.driver.PublishTripDTO;
import org.tec.carpooling.bl.dto.UI_BL.driver.StopDTO;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.bl.services.TripService;
import org.tec.carpooling.da.entities.DistrictEntity;
import org.tec.carpooling.ui.SceneManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DriverRideController {

    @FXML private Spinner<Integer> S_passangerCapacity;
    @FXML private DatePicker DP_tripDay;
    @FXML private VBox VB_stopsContainer;
    @FXML private Button BTN_addStop;
    @FXML private Button BTN_publishRide;
    @FXML private Pane historyPane;
    @FXML private Pane scheduleRidePane;
    @FXML private Pane publishRidePane;
    @FXML private ComboBox<DistrictEntity> CB_startingLocation;
    @FXML private ComboBox<DistrictEntity> CB_endingLocation;

    @Autowired private TripService tripService;
    @Autowired private SimpleDataRetrievalService simpleDataRetrievalService;

    private List<DistrictEntity> availableSanJoseDistricts;

    @FXML
    public void initialize() {
        loadAndFilterAvailableLocations();

        ObservableList<DistrictEntity> districts = FXCollections.observableArrayList(availableSanJoseDistricts);
        CB_startingLocation.setItems(districts);
        CB_startingLocation.setConverter(createDistrictConverter(districts));
        CB_startingLocation.setPromptText("District");
        CB_startingLocation.setPrefWidth(150);
        CB_startingLocation.setPrefHeight(30);

        CB_endingLocation.setItems(districts);
        CB_endingLocation.setConverter(createDistrictConverter(districts));
        CB_endingLocation.setPromptText("District");
        CB_endingLocation.setPrefWidth(150);
        CB_endingLocation.setPrefHeight(30);
        setupSpinners();
        addStopRow();
    }

    private void loadAndFilterAvailableLocations() {
        availableSanJoseDistricts = simpleDataRetrievalService.getAllDistrictsInProvince("San José");
    }

    private void setupSpinners() {
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7, 4);
        S_passangerCapacity.setValueFactory(factory);
        S_passangerCapacity.setEditable(false);
    }

    @FXML
    private void addStopRow() {
        HBox stopRow = new HBox(15);
        stopRow.setAlignment(Pos.CENTER_LEFT);

        ComboBox<DistrictEntity> locationComboBox = createLocationComboBox();
        VBox leavesAtBox = createTimePicker("Leaves at");
        VBox arrivesAtBox = createTimePicker("Arrives at");
        VBox costBox = createCostInput();
        VBox paymentBox = createPaymentMethodInput();
        Button removeButton = createRemoveButton(stopRow);

        HBox.setMargin(locationComboBox, new Insets(20, 0, 0, 0));

        stopRow.getChildren().addAll(locationComboBox, leavesAtBox, arrivesAtBox, costBox, paymentBox, removeButton);
        VB_stopsContainer.getChildren().add(stopRow);
    }

    @FXML
    private void onPublishRideButton() {
        if (isTripFormInvalid()) {
            return;
        }
        PublishTripDTO rideToPublish = buildTripDTOFromForm();
        boolean isSuccess = tripService.publishTrip(rideToPublish);
        handlePublishResponse(isSuccess);
    }

    private void handlePublishResponse(boolean isSuccess) {
        if (isSuccess) {
            showAlert("Éxito", Alert.AlertType.INFORMATION, "¡Viaje publicado correctamente!");
            resetRideForm();
        } else {
            showAlert("Failure", Alert.AlertType.ERROR, "Error al publicar viaje. Intente nuevamente.");
        }
    }

    private boolean isTripFormInvalid() {
        return isTripHeaderInvalid() || areStopsInvalid() || areStopTimesInconsistent();
    }

    private boolean isTripHeaderInvalid() {
        if (DP_tripDay.getValue() == null || S_passangerCapacity.getValue() == null) {
            showAlert("Error", Alert.AlertType.ERROR, "Por favor complete la capacidad de pasajeros y la fecha del viaje.");
            return true;
        }
        if (DP_tripDay.getValue().isBefore(LocalDate.now().plusDays(1))) {
            showAlert("Error", Alert.AlertType.ERROR, "La fecha del viaje debe ser al menos para mañana.");
            return true;
        }
        return false;
    }

    private boolean areStopsInvalid() {
        if (VB_stopsContainer.getChildren().isEmpty()) {
            showAlert("Error", Alert.AlertType.ERROR, "Debe agregar al menos una parada al viaje.");
            return true;
        }
        for (Node node : VB_stopsContainer.getChildren()) {
            if (node instanceof HBox stopRow && isStopRowIncomplete(stopRow)) {
                showAlert("Error de Parada", Alert.AlertType.ERROR, "El lugar de la parada y el método de pago son obligatorios.");
                return true;
            }
        }
        return false;
    }

    private boolean isStopRowIncomplete(HBox stopRow) {
        ComboBox<?> locationComboBox = (ComboBox<?>) stopRow.getChildren().get(0);
        VBox paymentVBox = (VBox) stopRow.getChildren().get(4);
        ComboBox<?> paymentMethodCombo = (ComboBox<?>) paymentVBox.getChildren().get(1);
        return locationComboBox.getValue() == null || paymentMethodCombo.getValue() == null;
    }

    private boolean areStopTimesInconsistent() {
        LocalTime previousLeavesAt = null;
        for (Node node : VB_stopsContainer.getChildren()) {
            if (node instanceof HBox stopRow) {
                String locationName = ((ComboBox<DistrictEntity>) stopRow.getChildren().get(0)).getValue().getName();
                LocalTime arrivesAt = getTimeFromPicker((VBox) stopRow.getChildren().get(2));
                LocalTime leavesAt = getTimeFromPicker((VBox) stopRow.getChildren().get(1));

                if (isDepartureNotAfterArrival(leavesAt, arrivesAt)) {
                    showAlert("Error de Tiempo", Alert.AlertType.ERROR, "En la parada '" + locationName
                            + "', la hora de salida debe ser posterior a la de llegada.");
                    return true;
                }
                if (isArrivalNotAfterPreviousDeparture(arrivesAt, previousLeavesAt)) {
                    showAlert("Error de Secuencia", Alert.AlertType.ERROR, "La hora de llegada a '" + locationName
                            + "' debe ser posterior a la hora de salida de la parada anterior.");
                    return true;
                }
                previousLeavesAt = leavesAt;
            }
        }
        return false;
    }

    private boolean isDepartureNotAfterArrival(LocalTime departure, LocalTime arrival) {
        return !departure.isAfter(arrival);
    }

    private boolean isArrivalNotAfterPreviousDeparture(LocalTime arrival, LocalTime previousDeparture) {
        return previousDeparture != null && !arrival.isAfter(previousDeparture);
    }

    private PublishTripDTO buildTripDTOFromForm() {
        List<StopDTO> stopDTOs = new ArrayList<>();
        for (Node node : VB_stopsContainer.getChildren()) {
            if (node instanceof HBox) {
                stopDTOs.add(createStopDTOFromRow((HBox) node));
            }
        }
        return new PublishTripDTO(DP_tripDay.getValue(), S_passangerCapacity.getValue(), stopDTOs);
    }

    private StopDTO createStopDTOFromRow(HBox stopRow) {
        DistrictEntity district = ((ComboBox<DistrictEntity>) stopRow.getChildren().get(0)).getValue();
        LocalTime leavesAt = getTimeFromPicker((VBox) stopRow.getChildren().get(1));
        LocalTime arrivesAt = getTimeFromPicker((VBox) stopRow.getChildren().get(2));
        double cost = ((Spinner<Double>) ((VBox) stopRow.getChildren().get(3)).getChildren().get(1)).getValue();
        String paymentMethod = ((ComboBox<String>) ((VBox) stopRow.getChildren().get(4)).getChildren().get(1)).getValue();
        DistrictEntity startStop = CB_startingLocation.getValue();
        DistrictEntity endStop = CB_endingLocation.getValue();
        return new StopDTO(district.getName(), district, leavesAt, arrivesAt, cost, paymentMethod, startStop, endStop);
    }

    private void resetRideForm() {
        DP_tripDay.setValue(null);
        S_passangerCapacity.getValueFactory().setValue(4);
        VB_stopsContainer.getChildren().clear();
        addStopRow();
    }

    private ComboBox<DistrictEntity> createLocationComboBox() {
        ComboBox<DistrictEntity> comboBox = new ComboBox<>();
        ObservableList<DistrictEntity> districts = FXCollections.observableArrayList(availableSanJoseDistricts);
        comboBox.setItems(districts);
        comboBox.setConverter(createDistrictConverter(districts));
        comboBox.setPromptText("District");
        comboBox.setPrefWidth(150);
        comboBox.setPrefHeight(30);
        return comboBox;
    }

    private StringConverter<DistrictEntity> createDistrictConverter(ObservableList<DistrictEntity> districts) {
        return new StringConverter<>() {
            @Override
            public String toString(DistrictEntity district) {
                return district != null ? district.getName() : "";
            }

            @Override
            public DistrictEntity fromString(String string) {
                return districts.stream().filter(d -> d.getName().equals(string)).findFirst().orElse(null);
            }
        };
    }

    private VBox createTimePicker(String labelText) {
        VBox timeVBox = new VBox(5);
        timeVBox.setAlignment(Pos.CENTER);
        Label timeLabel = new Label(labelText);
        timeVBox.getChildren().addAll(timeLabel, createTimeSpinners());
        return timeVBox;
    }

    private HBox createTimeSpinners() {
        HBox spinnersHBox = new HBox(5);
        spinnersHBox.setAlignment(Pos.CENTER);

        Spinner<Integer> hourSpinner = new Spinner<>(0, 23, 12);
        configureTimeSpinner(hourSpinner);
        hourSpinner.setEditable(true);

        Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, 0, 5);
        configureTimeSpinner(minuteSpinner);
        minuteSpinner.setEditable(true);

        spinnersHBox.getChildren().addAll(hourSpinner, minuteSpinner);

        return spinnersHBox;
    }

    private void configureTimeSpinner(Spinner<Integer> spinner) {
        spinner.setPrefWidth(60);
        spinner.setPrefHeight(30);
        spinner.getValueFactory().setConverter(new TwoDigitConverter());
    }

    private VBox createCostInput() {
        VBox costBox = new VBox(5);
        costBox.setAlignment(Pos.CENTER);
        Label costLabel = new Label("Cost");
        Spinner<Double> costSpinner = new Spinner<>(0.0, 10000.0, 5.0, 1.0);
        costSpinner.setEditable(true);
        costSpinner.setPrefWidth(90);
        costSpinner.setPrefHeight(30);
        costBox.getChildren().addAll(costLabel, costSpinner);
        return costBox;
    }

    private VBox createPaymentMethodInput() {
        VBox paymentBox = new VBox(5);
        paymentBox.setAlignment(Pos.CENTER);
        Label paymentLabel = new Label("Payment Method");
        ComboBox<String> paymentMethodCombo = new ComboBox<>();
        paymentMethodCombo.getItems().addAll("Efectivo", "Tarjeta", "SINPE");
        paymentMethodCombo.setPromptText("Método");
        paymentMethodCombo.setPrefWidth(120);
        paymentMethodCombo.setPrefHeight(30);
        paymentBox.getChildren().addAll(paymentLabel, paymentMethodCombo);
        return paymentBox;
    }

    private Button createRemoveButton(HBox rowToRemove) {
        Button removeButton = new Button("X");
        removeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 14px;");
        removeButton.setOnAction(event -> VB_stopsContainer.getChildren().remove(rowToRemove));
        HBox container = new HBox(removeButton);
        container.setAlignment(Pos.BOTTOM_CENTER);
        return removeButton;
    }

    private LocalTime getTimeFromPicker(VBox timePickerBox) {
        HBox spinnersHBox = (HBox) timePickerBox.getChildren().get(1);
        Spinner<Integer> hourSpinner = (Spinner<Integer>) spinnersHBox.getChildren().get(0);
        Spinner<Integer> minuteSpinner = (Spinner<Integer>) spinnersHBox.getChildren().get(1);
        return LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
    }

    private void showAlert(String title, Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goToPublishRidePane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-ride-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToScheduleRidePane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-scheduled-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToHistoryPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-history-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSettings(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-settings-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSignOut(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "login-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToAnalytics(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "driver/driver-stats-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class TwoDigitConverter extends StringConverter<Integer> {
        @Override
        public String toString(Integer value) {
            return (value == null) ? "00" : String.format("%02d", value);
        }
        @Override
        public Integer fromString(String string) {
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}