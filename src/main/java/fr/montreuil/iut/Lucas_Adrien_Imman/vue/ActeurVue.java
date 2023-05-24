package fr.montreuil.iut.Lucas_Adrien_Imman.vue;


import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Terrain;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class ActeurVue {

    private Pane paneTerrain;

    public ActeurVue(Pane paneTerrain) {
        super();
        this.paneTerrain = paneTerrain;
        this.creationSpriteActeur();
    }


    public Circle creationSpriteActeur() {
        Circle cActeur = new Circle(6);
        cActeur.setFill(Color.BLACK);
        paneTerrain.getChildren().add(cActeur);
        return cActeur;
    }




}