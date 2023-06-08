package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Projectile2 extends  Projectile{
    private IntegerProperty x , y ;
    private Ennemy e ;
    private String id;
    public static int compteur=0;


    public Projectile2(int x , int y , Ennemy e){
     super(x,y,e);
        this.id= "P2" + compteur;
        compteur++;
    }


    public boolean cibleAtteint() {
        while((this.getY()<=e.getY()+16 && e.getY()<= this.getY()) && (this.getX()<=e.getX()+16 && e.getX() <= this.getX())) {
         e.setSpeed(1);
            return true;
        }
        return false ;
    }

    public void moveProjectile() {
        super.moveProjectile();
    }


}