package fr.montreuil.iut.Lucas_Adrien_Imman.modele.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private static Connexion instance = null;
    private Connection connexion;

    private Connexion() throws SQLException {
        this.connexion = DriverManager.getConnection(" jdbc:postgresql://database-etudiants.iut.univ-paris8.fr/lliger", "lliger", "X7f7Nnjh");
        connexion.prepareStatement("SELECT * FROM td_sae.joueurs;");
    }

    public static Connexion getInstance() throws SQLException {
        if (instance != null){
            return instance;
        }else{
            return new Connexion();
        }
    }
}
