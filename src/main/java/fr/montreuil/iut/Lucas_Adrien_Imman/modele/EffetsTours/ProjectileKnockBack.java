package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileKnockBack extends Projectile {

    public ProjectileKnockBack(int x, int y, int health, int maxHealth, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y, health, maxHealth, ennemyCible, md);
    }

    @Override
    public void agitSurLaCible() {
        int knockBack = 2;
        Ennemy e = getEnnemyCible();
        if (e.isCentered()) {
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
