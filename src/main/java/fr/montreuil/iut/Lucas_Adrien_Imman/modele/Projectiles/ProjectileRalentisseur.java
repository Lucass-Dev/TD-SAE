package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;


import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileRalentisseur extends Projectile {



    public ProjectileRalentisseur(int x , int y , Ennemy e){
        super(x,y,e);
    }

    @Override
    public int agitSurLaCible() {
       int speed =  e.getSpeed() ;
        if (!cibleAtteint()){
            e.setSpeed(1);
        }
            return speed ;
    }



}
