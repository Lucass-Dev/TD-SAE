package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TowerVue {

    private Pane levelPane;
    private ImageView imageTower;

    public TowerVue(Pane levelPane){
        this.levelPane = levelPane;
    }

    public void createTowerSprite(Tower newTower) throws IOException {
        this.imageTower = new ImageView(new Image(Main.class.getResource("graphics/tower/1.png").openStream()));
        levelPane.getChildren().add(imageTower);
        this.imageTower.translateXProperty().bind(newTower.xProperty());
        this.imageTower.translateYProperty().bind(newTower.yProperty());
    }
}
