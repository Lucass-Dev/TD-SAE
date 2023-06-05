package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

abstract public class Tower{
    private SimpleIntegerProperty x, y;
    private int range;
    private int flopPrice;
    private int ramPrice;
    private String name;
    private SimpleIntegerProperty level;
    private int upgradeCost;
    private Pane tilePane;
    private Image sprite;
    private int id;
    private SimpleIntegerProperty movingPrice;

    abstract public void attack();
    abstract public void detect();

    public Tower(){};

    public Tower(Pane tilePane, int x, int y, Image image, int id){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.sprite = image;
        this.id = id;
        this.name = "Je suis la tour";
        this.level =  new SimpleIntegerProperty(1);
        this.movingPrice = new SimpleIntegerProperty(100);
    }

    public Tower(int range, int flopPrice, int ramPrice, String name, int level, int upgradeCost, SimpleIntegerProperty x, SimpleIntegerProperty y, int id) {
        this.range = range;
        this.flopPrice = flopPrice;
        this.ramPrice = ramPrice;
        this.name = name;
        this.level = new SimpleIntegerProperty(1);
        this.upgradeCost = upgradeCost;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    //GETTER


    public int getId() {
        return id;
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

    public SimpleIntegerProperty getLevel() {
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

    public SimpleIntegerProperty levelProperty() {
        return level;
    }

    public Pane getTilePane() {
        return tilePane;
    }

    public int getMovingPrice() {
        return movingPrice.get();
    }

    public SimpleIntegerProperty movingPriceProperty() {
        return movingPrice;
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
        this.level.set(level);
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    //OTHER METHODS
    public void upgrade(Player p){
        if (p.getFlop() >= this.upgradeCost){
            p.setFlop(p.getFlop() - this.upgradeCost);
            this.level.setValue(this.getLevel().get() + 1);
            System.out.println("J'ai amélioré la tour");
        }else{
            System.out.println("Pas assez d'argent");
        }
    }
}
