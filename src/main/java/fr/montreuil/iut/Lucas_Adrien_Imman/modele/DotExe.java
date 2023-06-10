package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class DotExe extends Ennemy {
    public DotExe(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 5, 100, player, 1, 100,50);
    }


}
