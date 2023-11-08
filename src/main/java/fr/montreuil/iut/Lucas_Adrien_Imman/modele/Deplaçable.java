package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Acteur;

public abstract class Deplaçable extends Acteur {
    public Deplaçable(int x, int y,String id) {
        super(x,y,id);
    }

    public abstract boolean isOnBound();

    public abstract boolean isOnObjective();
}
