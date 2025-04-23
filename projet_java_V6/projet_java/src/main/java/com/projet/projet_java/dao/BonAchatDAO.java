package com.projet.projet_java.dao;

import com.projet.projet_java.model.BonAchat;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BonAchatDAO {

    // 🔸 Insertion complète avec dateExpiration et catégorie
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
                    bon.setIdBonAchat(rs.getInt(1)); // 🔄 MAJ correcte du setter
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur insertion bon d'achat : " + e.getMessage());
        }

        return false;
    }

    // 🔍 Récupération des bons par ménage
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
            System.err.println("❌ Erreur récupération bons par ménage : " + e.getMessage());
        }

        return bons;
    }

    // 🔍 Récupération de tous les bons
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
            System.err.println("❌ Erreur récupération de tous les bons : " + e.getMessage());
        }

        return bons;
    }

    // 🔧 Méthode utilitaire pour factoriser le mapping SQL → objet
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
