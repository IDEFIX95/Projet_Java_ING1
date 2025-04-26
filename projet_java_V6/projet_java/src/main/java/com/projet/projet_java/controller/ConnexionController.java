package com.projet.projet_java.controller;

import com.projet.projet_java.utils.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexionController {

    @FXML
    private TextField pseudoField; // <<< bien utilisé
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label connexionMessageLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        String pseudo = pseudoField.getText(); // <<< corrigé ici : pseudoField et pas usernameField
        String motDePasse = passwordField.getText();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM utilisateurs WHERE pseudo = ? AND mot_de_passe = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pseudo);
            statement.setString(2, motDePasse);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                connexionMessageLabel.setText("Connexion réussie !");
            } else {
                connexionMessageLabel.setText("Identifiants incorrects !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connexionMessageLabel.setText("Erreur lors de la connexion !");
        }
    }
}



