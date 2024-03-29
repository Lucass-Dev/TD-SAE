package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

import java.io.IOException;

public class EnnemyVue {
    private Pane levelPane;
    private VBox ennemy;
    private Label pv;
    private ImageView image;
    private BarVue bv;

    public EnnemyVue(Pane levelPane) {
        this.levelPane = levelPane;
        this.ennemy = new VBox();
        this.pv = new Label();
        this.bv = new BarVue();
    }

    public void createEnnemySprite(Ennemy newEnnemy) throws IOException {
        HBox life = new HBox();
        bv.createBar(life, Color.RED, "Life", newEnnemy.lifeProperty(), newEnnemy.maxLifeProperty(), "lifeBar", false, true);
        this.image = new ImageView(new Image(Main.class.getResource("graphics/enemy/"+newEnnemy.getSpriteIndex()+".png").openStream()));


        this.ennemy.translateXProperty().bind(newEnnemy.xProperty());
        this.ennemy.translateYProperty().bind(newEnnemy.yProperty());
        this.ennemy.setId(newEnnemy.getId());
        this.ennemy.setAlignment(Pos.CENTER);
        ennemy.getChildren().add(life);
        ennemy.getChildren().add(image);
        levelPane.getChildren().add(ennemy);

        this.ennemy.getTransforms().add(new Translate(-20, -20));
    }
}
