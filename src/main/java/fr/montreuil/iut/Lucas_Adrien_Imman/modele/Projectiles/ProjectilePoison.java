package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectilePoison extends Projectile {

    public ProjectilePoison(int x, int y, Ennemy e) {
        super(x, y, e);
    }

    @Override
    public void agitSurLaCible() {
        if (cibleAtteint()){
            getE().reductionPv(10);
        }
    }
}
