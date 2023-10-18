package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

/* import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneElectrique extends EffetTour {

    public ZoneElectrique(int x, int y, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y, ennemyCible, md);
    }

    @Override
    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv envoyé en parametre
        if (isOnObjective()){
            getEnnemyCible().reductionPv(1);
        }
    }

    @Override
    public   boolean isOnObjective() {
        int range = 80 ;
        Ennemy ennemyCible = getEnnemyCible() ;
        return ((this.getY()-range<=ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<=ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }

    @Override
    public void algoDeplacement(){
        setX(getX());
        setY(getY());
    }
}
*/