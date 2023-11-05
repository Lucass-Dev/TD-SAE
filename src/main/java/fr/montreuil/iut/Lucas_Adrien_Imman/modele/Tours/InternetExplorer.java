package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.AgirSurCible;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ZoneRalentisseur;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class InternetExplorer extends Tower  {

    public InternetExplorer(int x, int y){
        super(x, y, "Internet Explorer", 100, 325, 475, 75, 125, 3, 10, 50, 50);
    }

    @Override
    public EffetTour getEffet(Ennemy ennemy) {
        return  new ZoneRalentisseur(this.getX() + 16, this.getY() + 16, ennemy);
    }
}