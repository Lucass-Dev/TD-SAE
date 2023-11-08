package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Deplaçable;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Environment;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public abstract class Ennemy extends Deplaçable {
    private String id;
    private Pane levelPane;
    private Environment environment;
    private SimpleIntegerProperty life;
    private int speed;
    public static int compteur=0;
    Player player ;
    private int spriteIndex;
    private SimpleIntegerProperty maxLife;
    private int damage ;
    private int dropRate ;
    private int initialSpeed ;


    //la direction représente une direction cardinale : 1 North 2 East 3 South 4 West 0 for nothing
    private int direction;


    public Ennemy(int x, int y, Pane levelPane, Environment environment, int spriteIndex, int life , Player player, int speed, int maxLife , int damage ,int startDirection ,int dropeRate , int initialSpeed){
        super(x,y,"E" + compteur);
        this.levelPane = levelPane;
        this.environment = environment;
        this.speed = speed;
        this.life = new SimpleIntegerProperty(life);
        this.maxLife = new SimpleIntegerProperty(maxLife);
        this.spriteIndex = spriteIndex;
        this.player = player ;
        this.damage = damage ;
        this.direction = startDirection;
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
    public String getId() {
        return id;
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
    public Environment getEnvironment() {
        return environment;
    }


    //OTHER METHODS

    public Ennemy isTouching(ObservableList<Ennemy> ennemies){
        for (Ennemy e: ennemies) {
            if (this.getX() < e.getX() +8 && this.getX() > e.getX() -8 && this.getY() < e.getY() +8 && this.getY() < e.getY() -8){
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
    public void resetSpped(){
        setSpeed(this.initialSpeed);
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

    public int getDirection() {
        return direction;
    }

    //Pour savoir si l'ennemie est centré sur la tuile avec une "marge d'erreur"

    public boolean isCentered(){
        int[] center;
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;

        center = this.environment.getGround().getTileCenter(pos);

        pos[0] = this.getX();
        pos[1] = this.getY();

        return pos[0] <= center[0]+3 && pos[0] >= center[0]-3 && pos[1] <= center[1]+3 && pos[1] >= center[1]-3 ;
    }

    public boolean isOnBound(){
        return this.getX() < this.levelPane.getWidth() && this.getY() < this.levelPane.getHeight() && this.getX() >= 0 && this.getY() >=0;
    }

    public boolean isOnObjective(){
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;
        return this.environment.getGround().getTileValue(pos) == 7;
    }

    public int getDropRate() {
        return dropRate;
    }
}
