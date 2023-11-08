package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.ProjectileVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class ListeObsEffetTour implements ListChangeListener<EffetTour> {
    private Pane levelpane;

    public ListeObsEffetTour(Pane levelpane) {
        this.levelpane = levelpane ;

    }


    @Override
    public void onChanged(Change<? extends EffetTour> c) {
        while (c.next()) {
            for (EffetTour newEffetTour : c.getAddedSubList()) {
                ProjectileVue projectileVue = new ProjectileVue(levelpane);
                try {
                    projectileVue.projectileSprite(newEffetTour);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for (EffetTour oldEffetTour : c.getRemoved()) {

                levelpane.getChildren().remove(levelpane.lookup("#" + oldEffetTour.getId()));
            }
        }
    }
}



