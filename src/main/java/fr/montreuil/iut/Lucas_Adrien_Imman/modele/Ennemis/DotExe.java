package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class DotExe extends Ennemy {
    public DotExe(int x, int y, Pane levelPane, Environment env, Player player, int startDirection, ModeDeplacement md) {
        super(
                x, // x
                y, // y
                4, // health (valeur par défaut)
                30, // maxHealth (valeur par défaut)
                levelPane, // levelPane
                env, // env
                2, // spriteIndex (valeur par défaut)
                player, // player
                30, // speed (valeur par défaut)
                15, // damage (valeur par défaut)
                startDirection, // startDi
                // rection
                25, // dropeRate (valeur par défaut)
                2, // initialSpeed (valeur par défaut)
                md // md
        );
    }

    @Override
    public void doDamage(){
        if(isOnObjective()){
            this.getPlayer().looseLife(this.getDamage());
            //Enlève un tour aléatoire
            if (this.getLevel().getPlacedTower().size() != 0){
                int max = this.getLevel().getPlacedTower().size();
                int rand = (int) (Math.random() * (max-1) + 1);
                for (int i = 0; i < max; i++) {
                    if (i == rand){
                        this.getLevel().getPlacedTower().remove(i);
                    }
                }
            }
        }
    }

    @Override
    public void die(){

    }
}
