package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplaçable;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public abstract class Projectile extends Deplaçable {
    private Ennemy ennemyCible;

    public Projectile(int x, int y, int health, int maxHealth, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y, health, maxHealth, md);
        this.ennemyCible = ennemyCible;
    }

    public abstract void agitSurLaCible();

    @Override
    public  boolean isOnObjective() { //return true si le projectile a atteint la cible (x et y)
        int range = 16 ;
        return ((this.getYValue()-range<= ennemyCible.getYValue()) && ennemyCible.getYValue()<= this.getYValue()+range) && (this.getXValue()-range<= ennemyCible.getXValue() && ennemyCible.getXValue() <= this.getXValue()+range);
    }

    public boolean isOnBound(){
        return this.getXValue() >640  && this.getYValue() >640;
    }

    public Ennemy getEnnemyCible() {
        return ennemyCible;
    }
}
