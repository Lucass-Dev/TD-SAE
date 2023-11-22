package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.Forges.TypeEffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class InternetExplorer extends Tour {

    public InternetExplorer(int x, int y){
        super(x, y, "Internet Explorer", 100, 325, 475, 75, 125, 3, 10, 50, 50,true);
    }

    @Override
    public EffetTour getEffet(Ennemy ennemy, ModeDeplacement modeDeplacement) {
        return getFabricEffetTours().creeEffetTour(TypeEffetTour.ZoneRalentisseur,getXValue()+16,getYValue()+16,ennemy,modeDeplacement);
    }
}