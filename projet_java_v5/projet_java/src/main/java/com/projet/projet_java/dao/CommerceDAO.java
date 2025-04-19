package com.projet.projet_java.dao;

import com.projet.projet_java.model.Commerce;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommerceDAO {

    // 🔹 Insertion
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
                    commerce.setIdCommerce(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur insertion commerce : " + e.getMessage());
        }

        return false;
    }

    // 🔹 Récupérer tous les commerces
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
            System.err.println("❌ Erreur récupération commerces : " + e.getMessage());
        }

        return commerces;
    }

    // 🔹 Récupérer un commerce par ID
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
            System.err.println("❌ Erreur récupération commerce par ID : " + e.getMessage());
        }

        return null;
    }

    // ✅ Récupérer un commerce par nom
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
            System.err.println("❌ Erreur récupération commerce par nom : " + e.getMessage());
        }

        return null;
    }
}
