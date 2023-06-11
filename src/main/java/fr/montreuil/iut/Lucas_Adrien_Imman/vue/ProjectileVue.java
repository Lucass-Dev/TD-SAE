package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.ProjectileDegatsBrut;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.ProjectilePoison;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.ProjectileRalentisseur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ProjectileVue {
    private Pane levelPane;


    public ProjectileVue(Pane levelPane) {
        this.levelPane = levelPane;
    }

    public void projectileSprite(Projectile newProjectile) {
        Circle circle = null;

        if (newProjectile instanceof ProjectileDegatsBrut){
            circle = new Circle(5);
            circle.setFill(Color.YELLOW);
        }
        else if (newProjectile instanceof ProjectileRalentisseur) {
            circle = new Circle(60);
            circle.setFill(Color.BLUE);
            circle.setOpacity(0.5);
        }
        else if (newProjectile instanceof ProjectilePoison){
            circle = new Circle(10);
           int p =  ((int) ((Math.random() * 2)));
           if(p == 0)
            circle.setFill(Color.GREEN);
           else
            circle.setFill(Color.BROWN);
        }

        circle.translateXProperty().bind(newProjectile.xProperty());
        circle.translateYProperty().bind(newProjectile.yProperty());
        circle.setId(newProjectile.getId());
        levelPane.getChildren().add(circle);

    }


}
