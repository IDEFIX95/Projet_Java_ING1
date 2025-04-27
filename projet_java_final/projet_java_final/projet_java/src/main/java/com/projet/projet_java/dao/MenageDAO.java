package com.projet.projet_java.dao;

import com.projet.projet_java.model.Menage;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;

/**
 * DAO pour la gestion des ménages (utilisateurs) :
 * Permet l'insertion, la recherche et la mise à jour des ménages dans la base de données.
 */
public class MenageDAO {

    /**
     * Recherche un ménage par son ID.
     * @param idMenage ID du ménage.
     * @return Menage trouvé ou null.
     */
    public Menage getById(int idMenage) {
        String sql = "SELECT * FROM Menage WHERE idMenage = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMenage);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToMenage(rs);
            }

        } catch (SQLException e) {
            System.err.println(" Erreur récupération par ID : " + e.getMessage());
        }
        return null;
    }

    /**
     * Recherche un ménage par email et mot de passe.
     * @param email Email du ménage.
     * @param motDePasse Mot de passe (déjà hashé).
     * @return Menage trouvé ou null.
     */
    public Menage findByEmailAndPassword(String email, String motDePasse) {
        String sql = "SELECT * FROM Menage WHERE email = ? AND motDePasse = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToMenage(rs);
            }

        } catch (SQLException e) {
            System.err.println(" Erreur recherche par email + mot de passe : " + e.getMessage());
        }
        return null;
    }

    /**
     * Recherche un ménage uniquement par son email.
     * @param email Email du ménage.
     * @return Menage trouvé ou null.
     */
    public Menage findByEmail(String email) {
        String sql = "SELECT * FROM Menage WHERE email = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToMenage(rs);
            }

        } catch (SQLException e) {
            System.err.println(" Erreur recherche par email : " + e.getMessage());
        }
        return null;
    }

    /**
     * Recherche un ménage par son badge d'accès.
     * @param badge Badge du ménage.
     * @return Menage trouvé ou null.
     */
    public Menage findByBadge(String badge) {
        String sql = "SELECT * FROM Menage WHERE badgeAccess = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, badge);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToMenage(rs);
            }

        } catch (SQLException e) {
            System.err.println(" Erreur recherche par badge : " + e.getMessage());
        }
        return null;
    }

    /**
     * Ajoute un nouveau ménage à la base de données.
     * Vérifie que l'email et le badge ne sont pas déjà utilisés.
     * @param m Menage à insérer.
     * @return true si insertion réussie, false sinon.
     */
    public boolean insert(Menage m) {
        if (findByEmail(m.getEmail()) != null) {
            System.err.println(" Email déjà utilisé : " + m.getEmail());
            return false;
        }

        if (findByBadge(m.getBadgeAccess()) != null) {
            System.err.println(" Badge déjà utilisé : " + m.getBadgeAccess());
            return false;
        }

        String sql = "INSERT INTO Menage (nom, adresse, email, motDePasse, badgeAccess, pointsFidelity) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, m.getNom());
            stmt.setString(2, m.getAdresse());
            stmt.setString(3, m.getEmail());
            stmt.setString(4, m.getMotDePasse());
            stmt.setString(5, m.getBadgeAccess());
            stmt.setInt(6, m.getPointsFidelity());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    m.setIdMenage(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println(" Erreur insertion ménage : " + e.getMessage());
        }
        return false;
    }

    /**
     * Met à jour uniquement les points de fidélité d'un ménage.
     * @param idMenage ID du ménage.
     * @param newPoints Nouveaux points.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean updatePoints(int idMenage, int newPoints) {
        String sql = "UPDATE Menage SET pointsFidelity = ? WHERE idMenage = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newPoints);
            stmt.setInt(2, idMenage);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(" Erreur mise à jour points : " + e.getMessage());
            return false;
        }
    }

    /**
     * Mise à jour complète du profil d'un ménage (nom, adresse, email, mot de passe).
     * @param m Menage modifié.
     * @return true si succès, false sinon.
     */
    public boolean updateMenage(Menage m) {
        String sql = "UPDATE Menage SET nom = ?, adresse = ?, email = ?, motDePasse = ? WHERE idMenage = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNom());
            stmt.setString(2, m.getAdresse());
            stmt.setString(3, m.getEmail());
            stmt.setString(4, m.getMotDePasse());
            stmt.setInt(5, m.getIdMenage());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(" Erreur mise à jour profil ménage : " + e.getMessage());
            return false;
        }
    }

    /**
     * Méthode utilitaire pour convertir un ResultSet SQL en objet Menage.
     * @param rs Résultat SQL.
     * @return Instance de Menage.
     */
    private Menage mapResultSetToMenage(ResultSet rs) throws SQLException {
        return new Menage(
                rs.getInt("idMenage"),
                rs.getString("nom"),
                rs.getString("adresse"),
                rs.getString("email"),
                rs.getString("motDePasse"),
                rs.getString("badgeAccess"),
                rs.getInt("pointsFidelity")
        );
    }
}
