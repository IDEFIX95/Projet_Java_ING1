package com.projet.projet_java.controller;

import com.projet.projet_java.utils.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscriptionController {

    @FXML
    private TextField prenomField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField pseudoField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label inscriptionMessageLabel;

    @FXML
    private void handleRegister(ActionEvent event) {
        String prenom = prenomField.getText();
        String nom = nomField.getText();
        String email = emailField.getText();
        String ageText = ageField.getText();
        String pseudo = pseudoField.getText();
        String motDePasse = passwordField.getText();

        if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || ageText.isEmpty() || pseudo.isEmpty() || motDePasse.isEmpty()) {
            inscriptionMessageLabel.setText("Veuillez remplir tous les champs !");
            return;
        }

        try {
            int age = Integer.parseInt(ageText);

            try (Connection connection = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO utilisateurs (prenom, nom, email, age, pseudo, mot_de_passe) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, prenom);
                statement.setString(2, nom);
                statement.setString(3, email);
                statement.setInt(4, age);
                statement.setString(5, pseudo);
                statement.setString(6, motDePasse);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    inscriptionMessageLabel.setText("Inscription réussie !");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                inscriptionMessageLabel.setText("Erreur lors de l'inscription !");
            }

        } catch (NumberFormatException e) {
            inscriptionMessageLabel.setText("L'âge doit être un nombre valide !");
        }
    }
}



