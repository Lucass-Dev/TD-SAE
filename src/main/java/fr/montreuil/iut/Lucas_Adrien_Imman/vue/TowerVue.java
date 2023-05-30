package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TowerVue {

    private Pane levelPane;
    private Rectangle rect;
    private ImageView imageView;

    public TowerVue(Pane levelPane){
        this.levelPane = levelPane;
    }

    public void createTowerSprite(Tower newTower, Image image){
        this.imageView = new ImageView(image);
        levelPane.getChildren().add(imageView);
        this.imageView.translateXProperty().bind(newTower.xProperty());
        this.imageView.translateYProperty().bind(newTower.yProperty());
    }
}
