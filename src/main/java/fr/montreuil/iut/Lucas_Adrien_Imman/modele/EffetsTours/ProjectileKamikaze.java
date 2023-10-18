package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileKamikaze extends Projectile {

    public ProjectileKamikaze(int x, int y, int health, int maxHealth, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y, health, maxHealth, ennemyCible, md);
    }

    @Override
    public void agitSurLaCible() {
        if (isOnObjective()) {
            getEnnemyCible().reductionPv(1);
        }
    }
}
