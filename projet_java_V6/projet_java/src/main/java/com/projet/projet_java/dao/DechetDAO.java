package com.projet.projet_java.dao;

import com.projet.projet_java.model.Dechet;
import com.projet.projet_java.model.TypeDechet;
import com.projet.projet_java.utils.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DechetDAO {

    // 🔹 Insertion d’un déchet avec idMenage
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
                    generatedId = keys.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur insertion déchet : " + e.getMessage());
        }

        return generatedId;
    }

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
            System.err.println("❌ Erreur récupération déchet : " + e.getMessage());
        }

        return null;
    }

    public List<Dechet> getAll() {
        List<Dechet> dechets = new ArrayList<>();
        String sql = "SELECT * FROM Dechet";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Dechet d = new Dechet(
                        rs.getInt("idDechet"),
                        TypeDechet.valueOf(rs.getString("typeDechet").toUpperCase()),
                        rs.getDouble("poids"),
                        rs.getInt("idPoubelle"),
                        rs.getInt("idMenage")
                );
                dechets.add(d);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erreur récupération déchets : " + e.getMessage());
        }

        return dechets;
    }
}
