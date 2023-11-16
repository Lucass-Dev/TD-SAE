package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneRalentisseur extends EffetTour {

    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;

    public ZoneRalentisseur(int x , int y , Ennemy ennemyCible, ModeDeplacement md){
        super(x, y,DEFAULT_INITIAL_HEALTH , DEFAULT_MAX_HEALTH,0,ennemyCible , 75,md);
    }

    @Override
    public void agirSurLaCible() { //si un cible est détecté , sa vitesse sera réduite
        if (isOnObjective()){
            getEnnemyCible().setSpeed(1);
        }
    }


     /* public   boolean cibleAtteint() {
        int range = 75 ;
        Ennemy ennemyCible = getEnnemyCible() ;
        return ((this.getY()-range<=ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<=ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }*/

}