package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class Virus extends Ennemy{

    public Virus(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 2, 5000, player, 4, 50,65);
    }

    @Override
    public void doDamage(){
        if(isOnObjective()){
            this.getPlayer().lifeReduction(this.getDamage());
            this.getLevel().freezeRam(10);
        }
    }

}
