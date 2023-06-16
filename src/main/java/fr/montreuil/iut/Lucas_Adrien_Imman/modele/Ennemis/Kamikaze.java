package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class Kamikaze extends Ennemy {
    public Kamikaze(int x, int y, Pane levelPane, Level level, Player player, int startDirection) {
        super(x, y, levelPane, level, 4, 30, player, 2, 30,35, 0, startDirection);
    }


    @Override
    public void doDamage() {

    }

    @Override
    public void die() {

    }
}
