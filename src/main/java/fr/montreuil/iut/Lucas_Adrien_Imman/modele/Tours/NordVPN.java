package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ProjectileKnockBack;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class NordVPN extends Tower {


    public NordVPN(int x, int y){
        super(x, y, "Task Killer", 100, 500, 600, 100, 300, 4, 10, 50, 50);
    }

    @Override
    public EffetTour getEffet(Ennemy ennemy, ModeDeplacement modeDeplacement) {
        return new ProjectileKnockBack(this.getXValue() + 16, this.getYValue() + 16, ennemy , modeDeplacement);
    }
}
