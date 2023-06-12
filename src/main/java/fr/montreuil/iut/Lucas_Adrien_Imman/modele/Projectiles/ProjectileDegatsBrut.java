package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileDegatsBrut extends Projectile {


    public ProjectileDegatsBrut(int x , int y , Ennemy e){
     super(x,y,e);

    }

    @Override
    public int agitSurLaCible() {
        if(cibleAtteint())
        e.reductionPv(10);
        return 0;
    }


}
