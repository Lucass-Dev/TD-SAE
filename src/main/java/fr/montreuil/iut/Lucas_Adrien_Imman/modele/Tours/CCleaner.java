package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ZoneElectrique;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class CCleaner extends Tower {


    public CCleaner(int x, int y){
        super(x, y, "CCleaner", 100, 100, 150, 100, 150, 1,50, 50, 50);
    }


    @Override
    public EffetTour getEffet(Ennemy ennemy) {
        return new ZoneElectrique(this.getX() + 16, this.getY() + 16, ennemy);
    }
}
