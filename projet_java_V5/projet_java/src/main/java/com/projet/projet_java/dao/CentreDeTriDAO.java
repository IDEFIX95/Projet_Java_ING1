package com.projet.projet_java.dao;

import com.projet.projet_java.model.CentreDeTri;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CentreDeTriDAO {

    // 🔹 Ajouter un centre de tri
    public boolean insert(CentreDeTri centre) {
        String sql = "INSERT INTO CentreDeTri (nom, adresse) VALUES (?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, centre.getNom());
            stmt.setString(2, centre.getAdresse());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    centre.setIdCentre(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur insertion centre de tri : " + e.getMessage());
        }
        return false;
    }

    // 🔹 Récupérer un centre par ID
    public CentreDeTri getById(int idCentre) {
        String sql = "SELECT * FROM CentreDeTri WHERE idCentre = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCentre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToCentre(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération par ID : " + e.getMessage());
        }

        return null;
    }

    // 🔹 Récupérer tous les centres
    public List<CentreDeTri> getAll() {
        List<CentreDeTri> centres = new ArrayList<>();
        String sql = "SELECT * FROM CentreDeTri";

        try (Connection conn = ConnexionBDD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                centres.add(mapResultSetToCentre(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération des centres : " + e.getMessage());
        }

        return centres;
    }

    // 🔹 Récupérer un centre par nom
    public CentreDeTri getByNom(String nom) {
        String sql = "SELECT * FROM CentreDeTri WHERE nom = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToCentre(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération par nom : " + e.getMessage());
        }

        return null;
    }

    // 🔁 Mapping SQL → Objet Java
    private CentreDeTri mapResultSetToCentre(ResultSet rs) throws SQLException {
        return new CentreDeTri(
                rs.getInt("idCentre"),
                rs.getString("nom"),
                rs.getString("adresse")
        );
    }
}
