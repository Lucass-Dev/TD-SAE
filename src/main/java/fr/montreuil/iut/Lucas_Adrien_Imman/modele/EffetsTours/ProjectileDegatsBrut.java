package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileDegatsBrut extends EffetTour {

    private static final int DEFAULT_INITIAL_HEALTH = 100;
    private static final int DEFAULT_MAX_HEALTH = 100;

    public ProjectileDegatsBrut(int x , int y , Ennemy ennemyCible, ModeDeplacement md){
     super(x,y,DEFAULT_INITIAL_HEALTH,DEFAULT_MAX_HEALTH,ennemyCible,md);

    }

    @Override
    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv envoyé en parametre
        if(isOnObjective())
        getEnnemyCible().reductionPv(10);
    }


}
