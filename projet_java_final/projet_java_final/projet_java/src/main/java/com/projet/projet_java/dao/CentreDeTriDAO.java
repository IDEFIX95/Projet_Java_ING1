package com.projet.projet_java.dao;

import com.projet.projet_java.model.CentreDeTri;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) pour gérer les accès aux Centres de Tri dans la base de données.
 */
public class CentreDeTriDAO {

    /**
     * Insère un nouveau centre de tri dans la base.
     * Vérifie d'abord qu'il n'existe pas déjà (même nom + même adresse).
     * @param centre CentreDeTri à insérer.
     * @return true si insertion réussie, false sinon.
     */
    public boolean insert(CentreDeTri centre) {
        if (exists(centre.getNom(), centre.getAdresse())) {
            System.err.println("⚠️ Centre de tri déjà existant (nom + adresse identiques).");
            return false;
        }

        String sql = "INSERT INTO CentreDeTri (nom, adresse) VALUES (?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, centre.getNom());
            stmt.setString(2, centre.getAdresse());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    centre.setIdCentre(rs.getInt(1)); // Mise à jour de l'ID du centre
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur insertion centre de tri : " + e.getMessage());
        }
        return false;
    }

    /**
     * Vérifie si un centre de tri existe déjà en base avec le même nom et la même adresse.
     * @param nom Nom du centre.
     * @param adresse Adresse du centre.
     * @return true si trouvé, false sinon.
     */
    public boolean exists(String nom, String adresse) {
        String sql = "SELECT COUNT(*) FROM CentreDeTri WHERE nom = ? AND adresse = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            stmt.setString(2, adresse);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur vérification doublon centre : " + e.getMessage());
        }

        return false;
    }

    /**
     * Récupère un centre de tri par son identifiant unique.
     * @param idCentre ID du centre.
     * @return CentreDeTri trouvé ou null.
     */
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

    /**
     * Récupère tous les centres de tri existants dans la base.
     * @return Liste de CentreDeTri.
     */
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

    /**
     * Récupère un centre de tri par son nom.
     * @param nom Nom du centre recherché.
     * @return CentreDeTri trouvé ou null.
     */
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

    /**
     * Méthode utilitaire : Convertit un ResultSet SQL en objet CentreDeTri.
     * @param rs Résultat SQL courant.
     * @return Objet CentreDeTri.
     * @throws SQLException si erreur SQL.
     */
    private CentreDeTri mapResultSetToCentre(ResultSet rs) throws SQLException {
        return new CentreDeTri(
                rs.getInt("idCentre"),
                rs.getString("nom"),
                rs.getString("adresse")
        );
    }
}
