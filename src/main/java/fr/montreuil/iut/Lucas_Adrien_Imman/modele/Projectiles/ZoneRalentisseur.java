package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;


import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneRalentisseur extends Projectile {



    public ZoneRalentisseur(int x , int y , Ennemy ennemyCible){
        super(x,y,ennemyCible);
    }

    @Override
    public void agitSurLaCible() {
        if (cibleAtteint()){
            getEnnemyCible().setSpeed(1);
        }
    }
    public   boolean cibleAtteint() {
        int range = 75 ;
        Ennemy ennemyCible = getEnnemyCible() ;
        return ((this.getY()-range<=ennemyCible.getY() && ennemyCible.getY()<= this.getY()+range) && (this.getX()-range<=ennemyCible.getX() && ennemyCible.getX() <= this.getX()+range)) ;
    }

    public void placement(){
        setX(getX());
        setY(getY());
    }


}
