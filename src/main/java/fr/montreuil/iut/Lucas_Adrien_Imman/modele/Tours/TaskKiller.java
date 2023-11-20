package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.Forges.TypeEffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;

public class TaskKiller extends Tour {

    public TaskKiller(int x, int y){
        super(x, y, "Task Killer", 100, 50, 75, 100, 100, 0, 10, 50, 20);
    }

    @Override
    public String toString() {
        return this.getName() + this.getLevel() +this.getRange();
    }


    @Override
    public EffetTour getEffet(Ennemy ennemy, ModeDeplacement modeDeplacement) {
        return getFabricEffetTours().creeEffetTour(TypeEffetTour.ProjectileDegatsBrut,getXValue()+16,getYValue()+16,ennemy,modeDeplacement);

    }
}
