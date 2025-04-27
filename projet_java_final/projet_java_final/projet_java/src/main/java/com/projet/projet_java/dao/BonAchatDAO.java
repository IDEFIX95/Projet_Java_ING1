package com.projet.projet_java.dao;

import com.projet.projet_java.model.BonAchat;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour l'accès aux données des Bons d'Achat en base.
 * Gère l'insertion et la récupération des bons.
 */
public class BonAchatDAO {

    /**
     * Insère un nouveau bon d'achat dans la base de données.
     * @param bon Le bon à insérer.
     * @return true si l'insertion réussit, false sinon.
     */
    public boolean insert(BonAchat bon) {
        String sql = "INSERT INTO BonAchat (idMenage, idCommerce, pointsUtilises, dateCreation, dateExpiration, categorie) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, bon.getIdMenage());
            stmt.setInt(2, bon.getIdCommerce());
            stmt.setInt(3, bon.getPointsUtilises());
            stmt.setDate(4, Date.valueOf(bon.getDateCreation()));
            stmt.setDate(5, Date.valueOf(bon.getDateExpiration()));
            stmt.setString(6, bon.getCategorie());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    bon.setIdBonAchat(rs.getInt(1)); //  MAJ de l'ID du bon après insertion
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println(" Erreur insertion bon d'achat : " + e.getMessage());
        }

        return false;
    }

    /**
     * Récupère tous les bons d'achat d'un ménage donné.
     * @param idMenage L'identifiant du ménage.
     * @return Liste des bons du ménage.
     */
    public List<BonAchat> getByMenage(int idMenage) {
        List<BonAchat> bons = new ArrayList<>();
        String sql = "SELECT * FROM BonAchat WHERE idMenage = ? ORDER BY dateCreation DESC";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BonAchat bon = extractBonFromResultSet(rs);
                bons.add(bon);
            }

        } catch (SQLException e) {
            System.err.println(" Erreur récupération bons par ménage : " + e.getMessage());
        }

        return bons;
    }

    /**
     * Récupère tous les bons d'achat de la base.
     * @return Liste de tous les bons existants.
     */
    public List<BonAchat> getAll() {
        List<BonAchat> bons = new ArrayList<>();
        String sql = "SELECT * FROM BonAchat";

        try (Connection conn = ConnexionBDD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BonAchat bon = extractBonFromResultSet(rs);
                bons.add(bon);
            }

        } catch (SQLException e) {
            System.err.println(" Erreur récupération de tous les bons : " + e.getMessage());
        }

        return bons;
    }

    /**
     * Convertit une ligne SQL (ResultSet) en objet BonAchat.
     * @param rs Résultat SQL en cours.
     * @return BonAchat correspondant.
     * @throws SQLException En cas de problème SQL.
     */
    private BonAchat extractBonFromResultSet(ResultSet rs) throws SQLException {
        return new BonAchat(
                rs.getInt("idBonAchat"),
                rs.getInt("idMenage"),
                rs.getInt("idCommerce"),
                rs.getInt("pointsUtilises"),
                rs.getDate("dateCreation").toLocalDate(),
                rs.getDate("dateExpiration").toLocalDate(),
                rs.getString("categorie")
        );
    }
}
