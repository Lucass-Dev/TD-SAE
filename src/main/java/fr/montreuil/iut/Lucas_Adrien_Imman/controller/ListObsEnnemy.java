package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.EnnemyVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsEnnemy implements ListChangeListener<Ennemy> {
    private Pane levelpane;


    public ListObsEnnemy(Pane levelpane) {
        this.levelpane = levelpane ;

    }


    @Override
    public void onChanged(Change<? extends Ennemy> c) {
        while (c.next()){
            for (Ennemy newEnnemy :c.getAddedSubList()) {
                EnnemyVue ennemyVue = new EnnemyVue(levelpane);
                ennemyVue.createEnnemySprite(newEnnemy); ;
            }

            for(Ennemy acteurMort : c.getRemoved()){
                levelpane.getChildren().remove(levelpane.lookup("#"+acteurMort.getId()));
            }

        }
    }
}
