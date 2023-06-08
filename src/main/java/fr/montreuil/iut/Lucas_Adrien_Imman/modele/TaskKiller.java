package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public class TaskKiller extends Tower {

    public TaskKiller(int x, int y){
        super(x, y, "Task Killer", 100, 50, 75, 100, 100, 0);
    }

    @Override
    public String toString() {
        return this.getName() + this.getLevel() +this.getRange();
    }
}
