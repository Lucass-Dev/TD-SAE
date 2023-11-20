package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.Forges.TypeEffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class NordVPN extends Tour {


    public NordVPN(int x, int y){
        super(x, y, "Task Killer", 100, 500, 600, 100, 300, 4, 10, 50, 50);
    }

    @Override
    public EffetTour getEffet(Ennemy ennemy, ModeDeplacement modeDeplacement) {
        return getFabricEffetTours().creeEffetTour(TypeEffetTour.ProjectileKnockBack,getXValue()+16,getYValue()+16,ennemy,modeDeplacement);
    }
}
