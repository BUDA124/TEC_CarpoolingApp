package org.tec.carpooling.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class ConfirmationController {

    @FXML
    private Label messageLabel;
    private boolean okPressed = false;

    /**
     * You can use this method to set a custom message for the confirmation dialog
     * from the calling controller, if needed, before the popup is shown.
     * This would require getting the controller instance *before* showAndWait().
     * However, for the current SceneManager.showPopupWindowAndGetController,
     * you get the controller *after* it's shown and closed.
     * If the message is static, set it in FXML.
     */
    public void setMessage(String message) {
        if (messageLabel != null) {
            messageLabel.setText(message);
        } else {
            System.err.println("ConfirmationController: messageLabel is null. Cannot set message.");
        }
    }

    @FXML
    private void initialize() {}

    @FXML
    private void handleOkButton(ActionEvent event) {
        okPressed = true;
        closeDialog(event);
    }

    @FXML
    private void handleCancelButton(ActionEvent event) {
        okPressed = false;
        closeDialog(event);
    }

    private void closeDialog(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public boolean isOkPressed() {
        return okPressed;
    }
}