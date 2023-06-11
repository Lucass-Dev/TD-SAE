package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles.Projectile;

public class ProjectilePoison extends Projectile {
    public ProjectilePoison(int x, int y, Ennemy e) {
        super(x, y, e);
    }


    public void agitSurLaCible() {
        if(cibleAtteint())
            e.reductionPv(10);
    }

}
