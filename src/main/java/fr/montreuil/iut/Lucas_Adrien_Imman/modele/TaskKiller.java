package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;


public class TaskKiller extends Tower {

    public TaskKiller(){
        super();
    }

    public TaskKiller(int x, int y){
        super(x,y);
    }

    public TaskKiller(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, int x, int y){
        super(range, flopPrice, ramPrice, name, level, upgradeCost, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y) );

    }

    @Override
    public  Ennemy ennemiProche(ObservableList<Ennemy> en ){
        return  super.ennemiProche(en);
    }



    @Override
    public String toString() {
        return this.getName() + this.getLevel() +this.getRange();
    }
}
