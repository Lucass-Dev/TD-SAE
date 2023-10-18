package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur extends  Entites {

    private SimpleIntegerProperty x, y; //Acteur
    public static int compteur=0;
    private String id;


    public Acteur(int x, int y,String id) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.id = id;
        compteur++;
    }

    public int getX() {
        return x.get();
    }
    public SimpleIntegerProperty xProperty() {
        return x;
    }
    public int getY() {
        return y.get();
    }
    public SimpleIntegerProperty yProperty() {
        return y;
    }
    public void setY(int y) {
        this.y.set(y);
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public String getId() {
        return id ;
    }


}
