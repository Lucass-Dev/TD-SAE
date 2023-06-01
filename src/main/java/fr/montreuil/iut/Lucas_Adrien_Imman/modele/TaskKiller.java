package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import fr.montreuil.iut.Lucas_Adrien_Imman.ACO.Acteur;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class TaskKiller extends Tower {

    public TaskKiller(){
        super();
    }

    public TaskKiller(int x, int y){
        super(x,y);
    }

    public TaskKiller(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, int x, int y){
        super(range, flopPrice, ramPrice, name, level, upgradeCost, new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
    }

    @Override
    public  Ennemy attack(ObservableList<Ennemy> en){
        
            for (Ennemy m : en) {
                if ((this.getY()-150<=m.getY() && m.getY()<= this.getY()+150) && (this.getX()-150<=m.getX() && m.getX() <= this.getX()+150)){

                    return m;
                }
            }
            return null;
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
