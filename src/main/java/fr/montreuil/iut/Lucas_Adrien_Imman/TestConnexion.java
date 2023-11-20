package fr.montreuil.iut.Lucas_Adrien_Imman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnexion {

    public static void main(String[] args) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:psql://https://database-etudiants.iut.univ-paris8.fr/lliger", "lliger", "X7f7Nnjh");
        Statement statement = c.createStatement();
        System.out.println(c);
    }
}
