package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class Projectile {
    private IntegerProperty x , y ;
    private Ennemy ennemyCible;
    private String id;
    public static int compteur=0;



    public Projectile(int x , int y , Ennemy ennemyCible){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.ennemyCible = ennemyCible;
        this.id= "P" + compteur;
        compteur++;
    }

    public abstract void agitSurLaCible();


    //GETTER
    public int getY() {
        return y.get();
    }

    public String getId() {
        return id ;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }


    public IntegerProperty yProperty() {
        return y;
    }

    //SETTER
    public Ennemy getEnnemyCible() {
        return ennemyCible;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void setX(int x) {
        this.x.set(x);
    }

    //OTHER METHODS
    public void placement() { // déplacement de projectiles vers la cible
        double  posX = ennemyCible.getX() - this.getX() ;
        double  posY = ennemyCible.getY() - this.getY() ;
        double dirX, dirY;

        double totalDis = Math.sqrt(posX * posX + posY * posY);

        dirX = posX  / totalDis  ;
        dirY = posY  / totalDis ;


        double newPosX = this.getX() + (5 * dirX);
        double newPosY = this.getY() + (5 * dirY);

        setX((int) newPosX);
        setY((int) newPosY);
    }

    public   boolean cibleAtteint() { //return true si la projectile a atteint la (x et y cible ) cible
        int range = 16 ;
        return ((this.getY()-range<= ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<= ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }

    public boolean isOnBound(){
        return this.getX() >640  && this.getY() >640;
    }
}
