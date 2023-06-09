package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.ProjectileDegatsBrut;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.ProjectileRalentisseur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Shape3D;

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
      else if (newProjectile instanceof ProjectileRalentisseur)
          circle = new Circle(60);
          circle.setFill(Color.BLUE);


        circle.translateXProperty().bind(newProjectile.xProperty());
        circle.translateYProperty().bind(newProjectile.yProperty());
        circle.setId(newProjectile.getId());
        levelPane.getChildren().add(circle);

    }


}
