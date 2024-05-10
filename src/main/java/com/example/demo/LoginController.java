package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome.fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root, 800, 600);
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();

        String user = username.getText();
        String password = pass.getText();

        if(user.equals("chhaily") && password.equals("123")){
            WelcomeController c = fxmlLoader.getController();
            c.show(user);
            stage.setScene(scene);
            stage.show();
        }else{
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("LOGIN");
            al.setHeaderText("Invalid username and password");
            al.setContentText("Let's try again!");
            al.showAndWait();
        }
    }
}
