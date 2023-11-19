package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplaçable;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public abstract class EffetTour extends Deplaçable {
    private Ennemy ennemyCible;
    private int range ;
    private int degats ;


    public EffetTour(int x,
                     int y,
                     int health,
                     int maxHealth,
                     int degats,
                     Ennemy ennemyCible,
                     int range,
                     ModeDeplacement md
    ){
        super(x, y, health, maxHealth, md);
        this.ennemyCible = ennemyCible;
        this.range = range;
        this.degats = degats;
    }



    public void agirSurLaCible(){
        getEnnemyCible().reductionPv(degats);
    }

    public void agit() {
        if (this.isOnObjective()) {
            agirSurLaCible();
            this.setHealth(0);
        }
    }



    public   boolean isOnObjective() { //return true si la projectile a atteint la (x et y cible ) cible

        return ((this.getYValue()-range<= ennemyCible.getYValue() && ennemyCible.getYValue()<= this.getYValue()+range) && (this.getXValue()-range<= ennemyCible.getXValue() && ennemyCible.getXValue() <= this.getXValue()+range)) ;
    }

    public boolean isOnBound(){
        return this.getXValue() >640  && this.getYValue() >640;
    }

    public Ennemy getEnnemyCible() {
        return ennemyCible;
    }
}
