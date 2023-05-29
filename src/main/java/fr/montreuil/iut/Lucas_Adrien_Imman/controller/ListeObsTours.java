package fr.montreuil.iut.Lucas_Adrien_Imman.controller;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Acteur;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tour;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.ActeurVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListeObsTours implements ListChangeListener<Tour> {

    private Pane paneTerrain ;


    public ListeObsTours(Pane paneTerrain) {
        this.paneTerrain = paneTerrain ;

    }


    @Override
    public void onChanged(Change<? extends Tour> c) {
        while (c.next()){
            for (Tour nvActeur:c.getAddedSubList()) {
                ActeurVue acteurVue = new ActeurVue(paneTerrain);
                acteurVue.getaCercleTour(nvActeur) ;
            }

        }
    }
}