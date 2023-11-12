package fr.montreuil.iut.Lucas_Adrien_Imman.vue;
import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ZoneElectrique;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ZoneRalentisseur;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class EffetTourVue {
    private final Pane levelPane;
    private ImageView imageView;
    Circle circle;


    public EffetTourVue(Pane levelPane) {
        this.levelPane = levelPane;
    }

    public void projectileSprite(EffetTour newEffetTour) throws IOException {

        if (!(newEffetTour instanceof ProjectileDegatsBrut)) { // cree les sprites correspondant

            if (newEffetTour instanceof ProjectileDotSH) {
                circle = new Circle(3);
                circle.setFill(Color.BLACK);
            }else if(newEffetTour instanceof ProjectileKamikaze){
                circle = new Circle(3);
                circle.setFill(Color.RED);
            }else if (newEffetTour instanceof ZoneRalentisseur) {
                circle = new Circle(60);
                circle.setFill(Color.BLUE);
                circle.setOpacity(0.2);
            }
            else if (newEffetTour instanceof ZoneElectrique) {
                circle = new Circle(70);
                circle.setFill(Color.BLANCHEDALMOND);
                int proba1 = (int) (Math.random() * 2);
                if(proba1%2==0){
                    circle.setFill(Color.AZURE);
                    circle.setOpacity(0.3);
                }
                circle.setOpacity(0.2);
            }
            else if(newEffetTour instanceof ProjectileKnockBack){
                circle = new Circle(5);
                circle.setFill(Color.VIOLET);
            }

            circle.translateXProperty().bind(newEffetTour.getXProperty());
            circle.translateYProperty().bind(newEffetTour.getYProperty());
            circle.setId(newEffetTour.getId());

            levelPane.getChildren().add(circle);
        }
        else {
            int proba2 = (int) (Math.random() * 3);
            this.imageView = new ImageView(new Image((Main.class.getResource("graphics/projectiles/" + proba2 + ".png")).openStream()));


            imageView.translateXProperty().bind(newEffetTour.getXProperty());
            imageView.translateYProperty().bind(newEffetTour.getYProperty());
            imageView.setId(newEffetTour.getId());
            levelPane.getChildren().add(imageView);
        }
    }
}

