DROP DATABASE IF EXISTS tri_selectif;
CREATE DATABASE tri_selectif;
USE tri_selectif;

-- 🗑️ Table Dechet
CREATE TABLE Dechet (
    idDechet INT AUTO_INCREMENT PRIMARY KEY,
    typeDechet VARCHAR(100),
    poids DOUBLE,
    idPoubelle INT,
    idMenage INT
);

-- 👤 Table Menage
CREATE TABLE Menage (
    idMenage INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    adresse VARCHAR(255),
    email VARCHAR(100) UNIQUE,
    motDePasse VARCHAR(255),
    badgeAccess VARCHAR(50),
    pointsFidelity INT DEFAULT 0
);

-- 🗑️ Table PoubelleIntelligente
CREATE TABLE PoubelleIntelligente (
    idPoubelle INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50),
    capaciteMaximale DOUBLE,
    capaciteActuelle DOUBLE DEFAULT 0,
    emplacement VARCHAR(100),
    idCentre INT,
    FOREIGN KEY (idCentre) REFERENCES CentreDeTri(idCentre)
);

-- 🕑 Table HistoriqueDepot
CREATE TABLE HistoriqueDepot (
    idDepot INT AUTO_INCREMENT PRIMARY KEY,
    idMenage INT,
    idPoubelle INT,
    heureDepot DATETIME DEFAULT CURRENT_TIMESTAMP,
    quantiteDechets DOUBLE,
    pointsGagnes INT,
    FOREIGN KEY (idMenage) REFERENCES Menage(idMenage) ON DELETE CASCADE,
    FOREIGN KEY (idPoubelle) REFERENCES PoubelleIntelligente(idPoubelle) ON DELETE CASCADE
);

-- 🧺 Table Corbeille
CREATE TABLE Corbeille (
    idCorbeille INT AUTO_INCREMENT PRIMARY KEY
);

-- 🔗 Table relationnelle Corbeille_Dechet (N:N)
CREATE TABLE Corbeille_Dechet (
    idCorbeille INT,
    idDechet INT,
    FOREIGN KEY (idCorbeille) REFERENCES Corbeille(idCorbeille) ON DELETE CASCADE,
    FOREIGN KEY (idDechet) REFERENCES Dechet(idDechet) ON DELETE CASCADE,
    PRIMARY KEY (idCorbeille, idDechet)
);

-- 🏪 Table Commerce
CREATE TABLE Commerce (
    idCommerce INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    adresse VARCHAR(255)
);

-- 📄 Table ContratPartenariat
CREATE TABLE ContratPartenariat (
    idContrat INT AUTO_INCREMENT PRIMARY KEY,
    idCommerce INT,
    dateDebut DATE,
    dateFin DATE,
    FOREIGN KEY (idCommerce) REFERENCES Commerce(idCommerce) ON DELETE CASCADE
);

-- ♻️ Table CentreDeTri
CREATE TABLE CentreDeTri (
    idCentre INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    adresse VARCHAR(255)
);

-- 🔗 Table Centre_Poubelle (N:N)
CREATE TABLE Centre_Poubelle (
    idCentre INT,
    idPoubelle INT,
    FOREIGN KEY (idCentre) REFERENCES CentreDeTri(idCentre) ON DELETE CASCADE,
    FOREIGN KEY (idPoubelle) REFERENCES PoubelleIntelligente(idPoubelle) ON DELETE CASCADE,
    PRIMARY KEY (idCentre, idPoubelle)
);

-- 🧾 Table Categorie
CREATE TABLE Categorie (
    idCategorie INT AUTO_INCREMENT PRIMARY KEY,
    listeCategorie VARCHAR(100)
);

-- 🎟️ Table BonAchat
CREATE TABLE BonAchat (
    idBonAchat INT AUTO_INCREMENT PRIMARY KEY,
    idMenage INT,
    idCommerce INT,
    pointsUtilises INT,
    dateCreation DATE,
    dateExpiration DATE,
    categorie VARCHAR(100),
    FOREIGN KEY (idMenage) REFERENCES Menage(idMenage),
    FOREIGN KEY (idCommerce) REFERENCES Commerce(idCommerce)
);






