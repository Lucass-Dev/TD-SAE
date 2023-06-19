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
    void enVIe(){
        player = new Player();
        Archive a1 = new Archive(557,62, levelPane, level, player);

        a1.reductionPv(20);
        assertTrue(a1.estMort(),"l'ennemi est mort");

        a1.setLife(20);
        assertFalse(a1.estMort(), "l'ennemi n'est pas mort");

    }
}
