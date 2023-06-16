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
        this.life = new SimpleIntegerProperty(1) ;
        this.maxlife = new SimpleIntegerProperty(100);
        this.ram =  new SimpleIntegerProperty(10000);
        this.maxRAM = new SimpleIntegerProperty(10000);
        this.flop = new SimpleIntegerProperty(500);
        this.username = username;
    }

    public String getUsername() {
        return username;
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

    public int getLife() {
        return life.get();
    }

    public void lifeReduction(int l){
        if(life.getValue()-l>=0)
            setLife(life.getValue()-l);
        else
            setLife(0);
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
        if (ram > this.getMaxRAM()){
            this.ram.set(this.maxRAM.getValue());
        }else{
            this.ram.set(ram);
        }
    }

    public void setMaxRAM(int i){
        this.maxRAM.set(this.maxRAM.get()+i);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDead(){
        return getLife() == 0;
    }


}
