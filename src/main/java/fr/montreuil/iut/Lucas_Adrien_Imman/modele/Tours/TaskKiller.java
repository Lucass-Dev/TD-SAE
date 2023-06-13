package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

public class TaskKiller extends Tower {

    public TaskKiller(int x, int y){
        super(x, y, "Task Killer", 100, 50, 75, 100, 100, 0, 10, 50);
    }

    @Override
    public String toString() {
        return this.getName() + this.getLevel() +this.getRange();
    }
}
