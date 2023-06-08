package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class TowerVue {

    private Pane levelPane;
    private ImageView imageView;

    public TowerVue(Pane levelPane){
        this.levelPane = levelPane;
    }

    public void createTowerSprite(Tower newTower) throws IOException {
        this.imageView = new ImageView(new Image(Main.class.getResource("graphics/tower/"+newTower.getSpriteIndex()+".png").openStream()));
        this.imageView.setPickOnBounds(true);
        this.imageView.setId(String.valueOf(newTower.getId()));
        levelPane.getChildren().add(imageView);
        this.imageView.translateXProperty().bind(newTower.xProperty());
        this.imageView.translateYProperty().bind(newTower.yProperty());
    }
}
