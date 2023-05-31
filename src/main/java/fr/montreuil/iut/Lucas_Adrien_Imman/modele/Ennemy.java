package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

import java.util.Arrays;

public abstract class Ennemy {
    private int id;
    private Pane tilePane;
    private Level level;
    private SimpleIntegerProperty x, y;
    private int life;
    private String name;
    private int speed;

    //direction stands for the cardinal direction with an int value : 1 North 2 East 3 South 4 West 0 for nothing
    private int direction;

    public Ennemy(int x, int y, Pane tilePane, Level level){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.tilePane = tilePane;
        this.level = level;
        this.direction = 4;
        this.speed = 5;
    }

    public abstract void doDamage();

    public boolean isCentered(){
        int[] center = new int[2];
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;

        center = this.level.getTileCenter(pos);

        pos[0] = this.getX();
        pos[1] = this.getY();

        System.out.println(Arrays.toString(center));
        System.out.println(Arrays.toString(pos));

        return pos[0] <= center[0]+5 && pos[0] >= center[0]-5 && pos[1] <= center[1]+5 && pos[1] >= center[1]-5 ;
    }
    public void move() {
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
            this.setX(this.getX()+getSpeed());
        }
        else if(this.getDirection() == 3){
            this.setY(this.getY()+this.getSpeed());
        }
        else if(this.getDirection() == 4){
            this.setX(this.getX()-getSpeed());
        }

    }

    public boolean isOnBound(){
        return this.getX() < this.tilePane.getWidth() && this.getY() < this.tilePane.getHeight() && this.getX() >= 0 && this.getY() >=0;
    }

    public boolean isOnObjective(){
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;
        return this.level.getTileValue(pos) == 7;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public Level getLevel() {
        return level;
    }

    public Pane getTilePane() {
        return tilePane;
    }

    public int getX() {
        return x.get();
    }

    public SimpleIntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public SimpleIntegerProperty yProperty() {
        return y;
    }

    public int getLife() {
        return life;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void kill(Object o) {
        o = null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
