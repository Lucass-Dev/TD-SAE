package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileKnockBack extends Projectile{
    public ProjectileKnockBack(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible);
    }

    @Override
    public void agitSurLaCible() {
        int knockBack = 2; //fait reculer l'ennemi ciblé de 2 pixel par rapport a sa direction actuel
        Ennemy e = getEnnemyCible() ;
        if(e.isCentered()){
            if(e.getDirection()==2){
                e.setX(e.getX()-knockBack);
            }
            else if(e.getDirection() == 4){
                e.setX(e.getX()+knockBack);
            }
            else if(e.getDirection()==1){
                e.setY(e.getY()+knockBack);
            }
            else if(e.getDirection()==3){
                e.setY(e.getY()-knockBack);
            }
        }
    }
}
