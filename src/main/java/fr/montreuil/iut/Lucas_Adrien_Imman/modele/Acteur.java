package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {
    private Terrain terrain ;
    private IntegerProperty x , y ;
    private int vitesse ;

    public static int compteur=0;
    private String id;
    private int pv ;


    // private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // private int direction ;

    public Acteur (Terrain terrain) {
        this.id="A"+compteur;
        compteur++;
        this.x = new SimpleIntegerProperty((int)Math.random()*32);
        this.y = new SimpleIntegerProperty(64+(int)(Math.random()*(96-64)));
        this.terrain = terrain ;
        this.vitesse = 5 ;
        this.pv = 30 ;
    }

    public int getX () {
        return x.get();
    }

    public IntegerProperty xProperty () {
        return x;
    }

    public void setX (int x) {
        this.x.set(x);
    }

    public int getY () {
        return y.get();
    }

    public IntegerProperty yProperty () {
        return y;
    }

    public void setY (int y) {
        this.y.set(y);
    }

    public String getId() {
        return id;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public boolean estMort(){
        return (this.pv==0);

    }

    public void setPos(int x , int y){
        setX(x);
        setY(y);
    }

    public void deplacmentHaut() {
        setY(getY() - vitesse);
    }

    public void deplacmentBas() {
        setY(getY() + vitesse);
    }

    public void deplacmentGauche() {
        setX(getX() - vitesse);
    }

    public void deplacmentDroite() {
        setX(getX() + vitesse);
    }

    public void seDeplace() {
        deplacmentDroite();
        while (!terrain.tuileSol(getX(), getY())) {
            deplacmentBas();
            deplacmentGauche();
        }
    }


}
