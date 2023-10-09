package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Projectiles;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileKamikaze extends Projectile {
    public ProjectileKamikaze(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible);
    }


    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv   envoyé en paramétre
        if (isOnObjective()) {
            getEnnemyCible().reductionPv(1);
        }
    }

}
