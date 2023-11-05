package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class Archive extends Ennemy{
    public Archive(int x, int y, Pane levelPane, Environment environment, Player player, int startDirection) {
        super(x, y, levelPane, environment, 1, 20, player, 3, 20,0,  startDirection, 30,3);
    }

    @Override
    public void doDamage(){
        if(isOnObjective()){
            this.getPlayer().looseLife(this.getDamage());
        }
    }

    @Override
    public void die(){
        //Fait apparaître plusieurs DotSH qand il est tué par des tours
        for (int i = 0; i < 3; i++) {
            Ennemy e = new DotSH(this.getX(), this.getY(), this.getLevelPane(), this.getEnvironment(), this.getPlayer(),1);
         //   e.setDirection(this.getDirection());
           // this.getLevel().getEnnemies().add(e);
        }
    }
}
