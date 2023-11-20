package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneRalentisseur extends EffetTour {

    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;

    public ZoneRalentisseur(int x , int y , Ennemy ennemyCible, ModeDeplacement md){
        super(x, y, DEFAULT_INITIAL_HEALTH, DEFAULT_MAX_HEALTH, ennemyCible, md);
    }

    @Override
    public void agitSurLaCible() { //si un cible est détecté , sa vitesse sera réduite
        if (isOnObjective()){
            getEnnemyCible().setSpeed(1);
        }
    }
    public   boolean isOnObjective() {
        int range = 75 ;
        Ennemy ennemyCible = getEnnemyCible() ;
        return ((this.getYValue()-range<=ennemyCible.getYValue() && ennemyCible.getYValue()<= this.getYValue()+range) && (this.getXValue()-range<=ennemyCible.getXValue() && ennemyCible.getXValue() <= this.getXValue()+range)) ;
    }
    @Override
    public void algoDeplacement(){
        setXValue(getXValue());
        setYValue(getYValue());
    }


}