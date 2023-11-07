package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.PopupVue;
import javafx.scene.layout.Pane;

public class Scam extends Ennemy{
    public Scam(int x, int y, Pane levelPane, Environment env, Player player, int startDirection, ModeDeplacement md) {
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
        if(this.isOnObjective()){
            Environment.getInstance().getPlayer().looseLife(this.getDamage());
            PopupVue pv = new PopupVue();
            pv.scamPopup();
        }
    }

    @Override
    public void die(){

    }
}
