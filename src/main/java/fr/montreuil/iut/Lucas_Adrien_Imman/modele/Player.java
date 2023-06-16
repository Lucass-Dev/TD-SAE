package fr.montreuil.iut.Lucas_Adrien_Imman.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {

    private IntegerProperty life;
    private IntegerProperty ram ;
    private IntegerProperty flop;
    private IntegerProperty maxlife;
    private IntegerProperty maxRAM;
    private String username;


    public Player(String username) {
        this.life = new SimpleIntegerProperty(100) ;
        this.maxlife = new SimpleIntegerProperty(100);
        this.ram =  new SimpleIntegerProperty(1000000);
        this.maxRAM = new SimpleIntegerProperty(1000);
        this.flop = new SimpleIntegerProperty(5000);
        this.username = username;
    }

    //OTHER METHODS

    public boolean isDead(){
        return getLife() == 0;
    }

    public void looseLife(int l){
        if(life.getValue()-l>=0)
            setLife(life.getValue()-l);
        else
            setLife(0);
    }


    //GETTER
    public int getLife() {
        return life.get();
    }

    public IntegerProperty lifeProperty() {
        return life;
    }

    public int getFlop() {
        return flop.get();
    }

    public IntegerProperty flopProperty() {
        return flop;
    }
    public int getRam() {
        return ram.get();
    }

    public IntegerProperty ramProperty() {
        return ram;
    }

    public int getMaxRAM() {
        return maxRAM.get();
    }

    public IntegerProperty maxRAMProperty() {
        return maxRAM;
    }

    public IntegerProperty maxlifeProperty() {
        return maxlife;
    }

    //SETTER

    public void setLife(int life) {
        this.life.set(life);
    }

    public void setRam(int ram) {
        if (ram > this.getMaxRAM()){
            this.ram.set(this.maxRAM.getValue());
        }else{
            this.ram.set(ram);
        }
    }

    public void setFlop(int flop) {
        this.flop.set(flop);
    }
}
