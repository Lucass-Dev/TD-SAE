package fr.montreuil.iut.Lucas_Adrien_Imman.modele;


public class ProjectileRalentisseur extends Projectile  {



    public ProjectileRalentisseur(int x , int y , Ennemy e){
        super(x,y,e);
    }

    @Override
    public void agitSurLaCible() {
       int speed =  e.getSpeed() ;
        if (!cibleAtteint()){
            e.setSpeed(1);
        }

    }

    @Override
    public boolean cibleAtteint() {
        return false;
    }

    @Override
    public void moveProjectile() {
        System.out.println("fdff");
        setX(getX()+16);
        setY(getY()+16);
    }


}
