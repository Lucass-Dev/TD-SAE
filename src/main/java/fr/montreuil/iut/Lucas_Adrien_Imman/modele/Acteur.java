package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Acteur {
    private Terrain terrain ;
    private IntegerProperty x , y ;
    private int vitesse ;
   // private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int direction ;

    public Acteur (Terrain terrain) {
        this.x = new SimpleIntegerProperty((int)Math.random()*32);
        this.y = new SimpleIntegerProperty(64+(int)(Math.random()*(96-64)));
        this.terrain = terrain ;
        this.vitesse = 30 ;
        this.direction = -1 ;
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
            setX(getX() + (this.vitesse*direction));

        }

    }


    public boolean verifObjectif(){
        return (terrain.tuile(getX(),getY()))==2 ;
    }
}
