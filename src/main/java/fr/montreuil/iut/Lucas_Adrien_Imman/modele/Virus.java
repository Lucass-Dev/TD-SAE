package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class Virus extends Ennemy{

    public Virus(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 2, 50, player, 4, 50,1);
    }

    @Override
    public void doDamage(){
        if(isOnObjective()){
            this.getPlayer().lifeReduction(this.getDamage());
            int rand = (int)((Math.random() * (3 - 1)) + 1);
            System.out.println(rand);
            if (rand == 1){
                this.getLevel().freezeRam(10);
            }else{
                this.getLevel().applyPoison(5);
            }
        }
    }

    @Override
    public void die(){

    }

}
