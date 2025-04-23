package com.projet.projet_java.utils;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FenetreManager {

    private static final List<Stage> fenetres = new ArrayList<>();

    public static void ajouterFenetre(Stage stage) {
        fenetres.add(stage);
    }

    public static void fermerToutes() {
        for (Stage stage : fenetres) {
            if (stage.isShowing()) {
                stage.close();
            }
        }
        fenetres.clear();
    }
}
