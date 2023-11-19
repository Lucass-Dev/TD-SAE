package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ZoneElectrique extends EffetTour {
    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;
    public ZoneElectrique(int x, int y, Ennemy ennemyCible, ModeDeplacement md) {
        super(x, y,DEFAULT_INITIAL_HEALTH , DEFAULT_MAX_HEALTH,1,ennemyCible , 80,md);

    }


}
