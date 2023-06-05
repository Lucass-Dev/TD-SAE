package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemy;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.io.IOException;

public class EnnemyVue {
    private Pane levelPane;
    private VBox ennemy;
    private Label pv;
    private ImageView image;

    public EnnemyVue(Pane levelPane) {
        this.levelPane = levelPane;
        this.ennemy = new VBox();
        this.pv = new Label();
    }

    public void createEnnemySprite(Ennemy newEnnemy, int i) throws IOException {
        LevelVue lv = new LevelVue();
        HBox life = new HBox();
        lv.createBar(life, Color.RED, "", newEnnemy.lifeProperty(), newEnnemy.maxLifeProperty(), "lifeBar", 5, 100, false);
        this.image = new ImageView(new Image(Main.class.getResource("graphics/enemy/"+i+".png").openStream()));
        ennemy.getChildren().add(life);
        ennemy.getChildren().add(image);
        levelPane.getChildren().add(ennemy);
        this.ennemy.translateXProperty().bind(newEnnemy.xProperty());
        this.ennemy.translateYProperty().bind(newEnnemy.yProperty());
        this.ennemy.setId("E"+newEnnemy.getId());
        this.ennemy.setAlignment(Pos.CENTER);
        this.ennemy.getTransforms().add(new Translate(-20, -20));
    }
}
