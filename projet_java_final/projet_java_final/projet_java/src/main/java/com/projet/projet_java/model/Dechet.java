package com.projet.projet_java.model;

/**
 * Classe représentant un Déchet.
 * Chaque déchet appartient à une poubelle et à un ménage qui l'a déposé.
 */
public class Dechet {

    private int idDechet;
    private TypeDechet typeDechet;
    private double poids;
    private int idPoubelle;
    private int idMenage; // ✅ Pour relier le déchet au ménage responsable

    // 🔹 Constructeur complet (utile pour récupérer depuis la base)
    public Dechet(int idDechet, TypeDechet typeDechet, double poids, int idPoubelle, int idMenage) {
        this.idDechet = idDechet;
        this.typeDechet = typeDechet;
        this.poids = poids;
        this.idPoubelle = idPoubelle;
        this.idMenage = idMenage;
    }

    // 🔹 Constructeur sans ID (avant insertion dans la base)
    public Dechet(TypeDechet typeDechet, double poids, int idPoubelle, int idMenage) {
        this(0, typeDechet, poids, idPoubelle, idMenage);
    }

    // -------------------------------
    // Getters
    // -------------------------------

    public int getIdDechet() {
        return idDechet;
    }

    public TypeDechet getTypeDechet() {
        return typeDechet;
    }

    public double getPoids() {
        return poids;
    }

    public int getIdPoubelle() {
        return idPoubelle;
    }

    public int getIdMenage() {
        return idMenage;
    }

    // -------------------------------
    // Setters
    // -------------------------------

    public void setIdDechet(int idDechet) {
        this.idDechet = idDechet;
    }

    public void setTypeDechet(TypeDechet typeDechet) {
        this.typeDechet = typeDechet;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setIdPoubelle(int idPoubelle) {
        this.idPoubelle = idPoubelle;
    }

    public void setIdMenage(int idMenage) {
        this.idMenage = idMenage;
    }
}
