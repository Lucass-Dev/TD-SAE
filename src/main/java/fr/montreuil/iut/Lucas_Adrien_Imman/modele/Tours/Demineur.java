package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;

public class Demineur extends Tower {

    public Demineur(int x, int y){
        super(x, y, "Demineur", 100, 250, 300, 75, 175, 2,40, 50,50);
    }

    @Override
    public EffetTour getEffet() {

    }
}
