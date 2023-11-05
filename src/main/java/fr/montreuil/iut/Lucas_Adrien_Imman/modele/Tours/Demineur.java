package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ProjectileKamikaze;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.*;

public class Demineur extends Tower {

    public Demineur(int x, int y){
        super(x, y, "Demineur", 100, 250, 300, 75, 175, 2,40, 50,50);
    }

    @Override
    public EffetTour getEffet(Ennemy ennemy) {
        if((!(ennemy instanceof DotExe) &&  !(ennemy instanceof Virus) && !(ennemy instanceof Scam) && !(ennemy instanceof Kamikaze))){
            return  new ProjectileKamikaze(this.getX() + 16, this.getY() + 16, ennemy);
        }
        else
            return null ;
    }
}
