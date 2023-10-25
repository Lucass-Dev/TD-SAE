package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.EffetTour;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.EffetTours.ProjectileDegatsBrut;

public class TaskKiller extends Tower {

    public TaskKiller(int x, int y){
        super(x, y, "Task Killer", 100, 50, 75, 100, 100, 0, 10, 50, 20);
    }

    @Override
    public String toString() {
        return this.getName() + this.getLevel() +this.getRange();
    }

    @Override
    public EffetTour getEffet() {
        return new ProjectileDegatsBrut(this.getX() + 16, this.getY() + 16, getEnnemieDetecte().get(0));
    }
}
