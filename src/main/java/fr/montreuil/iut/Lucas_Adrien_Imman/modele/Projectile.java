package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Projectile {
    private IntegerProperty x , y ;
    private Ennemy e ;
    private String id;
    public static int compteur=0;


    public Projectile(int x , int y , Ennemy e){
        super();
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.e = e;
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

    public boolean cibleAtteint() {
         if((this.getY()<=e.getY()+16 && e.getY()<= this.getY()) && (this.getX()<=e.getX()+16 && e.getX() <= this.getX())) {
             e.setLife((e.getLife() - 10));
             return true;
         }
         return false ;
    }

    public void moveProjectile() {

        double  posX = e.getX() - this.getX() ;
        double  posY = e.getY() - this.getY() ;
        double dirX, dirY;

        double totalDis = Math.sqrt(posX * posX + posY * posY);

        dirX = posX  / totalDis  ;
        dirY = posY  / totalDis ;


        double newPosX = this.getX() + (5 * dirX);
        double newPosY = this.getY() + (5 * dirY);

        setX((int) newPosX);
        setY((int) newPosY);



    }


}
