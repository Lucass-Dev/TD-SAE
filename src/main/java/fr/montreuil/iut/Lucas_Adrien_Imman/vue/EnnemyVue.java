package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemy;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    public void createEnnemySprite(Ennemy newEnnemy) throws IOException {

        this.image = new ImageView(new Image(Main.class.getResource("graphics/ennemy/Virus.png").openStream()));
        this.pv.textProperty().bind(newEnnemy.getLife().asString());
        ennemy.getChildren().add(pv);
        ennemy.getChildren().add(image);
        levelPane.getChildren().add(ennemy);
        this.ennemy.translateXProperty().bind(newEnnemy.xProperty());
        this.ennemy.translateYProperty().bind(newEnnemy.yProperty());
        this.ennemy.setId("E"+newEnnemy.getId());

    }
}
