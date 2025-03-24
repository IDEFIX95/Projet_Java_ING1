-- 1️⃣ Création de la base de données (si elle n'existe pas déjà)
CREATE DATABASE IF NOT EXISTS tri_selectif;
USE tri_selectif;

-- 2️⃣ Création de la table "Dechet"
CREATE TABLE IF NOT EXISTS Dechet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    couleur VARCHAR(20),
    poids FLOAT,
    dateDepot TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 3️⃣ Création de la table "Utilisateur" (si tu gères des utilisateurs)
CREATE TABLE IF NOT EXISTS Utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL
);

-- 4️⃣ Création de la table "Depot" (pour suivre les dépôts de déchets)
CREATE TABLE IF NOT EXISTS Depot (
    id INT AUTO_INCREMENT PRIMARY KEY,
    utilisateur_id INT,
    dechet_id INT,
    quantite FLOAT NOT NULL,
    dateDepot TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id) ON DELETE SET NULL,
    FOREIGN KEY (dechet_id) REFERENCES Dechet(id) ON DELETE CASCADE
);

-- 5️⃣ Insertion de données de test dans "Dechet"
INSERT INTO Dechet (type, couleur, poids) VALUES
('Plastique', 'Jaune', 2.5),
('Verre', 'Vert', 5.0),
('Papier', 'Bleu', 1.2),
('Déchets organiques', 'Marron', 3.8),
('Métal', 'Gris', 4.5);

-- 6️⃣ Insertion de données de test dans "Utilisateur"
INSERT INTO Utilisateur (nom, prenom, email, mot_de_passe) VALUES
('Dupont', 'Jean', 'jean.dupont@email.com', 'mdp123'),
('Martin', 'Sophie', 'sophie.martin@email.com', 'mdp456');

-- 7️⃣ Insertion de données de test dans "Depot"
INSERT INTO Depot (utilisateur_id, dechet_id, quantite) VALUES
(1, 1, 1.5),
(2, 3, 2.0),
(1, 5, 3.0);
