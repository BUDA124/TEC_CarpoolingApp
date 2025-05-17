package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.da.entities.GenderEntity;

import java.util.List;

@Component
public class RegistrationController {

    @FXML private ComboBox<GenderEntity> genderComboBox;

    @Autowired
    private SimpleDataRetrievalService simpleDataRetrievalService;

    @Autowired
    public RegistrationController() {}

    @FXML
    public void initialize() {
        // Load Genders
        List<GenderEntity> genders = simpleDataRetrievalService.getAllGenders();
        genderComboBox.setItems(FXCollections.observableArrayList(genders));

        genderComboBox.setConverter(new StringConverter<GenderEntity>() {
            @Override
            public String toString(GenderEntity gender) {
                return gender != null ? gender.getGenderName() : "";
            }

            @Override
            public GenderEntity fromString(String string) {
                return genderComboBox.getItems().stream()
                        .filter(g -> g.getGenderName().equals(string))
                        .findFirst().orElse(null);
            }
        });
    }

    @FXML
    private void handleRegister() {}

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}