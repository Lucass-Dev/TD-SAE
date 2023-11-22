package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.Forges.TypeEffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class CCleaner extends Tour {


    public CCleaner(int x, int y){
        super(x, y, "CCleaner", 100, 100, 150, 100, 150, 1,50, 50, 50,true);
    }


    @Override
    public EffetTour getEffet(Ennemy ennemy, ModeDeplacement modeDeplacement) {
        return getFabricEffetTours().creeEffetTour(TypeEffetTour.ZoneElectrique,getXValue()+16,getYValue()+16,ennemy,modeDeplacement);
    }
}
