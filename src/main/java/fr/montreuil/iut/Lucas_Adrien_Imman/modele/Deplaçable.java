package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;

public abstract class Deplaçable extends Acteur {
    private ModeDeplacement modeDeplacement;

    public Deplaçable(int x, int y, int health, int maxHealth, ModeDeplacement modeDeplacement) {
        super(x, y, health, maxHealth);
        this.modeDeplacement = modeDeplacement;
    }


    public abstract boolean isOnBound();



    public void algoDeplacement() {
        modeDeplacement.seDeplacer(this);
    }

    public ModeDeplacement getModeDeplacement() { return this.modeDeplacement; }


    public abstract boolean isOnObjective();
}
