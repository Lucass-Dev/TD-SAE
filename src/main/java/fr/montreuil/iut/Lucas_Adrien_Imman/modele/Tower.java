package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.collections.ObservableList;
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
    private String id;
    public static int compteur = 0;
    private SimpleIntegerProperty movingPrice;

    public Tower(){};

    public Tower(int x, int y, Image image, String name, int movingPrice, int flopPrice, int upgradeCost, int range, int ramPrice){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.sprite = image;
        this.id = "T"+compteur;
        compteur++;
        this.name = name;
        this.level =  new SimpleIntegerProperty(1);
        this.movingPrice = new SimpleIntegerProperty(100);
        this.range = range;
        this.flopPrice = flopPrice;
        this.ramPrice = ramPrice;
        this.upgradeCost = upgradeCost;
    }

    //GETTER


    public String getId() {
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

    public Image getSprite() {
        return sprite;
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
    public Ennemy detect(ObservableList<Ennemy> ennemis ){
        for (Ennemy m : ennemis) {
            if ((this.getY()-100<=m.getY() && m.getY()<= this.getY()+100) && (this.getX()-100<=m.getX() && m.getX() <= this.getX()+100)){
                return m;
            }
        }
        return null;
    }
}
