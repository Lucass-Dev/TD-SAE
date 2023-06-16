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

    public TestEnnemy(){
        this.levelPane = new Pane();
        this.level = new Level("0", levelPane);
        this.player = new Player();
        this.levelPane.setMaxWidth(640);
        this.levelPane.setMaxHeight(640);
    }


    @Test
    void doDamage(){
        player = new Player();
        Archive a1 = new Archive(557,62, levelPane, level, player);

        a1.doDamage();
        assertEquals(80,player.getLife());

        a1.doDamage();
        assertEquals(60, player.getLife());
    }

    @Test
    void onBound(){
        player = new Player();
        Archive a1 = new Archive(630,630, levelPane, level, player);

        assertTrue(a1.isOnBound(), "ennemi sur le terrain");

        Archive a2 = new Archive(8000,900, levelPane, level, player);
        assertFalse(a2.isOnBound(), "ennemi en dehors du terrain");
    }
}
