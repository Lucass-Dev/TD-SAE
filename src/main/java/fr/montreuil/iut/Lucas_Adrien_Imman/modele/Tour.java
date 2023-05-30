package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Tour {
    private IntegerProperty x, y;
    private Terrain terrain;

    public Tour(Terrain terrain) {
        this.terrain = terrain;
        this.x = new SimpleIntegerProperty();
        this.y = new SimpleIntegerProperty();
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

    public boolean estPlaceable(int x, int y) {
        return terrain.tuileSol(x, y);
    }

    public Acteur attaque(ObservableList<Acteur> acteurs) {
        for (Acteur m : acteurs) {
                if ((this.getY()-15<=m.getY() && m.getY()<= this.getY()+15) && (this.getX()-15<=m.getX() && m.getX() <= this.getX()+15)){
                    return m;
                }
            }
            return null;
        }

}



