package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;

public class ProjectileVue {
    private final Pane levelPane;
    private ImageView imageView;
    Circle circle;


    public ProjectileVue(Pane levelPane) {
        this.levelPane = levelPane;
    }

    public void projectileSprite(Projectile newProjectile) throws IOException {

        if (!(newProjectile instanceof ProjectileDegatsBrut)) {
            if (newProjectile instanceof ProjectileDotSH) {
                circle = new Circle(3);
                circle.setFill(Color.BLACK);
            } else if (newProjectile instanceof ProjectileRalentisseur) {
                circle = new Circle(60);
                circle.setFill(Color.BLUE);
                circle.setOpacity(0.2);
            }else if(newProjectile instanceof ProjectilePoison){
                circle = new Circle(10);
                int proba1 = ((int) ((Math.random() * 2)));
                if (proba1 == 0)
                    circle.setFill(Color.VIOLET);
                else
                    circle.setFill(Color.BROWN);
            }else if(newProjectile instanceof ProjectileKnockBack){
                circle = new Circle(5);
             circle.setFill(Color.VIOLET);
            }
            circle.translateXProperty().bind(newProjectile.xProperty());
            circle.translateYProperty().bind(newProjectile.yProperty());
            circle.setId(newProjectile.getId());
            levelPane.getChildren().add(circle);
        }
        else {
            int proba2 = (int) (Math.random() * 3);
            this.imageView = new ImageView(new Image((Main.class.getResource("graphics/projectiles/" + proba2 + ".png")).openStream()));


            imageView.translateXProperty().bind(newProjectile.xProperty());
            imageView.translateYProperty().bind(newProjectile.yProperty());
            imageView.setId(newProjectile.getId());
            levelPane.getChildren().add(imageView);
        }
    }
}




