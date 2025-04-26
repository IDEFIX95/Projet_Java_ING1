package com.projet.projet_java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import com.projet.projet_java.dao.HistoriqueDepotDAO;
import com.projet.projet_java.model.HistoriqueDepot;
import com.projet.projet_java.session.UtilisateurConnecte;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoriqueController {

    @FXML private TextArea historiqueTextArea;

    private final HistoriqueDepotDAO dao = new HistoriqueDepotDAO();

    @FXML
    public void initialize() {
        if (UtilisateurConnecte.getInstance().getMenage() == null) {
            historiqueTextArea.setText("Utilisateur non connecté.");
            return;
        }

        int idMenage = UtilisateurConnecte.getInstance().getMenage().getIdMenage();
        List<HistoriqueDepot> historiques = dao.getHistoriqueByMenage(idMenage);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy – HH:mm");

        StringBuilder sb = new StringBuilder();
        for (HistoriqueDepot h : historiques) {
            sb.append(String.format("%s – %.2fkg de déchets\n",
                    h.getHeureDepot().format(formatter),
                    h.getQuantiteDechets()));
        }

        historiqueTextArea.setText(sb.toString().trim());
    }
}
