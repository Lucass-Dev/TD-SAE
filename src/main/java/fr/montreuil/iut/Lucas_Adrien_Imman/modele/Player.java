package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {

    private IntegerProperty life;
    private IntegerProperty ram ;
    private IntegerProperty flop;
    private IntegerProperty x , y ;
    private IntegerProperty maxlife;
    private IntegerProperty maxRAM;



    public Player(SimpleIntegerProperty x , SimpleIntegerProperty y ) {
        this.life = new SimpleIntegerProperty(100) ;
        this.maxlife = new SimpleIntegerProperty(100);
        this.ram =  new SimpleIntegerProperty(0);
        this.maxRAM = new SimpleIntegerProperty(100);
        this.flop = new SimpleIntegerProperty(500);
        this.x = x ;
        this.y = y ;
    }

    public int getMaxRAM() {
        return maxRAM.get();
    }

    public IntegerProperty maxRAMProperty() {
        return maxRAM;
    }

    public int getMaxlife() {
        return maxlife.get();
    }

    public IntegerProperty maxlifeProperty() {
        return maxlife;
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

    public int getLife() {
        return life.get();
    }

    public IntegerProperty lifeProperty() {
        return life;
    }

    public void setLife(int life) {
        this.life.set(life);
    }

    public int getRam() {
        return ram.get();
    }

    public IntegerProperty ramProperty() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram.set(ram);
    }

    public int getFlop() {
        return flop.get();
    }

    public IntegerProperty flopProperty() {
        return flop;
    }

    public void setFlop(int flop) {
        this.flop.set(flop);
    }

    public boolean isDead(){
        return this.life.get() <= 0;
    }


}
