package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneRalentisseur extends EffetTour {



    public ZoneRalentisseur(int x , int y , Ennemy ennemyCible){
        super(x,y,ennemyCible,75);
    }

    @Override
    public void agitSurLaCible() { //si un cible est détecté , sa vitesse sera réduite
        if (isOnObjective()){
            getEnnemyCible().setSpeed(1);
        }
    }

    public void algoDeplacement(){
        setX(getX());
        setY(getY());
    }


     /* public   boolean cibleAtteint() {
        int range = 75 ;
        Ennemy ennemyCible = getEnnemyCible() ;
        return ((this.getY()-range<=ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<=ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }*/

}
