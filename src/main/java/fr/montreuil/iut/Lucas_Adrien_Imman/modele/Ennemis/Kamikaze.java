package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class Kamikaze extends Ennemy {
    public Kamikaze(int x, int y, Pane levelPane, Environment level, Player player) {
        super(x, y, levelPane, level, 4, 30, player, 2, 30,15, 25, 2);
    }


    @Override
    public void doDamage() {

    }

    @Override
    public void die() {

    }

    @Override
    public boolean isOnBound() {
        return false;
    }

    @Override
    public void algoDeplacement() {

    }

    @Override
    public boolean isOnObjective() {
        return false;
    }
}
