package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class Archive extends Ennemy {
    public Archive(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 1, 20, player, 3, 20,20,30);
    }

}
