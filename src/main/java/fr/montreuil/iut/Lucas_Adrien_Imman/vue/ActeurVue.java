package fr.montreuil.iut.Lucas_Adrien_Imman.vue;


import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Terrain;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class ActeurVue {


    private Terrain terrain;
    private Pane paneTerrain;

    public ActeurVue(Pane paneTerrain, Circle acteur1) {
        super();
        this.paneTerrain = paneTerrain;
        this.afficherActeur(acteur1);
    }


    public void afficherActeur(Circle acteur1) {
        acteur1.setFill(Color.BLACK);
        paneTerrain.getChildren().add(acteur1);
    }




}