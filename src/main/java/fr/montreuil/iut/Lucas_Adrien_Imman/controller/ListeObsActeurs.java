package fr.montreuil.iut.Lucas_Adrien_Imman.controller;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Acteur;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.ActeurVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListeObsActeurs implements ListChangeListener<Acteur> {

    private Pane paneTerrain ;


    public ListeObsActeurs(Pane paneTerrain) {
        this.paneTerrain = paneTerrain ;

    }



    @Override
    public void onChanged(Change<? extends Acteur> c) {
        while (c.next()){
            for (Acteur nvActeur:c.getAddedSubList()) {
                ActeurVue acteurVue = new ActeurVue(paneTerrain);
                acteurVue.getaCercle(nvActeur) ;
            }

            for (Acteur acteurMort:c.getRemoved()) {
                paneTerrain.getChildren().remove(paneTerrain.lookup("#"+acteurMort.getId()));
            }
        }
    }
}