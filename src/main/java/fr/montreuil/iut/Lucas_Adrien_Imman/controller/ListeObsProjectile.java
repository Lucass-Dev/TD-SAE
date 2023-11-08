package fr.montreuil.iut.Lucas_Adrien_Imman.controller;


import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.ProjectileVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class ListeObsProjectile implements ListChangeListener<Projectile> {
    private Pane levelpane;

    public ListeObsProjectile(Pane levelpane) {
        this.levelpane = levelpane ;

    }


    @Override
    public void onChanged(Change<? extends Projectile> c) {
        while (c.next()) {
            for (Projectile newProjectile : c.getAddedSubList()) {
                ProjectileVue projectileVue = new ProjectileVue(levelpane);
                try {
                    projectileVue.projectileSprite(newProjectile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            for (Projectile oldProjectile : c.getRemoved()) {

                levelpane.getChildren().remove(levelpane.lookup("#" + oldProjectile.getId()));
            }
        }
    }
}
