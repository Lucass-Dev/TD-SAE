package fr.montreuil.iut.Lucas_Adrien_Imman.testTD;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Archive;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class TestEnnemy {

    private Pane levelPane;
    private Level level;
    private Player player;


    @Test
    void doDamage(){
        player = new Player();
        Archive a1 = new Archive(250,250, levelPane, level, player);

        a1.doDamage();
        assertEquals(80,player.getLife());

        a1.doDamage();
        assertEquals(60, player.getLife());
    }

    @Test
    void onBound(){
        player = new Player();
        Archive a1 = new Archive(250,250, levelPane, level, player);

        assertTrue(a1.isOnBound(), "ennemi sur le terrain");

        Archive a2 = new Archive(8000,900, levelPane, level, player);
        assertFalse(a2.isOnBound(), "ennemi en dehors du terrain");
    }
}
