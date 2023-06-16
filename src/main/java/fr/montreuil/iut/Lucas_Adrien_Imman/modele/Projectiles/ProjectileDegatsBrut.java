package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileDegatsBrut extends Projectile {


    public ProjectileDegatsBrut(int x , int y , Ennemy ennemyCible){
     super(x,y,ennemyCible);

    }

    @Override
    public void agitSurLaCible() {
        if(cibleAtteint())
        getEnnemyCible().reductionPv(10);
    }


}
