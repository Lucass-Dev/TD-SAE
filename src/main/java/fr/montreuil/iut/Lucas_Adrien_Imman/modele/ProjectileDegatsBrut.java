package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

public class ProjectileDegatsBrut extends  Projectile{


    public ProjectileDegatsBrut(int x , int y , Ennemy e){
     super(x,y,e);

    }

    @Override
    public void agitSurLaCible() {
        if(cibleAtteint())
        e.reductionPv(10);
    }

    @Override
    public boolean cibleAtteint() {
        int range = 16 ;
        return ((this.getY()-range<=e.getY() && e.getY()<= this.getY()+range) && (this.getX()-range<=e.getX() && e.getX() <= this.getX()+range)) ;
    }

    @Override
    public void moveProjectile() {
        double  posX = e.getX() - this.getX() ;
        double  posY = e.getY() - this.getY() ;
        double dirX, dirY;

        double totalDis = Math.sqrt(posX * posX + posY * posY);

        dirX = posX  / totalDis  ;
        dirY = posY  / totalDis ;


        double newPosX = this.getX() + (5 * dirX);
        double newPosY = this.getY() + (5 * dirY);

        setX((int) newPosX);
        setY((int) newPosY);
    }


}
