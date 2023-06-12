package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class Virus extends Ennemy {
    public Virus(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 2, 50, player, 4, 50,65);
    }


}
