package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;


import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileRalentisseur extends Projectile {



    public ProjectileRalentisseur(int x , int y , Ennemy e){
        super(x,y,e);
    }

    @Override
    public void agitSurLaCible() {
        if (!cibleAtteint()){
            e.setSpeed(1);
        }
    }
    public   boolean cibleAtteint() {
        int range = 75 ;
        return ((this.getY()-range<=e.getY() && e.getY()<= this.getY()+range) && (this.getX()-range<=e.getX() && e.getX() <= this.getX()+range)) ;
    }

    public void moveProjectile(){
        setX(getX());
        setY(getY());
    }


}
