package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class Archive extends Ennemy{
    public Archive(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 1, 20, player, 3, 20,0);
    }

    @Override
    public void doDamage(){
        if(isOnObjective()){
            this.getPlayer().lifeReduction(this.getDamage());
        }
    }

    @Override
    public void die(){
        for (int i = 0; i < 3; i++) {
            Ennemy e = new DotSH(this.getX(), this.getY(), this.getLevelPane(), this.getLevel(), this.getPlayer());
            e.setDirection(this.getDirection());
            this.getLevel().getEnnemies().add(e);
        }
    }
}
