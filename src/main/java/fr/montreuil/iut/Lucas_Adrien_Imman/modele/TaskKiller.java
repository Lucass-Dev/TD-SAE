package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class TaskKiller extends Tower {

    public TaskKiller(){super();}

    public TaskKiller(Pane tilePane, int x, int y, Image image){super(tilePane, x, y, image);}

    public TaskKiller(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, int x, int y){
        super(range, flopPrice, ramPrice, name, level, upgradeCost, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    @Override
    public void attack(){

    }

    @Override
    public void detect(){
        System.out.println("test");
    }

    @Override
    public String toString() {
        return this.getName() + this.getLevel() +this.getRange();
    }
}
//8506