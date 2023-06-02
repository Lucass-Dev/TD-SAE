package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.vue.EnnemyVue;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.Pane;

public class Projectile {
  private IntegerProperty x , y ;
    private String id;
    public static int compteur=0;

    public Projectile(IntegerProperty x, IntegerProperty y) {
        this.x = x;
        this.y = y;
        this.id= "P" + compteur;
        compteur++;
    }

    public String getId() {
        return id ;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public boolean cibleAtteint(int x , int y){
        return this.x.getValue() == x && this.y.getValue() ==y ;
    }

   public void creeProjectile(int x , int y ){
        if (this.getX() != x && this.getY() != y){
            setX(this.getX()-1);
        }


    }

}
