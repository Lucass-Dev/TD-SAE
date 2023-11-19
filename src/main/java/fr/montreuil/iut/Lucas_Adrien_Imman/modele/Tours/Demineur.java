package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ProjectileKamikaze;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class Demineur extends Tower {

    public Demineur(int x, int y){
        super(x, y, "Demineur", 100, 250, 300, 75, 175, 2, 40, 50, 50);
    }

    @Override
    public EffetTour getEffet(Ennemy ennemy, ModeDeplacement modeDeplacement) {
            if(ennemy.isCibleDeDemineur()){
            return  new ProjectileKamikaze(this.getXValue() + 16, this.getYValue() + 16, ennemy , modeDeplacement);
        }
        else
            return null ;
    }
}
