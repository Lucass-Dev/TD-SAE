package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class EnnemyVue {
    private Pane levelPane;
    private ImageView imageEnemy;
    // private VBox vie;
    // private VBox ennemy;


    public EnnemyVue(Pane levelPane) {
        this.levelPane = levelPane;
    }

    public void createEnnemySprite(Ennemy newEnnemy) throws IOException {

        this.imageEnemy = new ImageView(new Image(Main.class.getResource("graphics/enemy/.exe.png").openStream()));
        this.imageEnemy.translateXProperty().bind(newEnnemy.xProperty());
        this.imageEnemy.translateYProperty().bind(newEnnemy.yProperty());
        this.imageEnemy.setId(newEnnemy.getId());
        levelPane.getChildren().add(imageEnemy);


    }

}
