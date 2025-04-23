package com.projet.projet_java.dao;

import com.projet.projet_java.model.PoubelleIntelligente;
import com.projet.projet_java.model.TypePoubelle;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoubelleDAO {

    public boolean insert(PoubelleIntelligente p) {
        if (exists(p.getTypePoubelle(), p.getEmplacement(), p.getIdCentre())) {
            System.err.println("⚠️ Une poubelle similaire existe déjà (même type, emplacement et centre).");
            return false;
        }

        String sql = "INSERT INTO PoubelleIntelligente (type, capaciteMaximale, capaciteActuelle, emplacement, idCentre) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getTypePoubelle().name());
            stmt.setDouble(2, p.getCapaciteMaximale());
            stmt.setDouble(3, p.getCapaciteActuelle());
            stmt.setString(4, p.getEmplacement());
            stmt.setInt(5, p.getIdCentre());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    p.setIdPoubelle(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur insertion poubelle : " + e.getMessage());
        }
        return false;
    }

    public boolean updateCapaciteActuelle(int idPoubelle, double nouvelleValeur) {
        if (nouvelleValeur < 0) {
            System.err.println("❌ Capacité négative refusée pour la poubelle ID : " + idPoubelle);
            return false;
        }

        String sql = "UPDATE PoubelleIntelligente SET capaciteActuelle = ? WHERE idPoubelle = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, nouvelleValeur);
            stmt.setInt(2, idPoubelle);

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.err.println("⚠️ Aucun enregistrement mis à jour pour la poubelle ID : " + idPoubelle);
                return false;
            }

            System.out.println("✅ Capacité mise à jour pour la poubelle ID " + idPoubelle + " → " + nouvelleValeur);
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Erreur mise à jour capacité actuelle : " + e.getMessage());
            return false;
        }
    }

    public boolean exists(TypePoubelle type, String emplacement, int idCentre) {
        String sql = "SELECT COUNT(*) FROM PoubelleIntelligente WHERE type = ? AND emplacement = ? AND idCentre = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type.name());
            stmt.setString(2, emplacement);
            stmt.setInt(3, idCentre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur vérification doublon poubelle : " + e.getMessage());
        }

        return false;
    }

    public PoubelleIntelligente getById(int idPoubelle) {
        String sql = "SELECT * FROM PoubelleIntelligente WHERE idPoubelle = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPoubelle);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return fromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération par ID : " + e.getMessage());
        }

        return null;
    }

    public List<PoubelleIntelligente> getAll() {
        List<PoubelleIntelligente> poubelles = new ArrayList<>();
        String sql = "SELECT * FROM PoubelleIntelligente";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                poubelles.add(fromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération de toutes les poubelles : " + e.getMessage());
        }

        return poubelles;
    }

    public List<PoubelleIntelligente> getByType(TypePoubelle type) {
        List<PoubelleIntelligente> result = new ArrayList<>();
        String sql = "SELECT * FROM PoubelleIntelligente WHERE type = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(fromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération par type : " + e.getMessage());
        }

        return result;
    }

    public List<PoubelleIntelligente> getByEmplacement(String emplacement) {
        List<PoubelleIntelligente> result = new ArrayList<>();
        String sql = "SELECT * FROM PoubelleIntelligente WHERE emplacement = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emplacement);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(fromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération par emplacement : " + e.getMessage());
        }

        return result;
    }

    public List<PoubelleIntelligente> getByCentre(int idCentre) {
        List<PoubelleIntelligente> result = new ArrayList<>();
        String sql = "SELECT * FROM PoubelleIntelligente WHERE idCentre = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCentre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(fromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération par centre : " + e.getMessage());
        }

        return result;
    }

    public PoubelleIntelligente getByTypeAndEmplacement(TypePoubelle type, String emplacement) {
        String sql = "SELECT * FROM PoubelleIntelligente WHERE type = ? AND emplacement = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type.name());
            stmt.setString(2, emplacement);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return fromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération par type et emplacement : " + e.getMessage());
        }

        return null;
    }

    private PoubelleIntelligente fromResultSet(ResultSet rs) throws SQLException {
        return new PoubelleIntelligente(
                rs.getInt("idPoubelle"),
                TypePoubelle.valueOf(rs.getString("type")),
                rs.getDouble("capaciteMaximale"),
                rs.getDouble("capaciteActuelle"),
                rs.getString("emplacement"),
                rs.getInt("idCentre")
        );
    }
}
