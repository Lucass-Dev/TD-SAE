package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

import java.util.Arrays;

public abstract class Ennemy {
    private String id;
    private Pane tilePane;
    private Level level;
    private SimpleIntegerProperty x, y;
    private SimpleIntegerProperty life;
    private String name;
    private int speed;
    public static int compteur=0;
    Player player ;

    //direction stands for the cardinal direction with an int value : 1 North 2 East 3 South 4 West 0 for nothing
    private int direction;

    public Ennemy(Pane tilePane, Level level , int life , Player player){

        this.x = new SimpleIntegerProperty(545);
        this.y = new SimpleIntegerProperty(545);
        this.tilePane = tilePane;
        this.level = level;
        this.direction = 4;
        this.speed = 2;
        this.life = new SimpleIntegerProperty(life);
        this.id= "E" + compteur;
        compteur++;
        this.player = player ;

    }

    public void doDamage(){
        if(isOnObjective()){
            player.setLife(getLife()-10);
        }
    }
    public abstract void move();
    public boolean isCentered(){
        int[] center;
        int[] pos = new int[2];
        pos[0] = this.getX()/32;
        pos[1] = this.getY()/32;

        center = this.level.getTileCenter(pos);

        pos[0] = this.getX();
        pos[1] = this.getY();

        return pos[0] <= center[0]+5 && pos[0] >= center[0]-5 && pos[1] <= center[1]+5 && pos[1] >= center[1]-5 ;
    }

    public boolean isOnBound(){// Dans le cas ou il dépasse les tuiles de la map
        //System.out.println("Objet en "+this.getX() + " sur " + this.tilePane.getWidth());
        //System.out.println("Objet en "+this.getY() + " sur " + this.tilePane.getHeight());
       // System.out.println(this.getX() < this.tilePane.getWidth() && this.getY() < this.tilePane.getHeight());

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

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean estMort(){
        return getLife()==0 ;
    }

    public int getLife() {
        return life.get();
    }

    public SimpleIntegerProperty lifeProperty() {
        return life;
    }

    public void setLife(int life) {
        this.life.set(life);
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void reductionPv(int l){
        setLife(getLife()-l);
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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
