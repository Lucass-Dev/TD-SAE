package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.Deplaçable;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public abstract class Ennemy extends Deplaçable {


    private int spriteIndex;

    private Pane levelPane;
    private Level level;
    Player player ;

    private SimpleIntegerProperty life;
    private int speed;
    private int damage ;
    private int initialSpeed ;
    private SimpleIntegerProperty maxLife;
    //la direction représente une direction cardinale : 1 North 2 East 3 South 4 West 0 for nothing
    private int direction;

    private int dropRate ;


    public Ennemy(int x, int y, Pane levelPane, Level level, int spriteIndex, int life , Player player, int speed, int maxLife , int damage, int startDirection , int dropeRate , int initialSpeed){
        super(x,y,"E" + compteur);

        this.levelPane = levelPane;
        this.level = level;
        this.direction = startDirection;
        this.speed = speed;
        this.life = new SimpleIntegerProperty(life);
        this.maxLife = new SimpleIntegerProperty(maxLife);
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
    public Level getLevel() {
        return level;
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

    //OTHER METHODS

    public Ennemy isTouching(ObservableList<Ennemy> ennemies){
        for (Ennemy e: ennemies) {

                if (this.getX() < e.getX() +8 && this.getX() > e.getX() -8 && this.getY() < e.getY() +8 && this.getY() < e.getY() -8){
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
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;

        center = this.level.getTileCenter(pos);

        pos[0] = this.getX();
        pos[1] = this.getY();

        return pos[0] <= center[0]+3 && pos[0] >= center[0]-3 && pos[1] <= center[1]+3 && pos[1] >= center[1]-3 ;
    }

    //Change la direction de l'ennemie en fonction de la valeur de la TravelingMap à ses coordonnées et en fonction de
    // sa direction initiale
    public void algoDeplacement() {
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;
        int travelingValue = this.getLevel().getTileValue(pos);

        switch (travelingValue){
            case 2 -> {
                if (this.getDirection() == 2){
                    if (isCentered()){
                        this.setDirection(1);
                    }
                } else if (this.getDirection() == 3) {
                    if(isCentered()){
                        this.setDirection(4);
                    }
                }
            }
            case 3 -> {
                if (this.getDirection() == 2){
                    if (isCentered()){
                        this.setDirection(3);
                    }
                } else if (this.getDirection() == 1) {
                    if(isCentered()){
                        this.setDirection(4);
                    }
                }
            }
            case 4 -> {
                if (this.getDirection() == 4){
                    if (isCentered()){
                        this.setDirection(1);
                    }
                } else if (this.getDirection() == 3) {
                    if (isCentered()){
                        this.setDirection(2);
                    }
                }
            }
            case 5 -> {
                if (this.getDirection() == 4){
                    if (isCentered()){
                        this.setDirection(3);
                    }
                } else if (this.getDirection() == 1) {
                    if (isCentered()){
                        this.setDirection(2);
                    }
                }
            }
        }

        if (this.getDirection() == 1){
            this.setY(this.getY()-this.getSpeed());
        }
        else if(this.getDirection() == 2){
            this.setX(this.getX()+this.getSpeed());
        }
        else if(this.getDirection() == 3){
            this.setY(this.getY()+this.getSpeed());
        }
        else if(this.getDirection() == 4){
            this.setX(this.getX()-this.getSpeed());
        }

    }
    public boolean isOnBound(){
        return this.getX() < this.levelPane.getWidth() && this.getY() < this.levelPane.getHeight() && this.getX() >= 0 && this.getY() >=0;
    }
    public boolean isOnObjective(){
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;
        return this.level.getTileValue(pos) == 7;
    }
    public void resetSpped(){
        setSpeed(this.initialSpeed);
    }
    public int getDropRate() {
        return dropRate;
    }



}
