package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.scene.layout.Pane;

public class Archive extends Ennemy{
    public Archive(int x, int y, Pane levelPane, Environment env, Player player, int startDirection, ModeDeplacement md) {
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
                startDirection, // startDirection
                25, // dropeRate (valeur par défaut)
                2, // initialSpeed (valeur par défaut)
                md // md
        );
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
            Ennemy e = new DotSH(
                    this.getXValue(),
                    this.getY(),
                    this.getLevelPane(),
                    this.getEnv(),
                    this.getPlayer(),
                    this.getDirection(),
                    this.getModeDeplacement()
            );

            e.setDirection(this.getDirection());
            this.getEnv().getEnnemies().add(e);
        }
    }
}
