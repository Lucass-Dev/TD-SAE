package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.AgirSurCible;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplaçable;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import javafx.collections.ObservableList;


public abstract class EffetTour extends Deplaçable implements AgirSurCible {

    private Ennemy ennemyCible;
    private int range ;


    public EffetTour(int x , int y , Ennemy ennemyCible , int range){
        super(x,y,"P" + compteur);
        this.ennemyCible = ennemyCible;
        this.range = range ;
    }

    public abstract void agitSurLaCible();

    //SETTER
    public Ennemy getEnnemyCible() {
        return ennemyCible;
    }

    //OTHER METHODS
    public void algoDeplacement() { // déplacement de projectiles vers la cible
        double  posX = ennemyCible.getX() - this.getX() ;
        double  posY = ennemyCible.getY() - this.getY() ;
        double dirX, dirY;

        double totalDis = Math.sqrt(posX * posX + posY * posY);  //ALGO déplacement projectile

        dirX = posX  / totalDis  ;
        dirY = posY  / totalDis ;


        double newPosX = this.getX() + (5 * dirX);
        double newPosY = this.getY() + (5 * dirY);

        setX((int) newPosX);
        setY((int) newPosY);
    }

    public   boolean isOnObjective() { //return true si la projectile a atteint la (x et y cible ) cible
       // int range = 16 ;
        return ((this.getY()-range<= ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<= ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }

    public boolean isOnBound(){
        return this.getX() >640  && this.getY() >640;
    }

}
