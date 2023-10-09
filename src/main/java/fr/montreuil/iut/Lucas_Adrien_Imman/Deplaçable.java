package fr.montreuil.iut.Lucas_Adrien_Imman;

public abstract class Deplaçable extends Acteur {
    public Deplaçable(int x, int y,String id) {
        super(x,y,id);
    }

    public abstract boolean isOnBound();

    public abstract void algoDeplacement();

    public abstract boolean isOnObjective();
}
