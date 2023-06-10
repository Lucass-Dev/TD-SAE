package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.layout.Pane;

public class DotExe extends Ennemy {
    public DotExe(int x, int y, Pane levelPane, Level level, Player player) {
        super(x, y, levelPane, level, 5, 100 , player, 1, 100,50);
    }

    @Override
    public void doDamage(){
        if(isOnObjective()){
            this.getPlayer().lifeReduction(this.getDamage());
            if (this.getLevel().getPlacedTower().size() != 0){
                int max = this.getLevel().getPlacedTower().size();
                System.out.println(max);
                int rand = (int) (Math.random() * (max-1) + 1);
                System.out.println(rand);

                for (int i = 0; i < max; i++) {
                    if (i == rand){
                        this.getLevel().getPlacedTower().remove(i);
                    }
                }
            }
        }
    }
}
