/*package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneElectrique extends Projectile{

    public ZoneElectrique(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible);
    }

    @Override
    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv envoyé en parametre
        if (cibleAtteint()){
            getEnnemyCible().reductionPv(1);
        }
    }

    @Override
    public   boolean cibleAtteint() {
        int range = 80 ;
        Ennemy ennemyCible = getEnnemyCible() ;
        return ((this.getY()-range<=ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<=ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }

    public void placement(){
        setX(getX());
        setY(getY());
    }
}
*/