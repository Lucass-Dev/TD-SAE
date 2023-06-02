package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class EnnemyVue {
    private Pane levelPane;
    private Circle ennemy;
    private  VBox vie ;

    public EnnemyVue(Pane levelPane) {
        this.levelPane = levelPane;
    }

    public void createEnnemySprite(Ennemy newEnnemy){
        this.ennemy = new Circle(5);

        this.ennemy.setFill(Color.DARKGOLDENROD);
        this.levelPane.getChildren().add(ennemy);
        this.ennemy.translateXProperty().bind(newEnnemy.xProperty());
        this.ennemy.translateYProperty().bind(newEnnemy.yProperty());
        this.ennemy.setId("E"+newEnnemy.getId());


    }
}
