package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import javafx.collections.ObservableList;

public class ProjectileDegatsBrut extends EffetTour {


    public ProjectileDegatsBrut(int x, int y, Ennemy ennemyCible) {
        super(x, y, ennemyCible);

    }

    @Override
    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv envoyé en parametre
        if (isOnObjective())
            getEnnemyCible().reductionPv(10);
    }

}

