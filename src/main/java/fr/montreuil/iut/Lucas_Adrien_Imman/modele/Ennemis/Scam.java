package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class Scam extends Ennemy {
    public Scam(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 3, 10, player, 6, 10,30);
    }


}