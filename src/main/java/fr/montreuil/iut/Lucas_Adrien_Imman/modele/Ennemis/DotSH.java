package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class DotSH extends Ennemy {
    public DotSH(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 0, 30, player, 3, 30,10,30);
    }

  
}
