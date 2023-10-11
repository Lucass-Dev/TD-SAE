package fr.montreuil.iut.Lucas_Adrien_Imman;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;

public abstract class Deplaçable extends Acteur {

    private ModeDeplacement modeDeplacement;


    public Deplaçable(int x, int y, String id, ModeDeplacement modeDeplacement) {
        super(x, y, id);
        this.modeDeplacement = modeDeplacement;
    }


    public abstract boolean isOnBound();



    public void algoDeplacement() {
        modeDeplacement.seDeplacer(this);
    }
    ;

    public abstract boolean isOnObjective();
}
