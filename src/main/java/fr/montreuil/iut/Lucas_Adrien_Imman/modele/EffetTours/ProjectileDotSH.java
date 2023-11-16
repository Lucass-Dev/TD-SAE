package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class ProjectileDotSH extends EffetTour {

    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;

    public ProjectileDotSH(int x,
                           int y,
                           Ennemy ennemyCible,
                           ModeDeplacement md
    ) {

        super(x, y, DEFAULT_INITIAL_HEALTH, DEFAULT_MAX_HEALTH,2, ennemyCible, 16,md);
    }



}
