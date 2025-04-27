package com.projet.projet_java.dao;

import com.projet.projet_java.model.Commerce;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) pour gérer les accès à la table Commerce dans la base de données.
 */
public class CommerceDAO {

    /**
     * Insère un nouveau commerce en base de données.
     * @param commerce Commerce à ajouter.
     * @return true si insertion réussie, false sinon.
     */
    public boolean insert(Commerce commerce) {
        String sql = "INSERT INTO Commerce (nom, adresse) VALUES (?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, commerce.getNom());
            stmt.setString(2, commerce.getAdresse());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    commerce.setIdCommerce(rs.getInt(1)); // Mise à jour de l'ID après insertion
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println(" Erreur insertion commerce : " + e.getMessage());
        }

        return false;
    }

    /**
     * Récupère tous les commerces présents en base de données.
     * @return Liste de Commerce.
     */
    public List<Commerce> getAll() {
        List<Commerce> commerces = new ArrayList<>();
        String sql = "SELECT * FROM Commerce";

        try (Connection conn = ConnexionBDD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Commerce c = new Commerce(
                        rs.getInt("idCommerce"),
                        rs.getString("nom"),
                        rs.getString("adresse")
                );
                commerces.add(c);
            }

        } catch (SQLException e) {
            System.err.println(" Erreur récupération commerces : " + e.getMessage());
        }

        return commerces;
    }

    /**
     * Récupère un commerce par son identifiant.
     * @param idCommerce Identifiant du commerce recherché.
     * @return Commerce trouvé ou null si absent.
     */
    public Commerce getById(int idCommerce) {
        String sql = "SELECT * FROM Commerce WHERE idCommerce = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCommerce);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Commerce(
                        rs.getInt("idCommerce"),
                        rs.getString("nom"),
                        rs.getString("adresse")
                );
            }

        } catch (SQLException e) {
            System.err.println(" Erreur récupération commerce par ID : " + e.getMessage());
        }

        return null;
    }

    /**
     * Récupère un commerce par son nom.
     * @param nom Nom du commerce recherché.
     * @return Commerce trouvé ou null si absent.
     */
    public Commerce getByNom(String nom) {
        String sql = "SELECT * FROM Commerce WHERE nom = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Commerce(
                        rs.getInt("idCommerce"),
                        rs.getString("nom"),
                        rs.getString("adresse")
                );
            }

        } catch (SQLException e) {
            System.err.println(" Erreur récupération commerce par nom : " + e.getMessage());
        }

        return null;
    }
}
