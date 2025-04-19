package com.projet.projet_java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnexionController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.equals("admin@tri.fr") && password.equals("1234")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/dashboard.fxml"));
                Parent root = loader.load();
                Scene dashboardScene = new Scene(root);

                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(dashboardScene);
                stage.setTitle("Tableau de bord");
            } catch (Exception e) {
                e.printStackTrace();
                errorLabel.setText("Erreur de chargement.");
                errorLabel.setVisible(true);
            }
        } else {
            errorLabel.setVisible(true);
        }
    }
}
