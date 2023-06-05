package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

abstract public class Tower{
    private SimpleIntegerProperty x, y;
    private int range;
    private int flopPrice;
    private int ramPrice;
    private String name;
    private int level;
    private int upgradeCost;


    public Tower(){};

    public Tower(int x, int y){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public Tower(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, SimpleIntegerProperty x, SimpleIntegerProperty y ) {
        this.range = range;
        this.flopPrice = flopPrice;
        this.ramPrice = ramPrice;
        this.name = name;
        this.level = level;
        this.upgradeCost = upgradeCost;
        this.x = x;
        this.y = y;
    }

    public Ennemy ennemiProche(ObservableList<Ennemy> ennemis ){
        for (Ennemy m : ennemis) {
            if ((this.getY()-100<=m.getY() && m.getY()<= this.getY()+100) && (this.getX()-100<=m.getX() && m.getX() <= this.getX()+100)){
                return m;
            }
        }
        return null;
    }



    //GETTER

    public int getRange() {
        return range;
    }

    public int getFlopPrice() {
        return flopPrice;
    }

    public int getRamPrice() {
        return ramPrice;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    //SETTER
    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setFlopPrice(int flopPrice) {
        this.flopPrice = flopPrice;
    }

    public void setRamPrice(int ramPrice) {
        this.ramPrice = ramPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

}
