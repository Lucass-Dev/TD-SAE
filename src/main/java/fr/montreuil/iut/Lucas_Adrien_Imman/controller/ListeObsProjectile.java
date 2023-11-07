/* package fr.montreuil.iut.Lucas_Adrien_Imman.controller;


import fr.montreuil.iut.Lucas_Adrien_Imman.vue.ProjectileVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class ListeObsProjectile implements ListChangeListener<EffetTour> {
    private Pane levelpane;

    public ListeObsProjectile(Pane levelpane) {
        this.levelpane = levelpane ;

    }


    @Override
    public void onChanged(Change<? extends EffetTour> c) {
        while (c.next()) {
            for (EffetTour newProjectile : c.getAddedSubList()) {
                ProjectileVue projectileVue = new ProjectileVue(levelpane);
                try {
                    projectileVue.projectileSprite(newProjectile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            for (EffetTour oldProjectile : c.getRemoved()) {

                levelpane.getChildren().remove(levelpane.lookup("#" + oldProjectile.getId()));
            }
        }
    }
}


*/
