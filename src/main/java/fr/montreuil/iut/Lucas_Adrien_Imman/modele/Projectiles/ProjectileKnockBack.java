package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileKnockBack extends Projectile{
    public ProjectileKnockBack(int x, int y, Ennemy e) {
        super(x, y, e);
    }

    @Override
    public void agitSurLaCible() {
        int knckBack = 2;
        Ennemy e = getE() ;
        if(e.isCentered()){
            if(e.getDirection()==2){
                e.setX(e.getX()-knckBack);
            }
            else if(e.getDirection() == 4){
                e.setX(e.getX()+knckBack);
            }
            else if(e.getDirection()==1){
                e.setY(e.getY()+knckBack);
            }
            else if(e.getDirection()==3){
                e.setY(e.getY()-knckBack);
            }
        }
    }
}
