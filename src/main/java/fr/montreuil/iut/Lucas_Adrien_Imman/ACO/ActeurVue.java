package fr.montreuil.iut.Lucas_Adrien_Imman.ACO;

import fr.montreuil.iut.Lucas_Adrien_Imman.ACO.Acteur;
import fr.montreuil.iut.Lucas_Adrien_Imman.ACO.Tour;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import  javafx.scene.shape.Rectangle;
import  javafx.scene.shape.Circle;

public class ActeurVue {

    private Pane paneTerrain;
    private Circle aCercle;
    private Rectangle tour ;

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


    public Circle getaCercleTour(Tour acteur) {
        tour = new Rectangle(16,16);
        tour.setFill(Color.DARKGOLDENROD);
        tour.translateXProperty().bind(acteur.xProperty());
        tour.translateYProperty().bind(acteur.yProperty());
        paneTerrain.getChildren().add(tour);

        return aCercle;
    }

}
