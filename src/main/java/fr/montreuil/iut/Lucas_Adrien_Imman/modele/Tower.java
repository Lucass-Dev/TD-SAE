package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.scene.image.Image;

abstract public class Tower{
    private int range;
    private int flopPrice;
    private int ramPrice;
    private String name;
    private int level;
    private int upgradeCost;
    private Image sprite;

    abstract public void attack(/*cible*/);
    abstract public void detect();

    public Tower(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, Image sprite) {
        this.range = range;
        this.flopPrice = flopPrice;
        this.ramPrice = ramPrice;
        this.name = name;
        this.level = level;
        this.upgradeCost = upgradeCost;
        this.sprite = sprite;
    }

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
}
