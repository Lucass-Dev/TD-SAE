package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileDotSH extends EffetTour {
    public ProjectileDotSH(int x, int y, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y, ennemyCible, md);
    }


    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv   envoyé en paramétre
        if (isOnObjective()) {
            getEnnemyCible().reductionPv(1);
        }
    }

}
