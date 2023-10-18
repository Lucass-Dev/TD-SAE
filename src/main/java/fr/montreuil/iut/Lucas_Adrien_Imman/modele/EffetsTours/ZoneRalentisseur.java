package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;


/*import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneRalentisseur extends EffetTour {



    public ZoneRalentisseur(int x , int y , Ennemy ennemyCible, ModeDeplacement md){
        super(x,y,ennemyCible, md);
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
        return ((this.getY()-range<=ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<=ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }
    @Override
    public void algoDeplacement(){
        setX(getX());
        setY(getY());
    }


}
*/