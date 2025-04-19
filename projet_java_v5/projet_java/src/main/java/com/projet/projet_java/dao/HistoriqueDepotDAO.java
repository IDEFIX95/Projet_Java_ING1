package com.projet.projet_java.dao;

import com.projet.projet_java.model.Dechet;
import com.projet.projet_java.model.HistoriqueDepot;
import com.projet.projet_java.model.TypeDechet;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueDepotDAO {

    // 🔹 Insertion
    public boolean insertHistorique(HistoriqueDepot depot) {
        String sql = "INSERT INTO HistoriqueDepot (idMenage, idPoubelle, heureDepot, quantiteDechets, pointsGagnes) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, depot.getIdMenage());
            stmt.setInt(2, depot.getIdPoubelle());
            stmt.setTimestamp(3, Timestamp.valueOf(depot.getHeureDepot()));
            stmt.setDouble(4, depot.getQuantiteDechets());
            stmt.setInt(5, depot.getPointsGagnes());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Erreur insertion historique : " + e.getMessage());
            return false;
        }
    }

    // 🔹 Tous les historiques d’un ménage
    public List<HistoriqueDepot> getHistoriqueByMenage(int idMenage) {
        List<HistoriqueDepot> historiques = new ArrayList<>();
        String sql = "SELECT * FROM HistoriqueDepot WHERE idMenage = ? ORDER BY heureDepot DESC";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                historiques.add(new HistoriqueDepot(
                        rs.getInt("idDepot"),
                        rs.getInt("idMenage"),
                        rs.getInt("idPoubelle"),
                        rs.getTimestamp("heureDepot").toLocalDateTime(),
                        rs.getDouble("quantiteDechets"),
                        rs.getInt("pointsGagnes")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération historique : " + e.getMessage());
        }

        return historiques;
    }

    // 🔹 Tous les historiques (global)
    public List<HistoriqueDepot> getAllHistorique() {
        List<HistoriqueDepot> historiques = new ArrayList<>();
        String sql = "SELECT * FROM HistoriqueDepot ORDER BY heureDepot DESC";

        try (Connection conn = ConnexionBDD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                historiques.add(new HistoriqueDepot(
                        rs.getInt("idDepot"),
                        rs.getInt("idMenage"),
                        rs.getInt("idPoubelle"),
                        rs.getTimestamp("heureDepot").toLocalDateTime(),
                        rs.getDouble("quantiteDechets"),
                        rs.getInt("pointsGagnes")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération tous historiques : " + e.getMessage());
        }

        return historiques;
    }

    // 🔹 Tous les déchets déposés par un ménage
    public List<Dechet> getDechetsByMenage(int idMenage) {
        List<Dechet> dechets = new ArrayList<>();

        String sql = """
                SELECT d.*
                FROM Dechet d
                JOIN HistoriqueDepot h ON d.idPoubelle = h.idPoubelle
                WHERE h.idMenage = ?
                ORDER BY h.heureDepot DESC
                """;

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dechets.add(new Dechet(
                        rs.getInt("idDechet"),
                        TypeDechet.valueOf(rs.getString("typeDechet")),
                        rs.getDouble("poids"),
                        rs.getInt("idPoubelle")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération déchets par ménage : " + e.getMessage());
        }

        return dechets;
    }

    // 🔹 Dernier dépôt d’un ménage
    public HistoriqueDepot getLastDepot(int idMenage) {
        String sql = "SELECT * FROM HistoriqueDepot WHERE idMenage = ? ORDER BY heureDepot DESC LIMIT 1";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new HistoriqueDepot(
                        rs.getInt("idDepot"),
                        rs.getInt("idMenage"),
                        rs.getInt("idPoubelle"),
                        rs.getTimestamp("heureDepot").toLocalDateTime(),
                        rs.getDouble("quantiteDechets"),
                        rs.getInt("pointsGagnes")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération dernier dépôt : " + e.getMessage());
        }

        return null;
    }

    // 🔹 Déchets d’un ménage entre deux dates (NOUVEAU)
    public List<Dechet> getDechetsByMenageEntreDates(int idMenage, LocalDateTime debut, LocalDateTime fin) {
        List<Dechet> dechets = new ArrayList<>();
        String sql = """
                SELECT d.*
                FROM Dechet d
                JOIN HistoriqueDepot h ON d.idPoubelle = h.idPoubelle
                WHERE h.idMenage = ?
                AND h.heureDepot BETWEEN ? AND ?
                ORDER BY h.heureDepot DESC
                """;

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            stmt.setTimestamp(2, Timestamp.valueOf(debut));
            stmt.setTimestamp(3, Timestamp.valueOf(fin));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dechets.add(new Dechet(
                        rs.getInt("idDechet"),
                        TypeDechet.valueOf(rs.getString("typeDechet")),
                        rs.getDouble("poids"),
                        rs.getInt("idPoubelle")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération déchets entre deux dates : " + e.getMessage());
        }

        return dechets;
    }
}
