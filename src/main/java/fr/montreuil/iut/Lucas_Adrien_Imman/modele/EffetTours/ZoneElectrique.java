package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneElectrique extends EffetTour {

    public ZoneElectrique(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible , 80);

    }

    @Override
    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv envoyé en parametre
        if (isOnObjective()){
            getEnnemyCible().reductionPv(1);
        }
    }


    public void algoDeplacement(){
        setX(getX());
        setY(getY());
    }

    /*
    public   boolean cibleAtteint() {
        int range = 80 ;
        Ennemy ennemyCible = getEnnemyCible() ;
        return ((this.getY()-range<=ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<=ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }
*/
}
*/