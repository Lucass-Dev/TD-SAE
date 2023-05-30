package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tower;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TowerVue {

    private Pane tilePane;
    private Rectangle rect;

    public TowerVue(Pane tilePane){
        this.tilePane = tilePane;
    }

    public void createTowerSprite(Tower newTower){
        this.rect = new Rectangle(16, 16);

        this.rect.setFill(Color.DARKGOLDENROD);
        tilePane.getChildren().add(rect);
        this.rect.translateXProperty().bind(newTower.xProperty());
        this.rect.translateYProperty().bind(newTower.yProperty());
        System.out.println(newTower.yProperty());
        System.out.println(newTower.xProperty());


    }
}
