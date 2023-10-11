package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.Deplaçable;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;


public abstract class EffetTour extends Deplaçable {

    private Ennemy ennemyCible;


    public EffetTour(int x , int y , Ennemy ennemyCible, ModeDeplacement md){
        super(x,y,"P" + compteur, md);
        this.ennemyCible = ennemyCible;
    }


    public abstract void agitSurLaCible();


    public Ennemy getEnnemyCible() {
        return ennemyCible;
    }



    public  boolean isOnObjective() { //return true si la projectile a atteint la (x et y cible ) cible
        int range = 16 ;
        return ((this.getY()-range<= ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<= ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }

    public boolean isOnBound(){
        return this.getX() >640  && this.getY() >640;
    }

}
