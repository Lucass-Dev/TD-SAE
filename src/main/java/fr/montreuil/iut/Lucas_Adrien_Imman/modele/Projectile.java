package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class Projectile {
    private IntegerProperty x , y ;
    protected Ennemy e ;
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

    public boolean isOnBound(){
        return this.getX() >640  && this.getY() >640;
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

    public abstract void agitSurLaCible();

    public  abstract  boolean cibleAtteint() ;

    public abstract void moveProjectile() ;



}
