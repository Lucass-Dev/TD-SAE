package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;


import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplaçable;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplacement.ModeDeplacement;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public abstract class Ennemy extends Deplaçable {


    private int spriteIndex;

    private Pane levelPane;
    private Environment env;
    Player player ;

    private SimpleIntegerProperty life;
    private int speed;
    private int damage ;
    private int initialSpeed ;
    private SimpleIntegerProperty maxLife;
    
    //la direction représente une direction cardinale : 1 North 2 East 3 South 4 West 0 for nothing
    private int direction;


    private int dropRate ;


    public Ennemy(int x, int y, int health, int maxHealth, Pane levelPane, Environment env, int spriteIndex, Player player, int speed, int damage, int startDirection, int dropeRate, int initialSpeed, ModeDeplacement md) {
        super(x, y, health, maxHealth, md);


        this.levelPane = levelPane;
        this.env = env;
        this.direction = startDirection;
        this.speed = speed;
        this.life = new SimpleIntegerProperty(life.getValue());
        this.maxLife = new SimpleIntegerProperty(maxLife.getValue());
        this.spriteIndex = spriteIndex;
        this.player = player ;
        this.damage = damage ;
        this.dropRate = dropeRate ;
        this.initialSpeed = initialSpeed ;
    }


    public abstract void doDamage();
    public abstract void die();

    //SETTER
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void setLife(int life) {
        this.life.set(life);
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //GETTER
    public int getSpriteIndex() {
        return spriteIndex;
    }
    public Player getPlayer() {
        return player;
    }
    public int getDamage() {
        return damage;
    }
    public Pane getLevelPane() {
        return levelPane;
    }
    public SimpleIntegerProperty lifeProperty() {
        return life;
    }
    public SimpleIntegerProperty maxLifeProperty() {
        return maxLife;
    }
    public SimpleIntegerProperty getLife() {
        return life;
    }
    public int getSpeed() {
        return speed;
    }
    public boolean isDead(){
        return getLife().getValue()==0 ;
    }
    public int getDirection() {
        return direction;
    }
    public Environment getEnv() {
        return env;
    }
    public int getOppositeDirection(){
        if (this.direction == 1){
            return 3;
        }else if (this.direction == 2){
            return 4;
        }else if (this.direction == 3){
            return 1;
        }else if (this.direction == 4){
            return 2;
        }

        return 0;
    }
    public int getDropRate() {
        return dropRate;
    }

    //OTHER METHODS
    public Ennemy isTouching(ObservableList<Ennemy> ennemies){
        for (Ennemy e: ennemies) {

                if (this.getXValue() < e.getXValue() +8 && this.getXValue() > e.getXValue() -8 && this.getYValue() < e.getYValue() +8 && this.getYValue() < e.getYValue() -8){
                    System.out.println("je tpuche");
                    return e;
                }

        }
        return null;
    }
    public void reductionPv(int l){
        if(life.getValue()-l>=0)
            setLife(life.getValue()-l);
        else
            setLife(0);
    }

    //Pour savoir si l'ennemie est centré sur la tuile avec une "marge d'erreur"

    public boolean isCentered(){
        int[] center;
        int[] pos = new int[2];
        pos[0] = this.getXValue()/32;
        pos[1] = this.getYValue()/32;

        center = this.env.getGround().getTileCenter(pos);

        pos[0] = this.getXValue();
        pos[1] = this.getYValue();

        return pos[0] <= center[0]+3 && pos[0] >= center[0]-3 && pos[1] <= center[1]+3 && pos[1] >= center[1]-3 ;
    }
    public void resetSpped(){
        setSpeed(this.initialSpeed);
    }


    public boolean isOnBound(){
        return this.getXValue() < this.levelPane.getWidth() && this.getYValue() < this.levelPane.getHeight() && this.getXValue() >= 0 && this.getYValue() >=0;
    }






}
