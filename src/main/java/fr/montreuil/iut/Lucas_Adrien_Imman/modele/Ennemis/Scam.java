package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.PopupVue;
import javafx.scene.layout.Pane;

public class Scam extends Ennemy{
    public Scam(int x, int y, Pane levelPane, Level level, Player player, int startDirection) {
        super(x, y, levelPane, level, 3, 10, player, 6, 10,0, startDirection, 20, 5);
    }

    @Override
    public void doDamage(){
        if(isOnObjective()){
            this.getPlayer().looseLife(this.getDamage());
            PopupVue pv = new PopupVue();
            pv.scamPopup();
        }
    }

    @Override
    public void die(){

    }
}
