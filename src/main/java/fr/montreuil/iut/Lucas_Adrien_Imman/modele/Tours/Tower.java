package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Acteur;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

abstract public class Tower extends Acteur {


    private int spriteIndex;


    private String name ;
    private SimpleIntegerProperty range;
    private SimpleIntegerProperty damage;
    private SimpleIntegerProperty reloadSpeed;
    private SimpleIntegerProperty upgradeCost;
    private SimpleIntegerProperty movingPrice;
    private SimpleBooleanProperty showingRange;
    private SimpleIntegerProperty sellingPrice;
    private int flopPrice;
    private int ramPrice;

    private SimpleIntegerProperty level;
    private Pane tilePane;
    private int delais ;

    private ArrayList<Ennemy> ennemieDetecte ;

    public Tower(int x, int y, String name, int movingPrice, int flopPrice, int upgradeCost, int range, int ramPrice, int spriteIndex, int damage, int reloadSpeed, int delais){

        super(x,y,"T"+compteur);
        this.spriteIndex = spriteIndex;
        this.name = name;
        this.level =  new SimpleIntegerProperty(1);
        this.movingPrice = new SimpleIntegerProperty(movingPrice);
        this.range = new SimpleIntegerProperty(range);
        this.flopPrice = flopPrice;
        this.ramPrice = ramPrice;
        this.upgradeCost = new SimpleIntegerProperty(upgradeCost);
        this.damage = new SimpleIntegerProperty(damage);
        this.reloadSpeed = new SimpleIntegerProperty(reloadSpeed);
        this.showingRange = new SimpleBooleanProperty(false);
        this.sellingPrice = new SimpleIntegerProperty((int) (this.flopPrice*0.75));
        this.delais = delais ;
        this.ennemieDetecte = new ArrayList<>() ;
    }

    public SimpleIntegerProperty movingPriceProperty() {
        return movingPrice;
    }

    public void setDelais(int delais) {
        this.delais = delais;
    }

    public boolean isShowingRange() {
        return showingRange.get();
    }

    public int getUpgradeCost() {
        return upgradeCost.get();
    }

    public SimpleIntegerProperty levelProperty() {
        return level;
    }

    public Pane getTilePane() {
        return tilePane;
    }

    public int getDamage() {
        return damage.get();
    }

    public int getReloadSpeed() {
        return reloadSpeed.get();
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

    public void setSpriteIndex(int spriteIndex) {
        this.spriteIndex = spriteIndex;
    }

    public void setRange(int range) {
        this.range.set(range);
    }

    public void setDamage(int damage) {
        this.damage.set(damage);
    }

    public void setReloadSpeed(int reloadSpeed) {
        this.reloadSpeed.set(reloadSpeed);
    }

    public void setTilePane(Pane tilePane) {
        this.tilePane = tilePane;
    }

    public void setMovingPrice(int movingPrice) {
        this.movingPrice.set(movingPrice);
    }



    //GETTER
    public int getDelais() {
        return this.delais;
    }

    public int getSellingPrice() {
        return sellingPrice.get();
    }

    public SimpleIntegerProperty sellingPriceProperty() {
        return sellingPrice;
    }

    public SimpleBooleanProperty showingRangeProperty() {
        return showingRange;
    }

    public int getFlopPrice() {
        return flopPrice;
    }

    public int getRamPrice() {
        return ramPrice;
    }

    public SimpleIntegerProperty getLevel() {
        return level;
    }

    public int getMovingPrice() {
        return movingPrice.get();
    }

    public int getSpriteIndex() {
        return spriteIndex;
    }

    public int getRange() {
        return range.get();
    }

    public SimpleIntegerProperty rangeProperty() {
        return range;
    }

    public SimpleIntegerProperty damageProperty() {
        return damage;
    }

    public SimpleIntegerProperty reloadSpeedProperty() {
        return reloadSpeed;
    }

    public SimpleIntegerProperty upgradeCostProperty() {
        return upgradeCost;
    }

    public String getName() {
        return name;
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost.set(upgradeCost);
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice.set(sellingPrice);
    }

    public void setShowingRange(boolean showingRange) {
        this.showingRange.set(showingRange);
    }
    //OTHER METHODS
    public void upgrade(Player p){
        if (p.getFlop() >= this.upgradeCost.get() && this.level.get() <= 10){
            p.setFlop(p.getFlop() - this.upgradeCost.get());
            this.level.setValue(this.getLevel().get() + 1);
            this.range.setValue(this.range.getValue() + 5);
            this.reloadSpeed.setValue(this.reloadSpeed.getValue() - 1);
            this.damage.setValue(this.damage.getValue() + 1);
            setUpgradeCost((int) (this.upgradeCost.get() * 1.2));
            setSellingPrice((int) (this.sellingPrice.get() * 1.2));
        }
    }

    public ArrayList<Ennemy> detect(ObservableList<Ennemy> ennemis){ // detecte l'ennemi qui a une range donné en paramétre | ex :(this.getX()+16) = prend le centre la tour (+16 pour obtenir le milieu)
        for (Ennemy m : ennemis) {
            if ((this.getY()+16)-range.get()<=m.getY() && m.getY()<= (this.getY()+16)+range.get() && (this.getX()+16)-range.get()<=m.getX() && m.getX() <= (this.getX()+16)+range.get()){
                ennemieDetecte.add(m);
            }
        }
        return ennemieDetecte ;
    }

}
