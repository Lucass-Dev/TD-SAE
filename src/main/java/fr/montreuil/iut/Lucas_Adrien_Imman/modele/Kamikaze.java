package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class Kamikaze extends Ennemy{
    public Kamikaze(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 4, 30, player, 2, 30);
    }

    @Override
    public void doDamage() {

    }
}
