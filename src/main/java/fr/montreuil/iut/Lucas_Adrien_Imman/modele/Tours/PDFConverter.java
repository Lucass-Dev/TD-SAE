package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ProjectileDotSH;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.DotExe;

public class PDFConverter extends Tower {

    public PDFConverter(int x, int y){
        super(x, y, "PDF Converter", 100, 750, 800, 75, 500, 5, 10, 50, 50);
    }

    @Override
    public EffetTour getEffet() {
        if(firstDetect instanceof DotExe) {
            return new ProjectileDotSH(this.getX() + 16, this.getY() + 16, firstDetect);
        }
    }
}
