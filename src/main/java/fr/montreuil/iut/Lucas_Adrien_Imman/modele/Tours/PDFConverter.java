package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.Forges.TypeEffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class PDFConverter extends Tour {

    public PDFConverter(int x, int y){
        super(x, y, "PDF Converter", 100, 750, 800, 75, 500, 5, 10, 50, 50,false);
    }

    @Override
    public EffetTour getEffet(Ennemy ennemy, ModeDeplacement modeDeplacement) {
        if(ennemy.isCibleDePDFConverter()) {
            return getFabricEffetTours().creeEffetTour(TypeEffetTour.ProjectileDotSH,getXValue()+16,getYValue()+16,ennemy,modeDeplacement);
        }
        return null;
    }
}
