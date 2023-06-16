package fr.montreuil.iut.Lucas_Adrien_Imman.testTD;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Archive;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.ProjectileDegatsBrut;
import javafx.scene.layout.Pane;

public class TestProjectiles {

    private Pane levelPane;
    private Level level;
    private Player player;

    public TestProjectiles(){
        this.levelPane = new Pane();
        this.level = new Level("level", levelPane);
        this.player = new Player();
    }



    @Test
    void onBound(){
        Archive a1 = new Archive(235,351,levelPane,level, player);
        ProjectileDegatsBrut p1 = new ProjectileDegatsBrut(750,800,a1);

        assertTrue (p1.isOnBound(),"le projectile est bien sur le terrain");

        ProjectileDegatsBrut p2 = new ProjectileDegatsBrut(102,605,a1);

        assertFalse(p2.isOnBound(), "le projectile n'est pas sur le terrain");


    }

    @Test
    void onTarget(){
        Archive a2 = new Archive(235,351,levelPane,level, player);
        ProjectileDegatsBrut p1 = new ProjectileDegatsBrut(235,351,a2);

        assertTrue(p1.cibleAtteint(), "le projectile est sur les mêmes coordonnées que l'ennemi");

        ProjectileDegatsBrut p2 = new ProjectileDegatsBrut(102,605,a2);

        assertFalse(p2.cibleAtteint(), "le projectile et l'ennemi sont a des coordonnée differentes");

    }


}
