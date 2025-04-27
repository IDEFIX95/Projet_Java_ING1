package com.projet.projet_java.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import com.projet.projet_java.dao.HistoriqueDepotDAO;
import com.projet.projet_java.model.TypeDechet;
import com.projet.projet_java.session.UtilisateurConnecte;

import java.util.Map;

/**
 * Contrôleur de la page Statistiques.
 * Affiche des indicateurs sur les dépôts réalisés par l'utilisateur connecté.
 */
public class StatistiquesController {

    @FXML private Label totalDechetsLabel;
    @FXML private Label totalPointsLabel;
    @FXML private Label topPoubelleLabel;
    @FXML private PieChart dechetsChart;

    private final HistoriqueDepotDAO dao = new HistoriqueDepotDAO();

    /**
     * Initialise les statistiques au chargement de la fenêtre.
     */
    @FXML
    public void initialize() {
        // Vérifie si un utilisateur est connecté
        if (UtilisateurConnecte.getInstance().getMenage() == null) {
            totalDechetsLabel.setText("N/A");
            totalPointsLabel.setText("N/A");
            topPoubelleLabel.setText("N/A");
            return;
        }

        int idMenage = UtilisateurConnecte.getInstance().getMenage().getIdMenage();

        //  Récupération des statistiques principales
        double totalPoids = dao.getPoidsTotalByMenage(idMenage);
        int totalPoints = dao.getPointsTotalByMenage(idMenage);
        String topPoubelle = dao.getTopPoubelleByMenage(idMenage);

        totalDechetsLabel.setText(String.format("%.2f kg", totalPoids));
        totalPointsLabel.setText(String.valueOf(totalPoints));
        topPoubelleLabel.setText(topPoubelle != null ? topPoubelle : "Aucune");

        //  Récupération de la répartition des déchets par type
        Map<TypeDechet, Double> repartition = dao.getRepartitionQuantiteParType(idMenage);
        double totalReel = repartition.values().stream().mapToDouble(Double::doubleValue).sum();

        // Mise à jour du graphique circulaire
        dechetsChart.getData().clear();
        if (totalReel > 0) {
            repartition.forEach((type, poids) -> {
                double pourcentage = (poids / totalReel) * 100;
                String label = String.format("%s – %.1f%% (%.2f kg)", type.name(), pourcentage, poids);
                dechetsChart.getData().add(new PieChart.Data(label, poids));
            });
        }

        //  Configuration visuelle du graphique
        dechetsChart.setPrefSize(600, 450);
        dechetsChart.setLabelsVisible(true);
        dechetsChart.setLegendVisible(true);
    }
}
