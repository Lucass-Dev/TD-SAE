package fr.montreuil.iut.Lucas_Adrien_Imman.modele.Ennemis;

import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Level;
import fr.montreuil.iut.Lucas_Adrien_Imman.modele.Player;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

public abstract class Ennemy {
    private String id;
    private Pane levelPane;
    private Level level;
    private SimpleIntegerProperty x, y;
    private SimpleIntegerProperty life;
    private String name;
    private int speed;
    public static int compteur=0;
    Player player ;
    private int spriteIndex;
    private SimpleIntegerProperty maxLife;
    private int damage ;
    private int dropRate ;
    private int initialSpeed ;


    //direction stands for the cardinal direction with an int value : 1 North 2 East 3 South 4 West 0 for nothing
    private int direction;



    public Ennemy(int x, int y, Pane levelPane, Level level, int spriteIndex, int life , Player player, int speed, int maxLife , int damage , int dropeRate , int initialSpeed){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.levelPane = levelPane;
        this.level = level;
        this.direction = 4;
        this.speed = speed;
        this.life = new SimpleIntegerProperty(life);
        this.maxLife = new SimpleIntegerProperty(maxLife);
        this.spriteIndex = spriteIndex;
        this.id= "E" + compteur;
        compteur++;
        this.player = player ;
        this.damage = damage ;
        this.dropRate = dropeRate ;
        this.initialSpeed = initialSpeed ;

    }

    public Ennemy(int x, int y) {
        super();
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);

    }

    public void resetSpped(){
        setSpeed(this.initialSpeed);
    }

    public int getDropRate() {
        return dropRate;
    }

    public void setDropRate(int dropRate) {
        this.dropRate = dropRate;
    }

    public void doDamage(){
        if(isOnObjective()){
            player.lifeReduction(damage);
        }
    }

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
        return levelPane;
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

    public SimpleIntegerProperty getLife() {
        return life;
    }
    public int getLifeValue(){
        return life.getValue();
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean estMort(){
        return getLife().getValue()==0 ;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void setLife(int life) {
        this.life.set(life);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public SimpleIntegerProperty lifeProperty() {
        return life;
    }

    public int getMaxLife() {
        return maxLife.get();
    }

    public SimpleIntegerProperty maxLifeProperty() {
        return maxLife;
    }

    public void reductionPv(int l){
        if(life.getValue()-l>=0)
            setLife(life.getValue()-l);
        else
            setLife(0);
    }


    public void reductionSpeed(int s){
        if(speed-s>=0)
            setSpeed(speed-=s);
    }
    public int getSpriteIndex() {
        return spriteIndex;
    }
}
