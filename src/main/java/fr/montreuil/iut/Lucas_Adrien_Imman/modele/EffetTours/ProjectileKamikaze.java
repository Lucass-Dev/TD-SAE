package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class ProjectileKamikaze extends Projectile {

    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;

    public ProjectileKamikaze(int x, int y, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y, DEFAULT_INITIAL_HEALTH, DEFAULT_MAX_HEALTH, ennemyCible, md);
public class ProjectileKamikaze extends EffetTour {
    public ProjectileKamikaze(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible,16);
    }

    @Override
    public void agitSurLaCible() {
        if (isOnObjective()) {
            getEnnemyCible().reductionPv(1);
        }
    }

}
