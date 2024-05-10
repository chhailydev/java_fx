package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Label displayName;
    @FXML
    private AnchorPane welcomePage;
    private Stage stage;

    public void show(String user){
        displayName.setText("Mr/Mrs: " + user);
    }

    public void logout(ActionEvent e) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Logout Confirmation");
        alert.setContentText("Do wanna logout ?");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) welcomePage.getScene().getWindow();
            stage.close();
        }
    }
}
