package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tower;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.ActeurVue;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

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
                towerVue.createTowerSprite(newTower, newTower.getSprite()) ;
            }

        }
    }
}
