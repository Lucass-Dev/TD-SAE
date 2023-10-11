package fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetsTours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class ProjectileDegatsBrut extends EffetTour {


    public ProjectileDegatsBrut(int x , int y , Ennemy ennemyCible, ModeDeplacement md){
     super(x,y,ennemyCible, md);

    }

    @Override
    public void agitSurLaCible() {//si un cible est détecté , il perdera  x pv envoyé en parametre
        if(isOnObjective())
        getEnnemyCible().reductionPv(10);
    }


}
