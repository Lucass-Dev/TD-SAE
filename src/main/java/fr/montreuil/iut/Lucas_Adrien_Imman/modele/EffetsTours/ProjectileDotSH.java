package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class ProjectileDotSH extends Projectile {

    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;

    public ProjectileDotSH(int x, int y, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y, DEFAULT_INITIAL_HEALTH, DEFAULT_MAX_HEALTH, ennemyCible, md);
    }

    @Override
    public void agitSurLaCible() {
        if (isOnObjective()) {
            getEnnemyCible().reductionPv(1);
        }
    }
}
