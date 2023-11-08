package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileKamikaze extends EffetTour {
    public ProjectileKamikaze(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible,16);
    }


    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv   envoyé en paramétre
        if (isOnObjective()) {
            getEnnemyCible().reductionPv(1);
        }
    }

}
