package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Acteur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class ActeurVue {

    private Pane paneTerrain;
    private Circle aCercle;
    private Circle tour ;

    public ActeurVue(Pane paneTerrain) {
        this.paneTerrain = paneTerrain;
    }


    public Circle getaCercle(Acteur acteur) {
        aCercle = new Circle(8);
        aCercle.setId(acteur.getId());
        aCercle.setFill(Color.BLACK);
        aCercle.translateXProperty().bind(acteur.xProperty());
        aCercle.translateYProperty().bind(acteur.yProperty());
        paneTerrain.getChildren().add(aCercle);

        return aCercle;
    }


}
