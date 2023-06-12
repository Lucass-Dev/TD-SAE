package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.Tower;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours.Tower;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ListObsTower implements ListChangeListener<Tower> {


    private Pane levelpane ;


    public ListObsTower(Pane levelpane) {
        this.levelpane = levelpane ;

    }


    @Override
    public void onChanged(Change<? extends Tower> c) {
        while (c.next()){
            for (Tower newTower :c.getAddedSubList()) {
                TowerVue towerVue = new TowerVue(levelpane);
                try {
                    towerVue.createTowerSprite(newTower) ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for(Tower acteurMort : c.getRemoved()){
                levelpane.getChildren().remove(levelpane.lookup("#"+acteurMort.getId()));
            }

        }
    }
}
