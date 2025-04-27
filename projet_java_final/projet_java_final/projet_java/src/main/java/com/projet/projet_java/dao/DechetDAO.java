// DAO pour la gestion des déchets : insertion, récupération par ID et récupération globale
package com.projet.projet_java.dao;

import com.projet.projet_java.model.Dechet;
import com.projet.projet_java.model.TypeDechet;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) pour la gestion des entités Dechet.
 */
public class DechetDAO {

    /**
     * Insère un déchet en base de données.
     * @param dechet Objet Dechet à insérer.
     * @return ID généré du déchet inséré ou -1 en cas d'échec.
     */
    public int insert(Dechet dechet) {
        String sql = "INSERT INTO Dechet (typeDechet, poids, idPoubelle, idMenage) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, dechet.getTypeDechet().name());
            stmt.setDouble(2, dechet.getPoids());
            stmt.setInt(3, dechet.getIdPoubelle());
            stmt.setInt(4, dechet.getIdMenage());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    generatedId = keys.getInt(1); // Récupération de l'ID généré
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'insertion du déchet : " + e.getMessage());
        }

        return generatedId;
    }

    /**
     * Récupère un déchet en base par son ID.
     * @param id Identifiant du déchet.
     * @return Dechet correspondant ou null si non trouvé.
     */
    public Dechet getById(int id) {
        String sql = "SELECT * FROM Dechet WHERE idDechet = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Dechet(
                        rs.getInt("idDechet"),
                        TypeDechet.valueOf(rs.getString("typeDechet").toUpperCase()),
                        rs.getDouble("poids"),
                        rs.getInt("idPoubelle"),
                        rs.getInt("idMenage")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération du déchet : " + e.getMessage());
        }

        return null;
    }

    /**
     * Récupère tous les déchets enregistrés en base.
     * @return Liste de tous les Dechet.
     */
    public List<Dechet> getAll() {
        List<Dechet> dechets = new ArrayList<>();
        String sql = "SELECT * FROM Dechet";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Dechet dechet = new Dechet(
                        rs.getInt("idDechet"),
                        TypeDechet.valueOf(rs.getString("typeDechet").toUpperCase()),
                        rs.getDouble("poids"),
                        rs.getInt("idPoubelle"),
                        rs.getInt("idMenage")
                );
                dechets.add(dechet);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération de tous les déchets : " + e.getMessage());
        }

        return dechets;
    }
}
