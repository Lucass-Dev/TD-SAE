package fr.montreuil.iut.Lucas_Adrien_Imman.controller;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.EnnemyVue;
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
        while (c.next()){
            for (Projectile newProjectile :c.getAddedSubList()) {
                ProjectileVue projectileVue = new ProjectileVue(levelpane);

                    projectileVue.projectileSprite(newProjectile);

                }
            }
            for(Projectile oldProjectile : c.getRemoved()){
                System.out.println(oldProjectile.getId());
                levelpane.getChildren().remove(levelpane.lookup("#"+oldProjectile.getId()));
            }
        }
    }



