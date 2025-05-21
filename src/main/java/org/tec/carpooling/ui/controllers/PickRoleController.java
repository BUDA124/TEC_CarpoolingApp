package org.tec.carpooling.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Controller;
import org.tec.carpooling.bl.dto.BL_DA.UserDTO;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.da.entities.PersonalUserEntity;
import org.tec.carpooling.da.repositories.DriverRepository;
import org.tec.carpooling.da.repositories.PersonRepository;
import org.tec.carpooling.da.repositories.PersonalUserRepository;
import org.tec.carpooling.ui.SceneManager;
import org.tec.carpooling.ui.UserSession;
import org.yaml.snakeyaml.nodes.AnchorNode;

import java.io.IOException;
import java.util.Optional;

@Controller
public class PickRoleController {

    @FXML
    private AnchorPane AP_driverWheel;
    @FXML
    private AnchorPane AP_passangerSeat;
    @FXML
    private Button registerDriverButton;


    @Autowired
    private PersonalUserRepository personalUserRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DriverRepository driverRepository;

    @FXML
    private void onPassangerPane(MouseEvent event) {
        try {
            SceneManager.switchToScene(event, "passenger-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void onDriverPane(MouseEvent event) {
        UserSession session = UserSession.getInstance();
        UserDTO currentUser = session.getCurrentUser();

        String username = currentUser.getUsername();

        Optional<PersonalUserEntity> optionalUser = personalUserRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            Optional<PersonEntity> person = personRepository.findById(optionalUser.get().getPerson().getId());
            if (person.isPresent()) {
                person.get().getId();
            }


        } else {
            showError("Usuario no encontrado.");
        }
    }



    @FXML
    private void onRegisterDriverButton(ActionEvent event) {
        try {



            SceneManager.switchToScene(event, "register-driver-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

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