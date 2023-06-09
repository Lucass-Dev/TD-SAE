package fr.montreuil.iut.Lucas_Adrien_Imman.modele;


public class ProjectileRalentisseur extends Projectile  {



    public ProjectileRalentisseur(int x , int y , Ennemy e){
        super(x,y,e);
    }

    @Override
    public void agitSurLaCible() {
       int speed =  e.getSpeed() ;
        if (cibleAtteint()){
            e.reductionSpeed(10);
        }
        e.setSpeed(speed);
    }





}
