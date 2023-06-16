package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ProjectileVue {
    private final Pane levelPane;
    private ImageView imageView;
    Circle circle;


    public ProjectileVue(Pane levelPane) {
        this.levelPane = levelPane;
    }

    public void projectileSprite(Projectile newProjectile) throws IOException {

        if (!(newProjectile instanceof ProjectileDegatsBrut)) { // cree les sprites correspondant

            if (newProjectile instanceof ProjectileDotSH) {
                circle = new Circle(3);
                circle.setFill(Color.BLACK);
            }else if(newProjectile instanceof  ProjectileKamikaze){
                circle = new Circle(3);
                circle.setFill(Color.RED);
            }else if (newProjectile instanceof ZoneRalentisseur) {
                circle = new Circle(60);
                circle.setFill(Color.BLUE);
                circle.setOpacity(0.2);
            }
            else if (newProjectile instanceof ZoneElectrique) {
                circle = new Circle(70);
                circle.setFill(Color.BLANCHEDALMOND);
                int proba1 = (int) (Math.random() * 2);
                if(proba1%2==0){
                    circle.setFill(Color.AZURE);
                    circle.setOpacity(0.3);
                }
                circle.setOpacity(0.2);
            }
            else if(newProjectile instanceof ProjectileKnockBack){
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




