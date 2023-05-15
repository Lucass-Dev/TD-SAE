package com.application.towerdefensesae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {
    private IntegerProperty x;
    private IntegerProperty y;

    public Acteur () {
        this.x = new SimpleIntegerProperty(15);
        this.y = new SimpleIntegerProperty(84);
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

    public void avance(){


        for (int i = 0; i <10 ; i++) {


            setX((int) (Math.random() * 100));
            setY((int) (Math.random() * 100));

        }
    }
}
