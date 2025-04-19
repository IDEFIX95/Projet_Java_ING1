package com.projet.projet_java.model;

public class Dechet {
    private int idDechet;
    private TypeDechet typeDechet;
    private double poids;
    private int idPoubelle; // 🔄 Nouveau champ pour tracer la poubelle liée

    // 🔹 Constructeur complet avec idPoubelle
    public Dechet(int idDechet, TypeDechet typeDechet, double poids, int idPoubelle) {
        this.idDechet = idDechet;
        this.typeDechet = typeDechet;
        this.poids = poids;
        this.idPoubelle = idPoubelle;
    }

    // 🔹 Constructeur sans idDechet (avant insertion)
    public Dechet(TypeDechet typeDechet, double poids, int idPoubelle) {
        this(0, typeDechet, poids, idPoubelle);
    }

    // ✅ Getters
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

    // ✅ Setters
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
}
