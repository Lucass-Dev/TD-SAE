package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;

public abstract class Deplaçable extends Acteur {
    private ModeDeplacement modeDeplacement;

    public Deplaçable(int x, int y, int health, int maxHealth, ModeDeplacement modeDeplacement) {
        super(x, y, health, maxHealth);
        this.modeDeplacement = modeDeplacement;
    }


    public abstract boolean isOnBound();

    public void move(){ //TODO
         }


    public void deplacement() {
        modeDeplacement.seDeplacer(this);
    }

    public ModeDeplacement getModeDeplacement() { return this.modeDeplacement; }


    public boolean isOnObjective(){
        int[] pos = new int[2];
        pos[0] = this.getXValue()/32;
        pos[1] = this.getYValue()/32;
        return Environment.getInstance().getGround().getTileValue(pos) == 7;
    }
}
