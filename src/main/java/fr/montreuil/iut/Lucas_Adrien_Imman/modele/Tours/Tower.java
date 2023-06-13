package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Tours;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis.Ennemy;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

abstract public class Tower{
    private SimpleIntegerProperty x, y;
    private SimpleIntegerProperty range;
    private SimpleIntegerProperty damage;
    private SimpleIntegerProperty reloadSpeed;
    private int flopPrice;
    private int ramPrice;
    private String name;
    private SimpleIntegerProperty level;
    private SimpleIntegerProperty upgradeCost;
    private Pane tilePane;
    private int spriteIndex;
    private String id;
    public static int compteur = 0;
    private SimpleIntegerProperty movingPrice;
    private SimpleBooleanProperty showingRange;

    public Tower(int x, int y, String name, int movingPrice, int flopPrice, int upgradeCost, int range, int ramPrice, int spriteIndex, int damage, int reloadSpeed){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.spriteIndex = spriteIndex;
        this.id = "T"+compteur;
        compteur++;
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
    }

    //GETTER


    public boolean isShowingRange() {
        return showingRange.get();
    }

    public SimpleBooleanProperty showingRangeProperty() {
        return showingRange;
    }

    public String getId() {
        return id;
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
        return upgradeCost.get();
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

    public int getSpriteIndex() {
        return spriteIndex;
    }

    public int getRange() {
        return range.get();
    }

    public SimpleIntegerProperty rangeProperty() {
        return range;
    }

    public int getDamage() {
        return damage.get();
    }

    public SimpleIntegerProperty damageProperty() {
        return damage;
    }

    public int getReloadSpeed() {
        return reloadSpeed.get();
    }

    public SimpleIntegerProperty reloadSpeedProperty() {
        return reloadSpeed;
    }

    public SimpleIntegerProperty upgradeCostProperty() {
        return upgradeCost;
    }

    //SETTER
    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
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
        this.upgradeCost.set(upgradeCost);
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

    public void setId(String id) {
        this.id = id;
    }

    public void setMovingPrice(int movingPrice) {
        this.movingPrice.set(movingPrice);
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
        }
    }
    public Ennemy detect(ObservableList<Ennemy> ennemis){
        for (Ennemy m : ennemis) {
            if ((this.getY()-range.get()<=m.getY() && m.getY()<= this.getY()+range.get()) && (this.getX()-range.get()<=m.getX() && m.getX() <= this.getX()+range.get())){
                return m;
            }
        }
        return null;
    }
}
