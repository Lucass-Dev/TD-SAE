package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur extends Entites {
    SimpleIntegerProperty x; // Position x
    SimpleIntegerProperty y; // Position y

    public Acteur(int x, int y, int initialHealth, int maxHealth) {
        super(initialHealth, maxHealth);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    // Getters et Setters pour x et y
    public int getXValue() {
        return x.getValue();
    }

    public void setXValue(int x) {
        this.x.set(x);
    }

    public int getYValue() {
        return y.getValue();
    }

    public void setYValue(int y) {
        this.y.set(y);
    }

    public SimpleIntegerProperty getXProperty() {return this.x;}

    public SimpleIntegerProperty getYProperty() {return this.y;}

    // Autres méthodes spécifiques à Acteurs
}

