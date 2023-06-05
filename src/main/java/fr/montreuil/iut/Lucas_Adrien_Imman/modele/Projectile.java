package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.Main;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.EnnemyVue;
import fr.montreuil.iut.Lucas_Adrien_Imman.vue.TowerVue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

public class Projectile {
    private IntegerProperty x , y ;
    private Ennemy e ;
    private String id;
    public static int compteur=0;


    public Projectile(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.id= "P" + compteur;
        compteur++;
    }
    public Projectile(int x , int y , Ennemy e){
        super();
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.e = e;

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

    public boolean cibleAtteint() {
        return this.getX() == e.getX() && this.getY() == e.getY();

    }

    public void creeProjectile() {
/*
        double  posX = e.getX() - this.getX() ;
        double  posY = e.getY() - this.getX() ;

        double totalDis = Math.sqrt(posX * posX + posY * posY);

        double dircX = posX / totalDis ;
        double dircY = posY / totalDis ;


        double newPosX = this.getX() + (1 * dircX);
        double newPosY = this.getY() + (1 * dircY);

        setX((int) newPosX*32);
        setY((int)newPosY*32);


        if(cibleAtteint())
        e.setLife((e.getLife()-50));
*/

       for (int i = 0; i < 10; i++) {

           setX(getX()+(e.getX()-getX()) );
           setY(getY()+(e.getY()-getY()));
       }

        if(cibleAtteint())
            e.setLife((e.getLife()-50));
    }


}
