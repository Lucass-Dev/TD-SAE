package fr.montreuil.iut.Lucas_Adrien_Imman.vue;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectile;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectile2;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectile3;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ProjectileVue {
    private Pane levelPane;


    public ProjectileVue(Pane levelPane) {
        this.levelPane = levelPane;
    }

    public void projectileSprite(Projectile newProjectile){
        Circle circle =  new Circle(5);

        circle.setFill(Color.VIOLET);
      if(newProjectile instanceof Projectile2) {
          circle.setFill(Color.BLUE);

      }
      else if (newProjectile instanceof Projectile3) {
          circle.setFill(Color.RED);
      }


        circle.setId(newProjectile.getId());
        System.out.println(newProjectile.getId());
        circle.translateXProperty().bind(newProjectile.xProperty());
        circle.translateYProperty().bind(newProjectile.yProperty());
        levelPane.getChildren().add(circle);

    }
}
