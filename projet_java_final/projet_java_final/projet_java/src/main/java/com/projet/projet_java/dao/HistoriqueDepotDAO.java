package com.projet.projet_java.dao;

import com.projet.projet_java.model.HistoriqueDepot;
import com.projet.projet_java.model.TypeDechet;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.util.*;

/**
 * DAO pour la gestion des historiques de dépôts des ménages.
 */
public class HistoriqueDepotDAO {

    /**
     * Insère un nouvel historique de dépôt dans la base de données.
     * @param depot L'historique à insérer.
     * @return true si succès, false sinon.
     */
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
            System.err.println(" Erreur insertion historique : " + e.getMessage());
            return false;
        }
    }

    /**
     * Récupère la poubelle la plus utilisée par un ménage donné.
     * @param idMenage ID du ménage.
     * @return Description de la poubelle la plus utilisée.
     */
    public String getTopPoubelleByMenage(int idMenage) {
        String sql = """
            SELECT p.type, p.emplacement, COUNT(*) AS total
            FROM HistoriqueDepot h
            JOIN PoubelleIntelligente p ON h.idPoubelle = p.idPoubelle
            WHERE h.idMenage = ?
            GROUP BY p.type, p.emplacement
            ORDER BY total DESC
            LIMIT 1
        """;

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("type") + " - " + rs.getString("emplacement") + " (" + rs.getInt("total") + " dépôts)";
            }

        } catch (SQLException e) {
            System.err.println(" Erreur top poubelle par ménage : " + e.getMessage());
        }

        return "Aucune donnée";
    }

    /**
     * Récupère l'historique de dépôt d'un ménage spécifique.
     * @param idMenage ID du ménage.
     * @return Liste des historiques.
     */
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
            System.err.println(" Erreur récupération historique : " + e.getMessage());
        }

        return historiques;
    }

    /**
     * Récupère tous les historiques de dépôts.
     * @return Liste de tous les historiques.
     */
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
            System.err.println(" Erreur récupération tous historiques : " + e.getMessage());
        }

        return historiques;
    }

    /**
     * Récupère le dernier dépôt effectué par un ménage.
     * @param idMenage ID du ménage.
     * @return Dernier HistoriqueDepot ou null.
     */
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
            System.err.println(" Erreur récupération dernier dépôt : " + e.getMessage());
        }

        return null;
    }

    /**
     * Récupère le poids total de déchets déposés par un ménage.
     * @param idMenage ID du ménage.
     * @return Poids total.
     */
    public double getPoidsTotalByMenage(int idMenage) {
        String sql = "SELECT SUM(quantiteDechets) FROM HistoriqueDepot WHERE idMenage = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getDouble(1);

        } catch (SQLException e) {
            System.err.println(" Erreur total poids par ménage : " + e.getMessage());
        }
        return 0;
    }

    /**
     * Récupère le total des points gagnés par un ménage.
     * @param idMenage ID du ménage.
     * @return Total de points.
     */
    public int getPointsTotalByMenage(int idMenage) {
        String sql = "SELECT SUM(pointsGagnes) FROM HistoriqueDepot WHERE idMenage = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.err.println(" Erreur total points par ménage : " + e.getMessage());
        }
        return 0;
    }

    /**
     * Récupère la répartition des quantités de déchets par type pour un ménage.
     * @param idMenage ID du ménage.
     * @return Map de TypeDechet → Poids total.
     */
    public Map<TypeDechet, Double> getRepartitionQuantiteParType(int idMenage) {
        Map<TypeDechet, Double> map = new EnumMap<>(TypeDechet.class);
        String sql = """
            SELECT typeDechet, SUM(poids) as total
            FROM Dechet
            WHERE idMenage = ?
            GROUP BY typeDechet
        """;

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                try {
                    TypeDechet type = TypeDechet.valueOf(rs.getString("typeDechet").toUpperCase());
                    double poids = rs.getDouble("total");
                    map.put(type, poids);
                } catch (IllegalArgumentException e) {
                    System.err.println(" Type de déchet ignoré (inconnu pour l'enum) : " + rs.getString("typeDechet"));
                }
            }

        } catch (SQLException e) {
            System.err.println(" Erreur répartition déchets par type et ménage : " + e.getMessage());
        }

        return map;
    }
}
